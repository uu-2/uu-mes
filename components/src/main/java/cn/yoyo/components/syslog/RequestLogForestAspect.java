package cn.yoyo.components.syslog;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.yoyo.components.syslog.enums.BizStatus;
import cn.yoyo.components.syslog.enums.BizType;
import com.dtflys.forest.exceptions.ForestRuntimeException;
import com.dtflys.forest.http.ForestBody;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.interceptor.Interceptor;
import com.dtflys.forest.reflection.ForestMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RequestLogForestAspect<T> implements Interceptor<T> {
    private final static String ST = "_ST";
    private final static String RT = "_RT";
    private static final String EX = "_E";

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     * 该方法在被调用时，并在beforeExecute前被调用
     *
     * @Param request Forest请求对象
     * @Param args 方法被调用时传入的参数数组
     */
    @Override
    public void onInvokeMethod(ForestRequest req, ForestMethod method, Object[] args) {
        // req 为Forest请求对象，即 ForestRequest 类实例
        // method 为Forest方法对象，即 ForestMethod 类实例
        // addAttribute作用是添加和Forest请求对象以及该拦截器绑定的属性
        req.addAttachment(ST, System.currentTimeMillis());
    }


    /**
     * 该方法在请求成功响应时被调用
     */
    @Override
    public void onSuccess(T data, ForestRequest req, ForestResponse res) {
        req.addAttachment(RT, BizStatus.SUCCESS.ordinal());
    }

    /**
     * 该方法在请求发送失败时被调用
     */
    @Override
    public void onError(ForestRuntimeException ex, ForestRequest req, ForestResponse res) {
        req.addAttachment(RT, BizStatus.FAIL.ordinal());
        req.addAttachment(EX, ex);
    }

    /**
     * 该方法在请求发送之后被调用
     */
    @Override
    public void afterExecute(ForestRequest req, ForestResponse res) {
        // 执行在发送请求之后处理的代码
        handleLog(req, res);
    }

    protected void handleLog(ForestRequest req, ForestResponse res) {
        try {
            long startTime = (long) req.getAttachment(ST);
            long endTime = System.currentTimeMillis();
            int bizStatus = (int) req.getAttachment(RT);
            Object ex = req.getAttachment(EX);


            // *========数据库日志=========*//
            OperLogEvent operLog = new OperLogEvent();
            operLog.setStatus(bizStatus);
            // 设置标题
            operLog.setTitle("调用外部接口");
            // 设置action动作
            operLog.setBusinessType(BizType.EXT_API_CALL.ordinal());

            OperLogEvent.fillLogUser(operLog);
            // 设置方法名称
            operLog.setExecuteHandler("forest");
            // 设置消耗时间
            operLog.setOperTime(DateTime.of(startTime));
            operLog.setCostTime(endTime - startTime);

            // 保存请求参数
            operLog.setApiUrl(req.urlString());
            operLog.setApiMethod(req.getType().getName());
            // 保存请求参数
            ForestBody bodyMessage = req.getBody(); // 获取请求体对象
            if (bodyMessage != null) {
                operLog.setOperParam(bodyMessage.encodeToString());
            }

            operLog.setApiCostTime(res.getTimeAsMillisecond());
            if (ex != null) {
                ForestRuntimeException forestRuntimeEX = (ForestRuntimeException) ex;
                operLog.setStatus(BizStatus.FAIL.ordinal());
                operLog.setErrorMsg(StrUtil.sub(forestRuntimeEX.getMessage(), 0, 2000));
            } else {
                // 保存返回值
                String content = res.getContent();
                operLog.setApiStatus(res.getStatusCode());
                operLog.setJsonResult(content);
            }

            // 保存数据库
            eventPublisher.publishEvent(operLog);
        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

}

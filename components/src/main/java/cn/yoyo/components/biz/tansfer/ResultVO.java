package cn.yoyo.components.biz.tansfer;

import cn.yoyo.components.context.ContextManager;
import lombok.Getter;


@Getter
public class ResultVO<T> {
    private final Object traceId = ContextManager.getTraceId();
    private final long timestamp = System.currentTimeMillis();
    /**
     * 状态码，比如1000代表响应成功
     */
    private final int code;
    /**
     * 响应信息，用来说明响应情况
     */
    private final String msg;

    /**
     * 响应的具体数据
     */
    private final T data;

    public ResultVO(T data) {
        this(CoreResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = null;
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }
}

package cn.yoyo.components.biz.adapter.rest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {
    private static final Pattern VERSION_PREFIX_PATTERN = Pattern.compile("v(\\d+\\.\\d+)");
    private static final String X_VERSION = "X-VERSION";

    private final String version;

    public ApiVersionCondition(String version) {
        this.version = version;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiVersionCondition(other.getApiVersion());
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest httpServletRequest) {
        String headerVersion = httpServletRequest.getHeader(X_VERSION);
        if(Objects.equals(version,headerVersion)){
            return this;
        }

        Matcher m = VERSION_PREFIX_PATTERN.matcher(httpServletRequest.getRequestURI());
        if (m.find()) {
            String pathVersion = m.group(1);
            // 这个方法是精确匹配
            if (Objects.equals(pathVersion, version)) {
                return this;
            }
            // 该方法是只要大于等于最低接口version即匹配成功，需要和compareTo()配合
            // 举例：定义有1.0/1.1接口，访问1.2，则实际访问的是1.1，如果从小开始那么排序反转即可
//            if(Float.parseFloat(pathVersion)>=Float.parseFloat(version)){
//                return this;
//            }

        }
        return null;
    }

    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        return 0;
        // 优先匹配最新的版本号，和getMatchingCondition注释掉的代码同步使用
//  return other.getApiVersion().compareTo(this.version);
    }

    public String getApiVersion() {
        return version;
    }

}

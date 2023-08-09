package cn.yoyo.module.sdk.clients.baidu;

import com.dtflys.forest.annotation.ForestClient;
import com.dtflys.forest.annotation.Request;

@ForestClient
public interface BaiduClient {

    @Request(url = "https://baidu.com")
    String index();
}

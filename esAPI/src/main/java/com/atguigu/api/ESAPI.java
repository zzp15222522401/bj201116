package com.atguigu.api;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.IOException;

public class ESAPI {
    public static void main(String[] args) throws IOException {
        //创建Jest的工厂
        JestClientFactory jestClientFactory = new JestClientFactory();
        HttpClientConfig httpClientConfig = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        //连接ES
        jestClientFactory.setHttpClientConfig(httpClientConfig);
        //获取es客户端连接
        JestClient jestClientFactoryObject = jestClientFactory.getObject();
        //TODO 单条插入数据
        Index build = new Index.Builder("{\n" +
                "  \"id\":\"1004\",\n" +
                "  \"name\":\"地下城\",\n" +
                "  \"types\":\"单人,团队,副本,刷图\"\n" +
                "}").index("game_test2020").id("104").type("game").build();
        jestClientFactoryObject.execute(build);


        jestClientFactoryObject.shutdownClient();
    }
}

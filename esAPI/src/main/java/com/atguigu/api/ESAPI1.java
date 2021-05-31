package com.atguigu.api;

import com.atguigu.bean.Game;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.IOException;

public class ESAPI1 {
    public static void main(String[] args) throws IOException {
        JestClientFactory jestClientFactory = new JestClientFactory();
        HttpClientConfig clientConfig = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        jestClientFactory.setHttpClientConfig(clientConfig);
        //TODO 利用bean单条插入数据
        JestClient object = jestClientFactory.getObject();
        Game game = new Game("1005", "穿越火线", "竞技，生化，幽灵，团队，灾变");
        Index build = new Index.Builder(game).index("game_test2021").type("game").id("105").build();
        object.execute(build);

        object.shutdownClient();
    }
}

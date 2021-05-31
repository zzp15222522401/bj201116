package com.atguigu.api;

import com.atguigu.bean.Game;
import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;

import java.io.IOException;

public class ESPut2 {
    public static void main(String[] args) throws IOException {
        JestClientFactory jestClientFactory = new JestClientFactory();
        HttpClientConfig clientConfig = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        jestClientFactory.setHttpClientConfig(clientConfig);
        JestClient object = jestClientFactory.getObject();

        //TODO 多条插入数  Buik类
        Game game1= new Game("1110", "传奇", "复古，经典，高爆");
        Game game2= new Game("1111", "红色警戒", "单机，对抗，益智");
        Game game3= new Game("1112", "CSGO", "枪战，团队，抽奖");


        Index build1 = new Index.Builder(game1).build();
        Index build2 = new Index.Builder(game2).build();
        Index build3 = new Index.Builder(game3).build();
        Bulk build = new Bulk.Builder()
                .defaultIndex("game_test2000")
                .defaultType("game")
                .addAction(build1)
                .addAction(build2)
                .addAction(build3).build();
        object.execute(build);

        object.shutdownClient();
    }
}

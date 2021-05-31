package com.atguigu.flinkgmall.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolAbstract;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
    //使用redis连接池获取连接
    private static JedisPool jedisPool;

    public static Jedis getjedis(){
        if(jedisPool==null){//如果jedis连接不存在那么就创建连接
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100); //最大可用连接数
        jedisPoolConfig.setBlockWhenExhausted(true); //连接耗尽是否等待
        jedisPoolConfig.setMaxWaitMillis(2000); //等待时间

        jedisPoolConfig.setMaxIdle(5); //最大闲置连接数
        jedisPoolConfig.setMinIdle(5); //最小闲置连接数

        jedisPoolConfig.setTestOnBorrow(true); //取连接的时候进行一下测试 ping pong

        jedisPool=new JedisPool(jedisPoolConfig,"hadoop102",6379,10000);
        }
        Jedis resource = jedisPool.getResource();
        return resource;
    }
    /*//测试redis连接
    public static void main(String[] args) {
        Jedis jedis = getjedis();
        String ping = jedis.ping();
        System.out.println(ping);
    }*/
}

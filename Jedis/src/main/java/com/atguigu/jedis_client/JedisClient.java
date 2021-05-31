package com.atguigu.jedis_client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class JedisClient {
    //一般的javaAPI编写分为三步走，连接资源，写主要程序，关闭资源
    private Jedis jedis;
    private JedisPool jedisPool;
    private JedisSentinelPool sentinelPool;//哨兵模式下使用这个
    @Before
    public void befor() {
        /**
         * 数据池连接（也可以直接连接Jedis，但是不如这样省资源）
         */
        if (jedisPool == null) {

            JedisPoolConfig poolConfig = new JedisPoolConfig();
            poolConfig.setMaxTotal(10);
            poolConfig.setMaxIdle(5);
            poolConfig.setMinIdle(4);
            poolConfig.setTestOnBorrow(true);
            poolConfig.setMaxWaitMillis(3000);
            poolConfig.setBlockWhenExhausted(true);

            JedisPool jedisPool = new JedisPool(poolConfig, "hadoop102");
        }
            jedis = jedisPool.getResource();

    }
   /* @Before//下面表示的时哨兵模式下的初始加载(连接池)
    public void sentinelbefor(){
        if(sentinelPool==null){

        Set<String> sentinel=new HashSet<String>();
        sentinel.add("192.168.206.102:26379");

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxIdle(5);
        poolConfig.setMinIdle(4);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setMaxWaitMillis(3000);
        poolConfig.setBlockWhenExhausted(true);
        //不再使用JedisPool，使用SentinelPool
            JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster", sentinel, poolConfig);
        }
            jedis = jedisSentinelPool.getResource();

    }*/
    @After
    public void after(){
        jedis.close();
    }
    @Test
    public void key() throws InterruptedException {
        jedis.flushDB();
        jedis.set("aaa","bbb");
        jedis.set("aab","ccc");
        jedis.set("aac","ddd");
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.get("aaa"));
        System.out.println(jedis.exists("bbb"));
        jedis.del("aaa");
        System.out.println(jedis.keys("*"));
        jedis.setex("aad",10,"aaa");
        System.out.println(jedis.get("aad"));
        Thread.sleep(11000);
        System.out.println(jedis.get("aad"));
    }
    @Test
    public void string(){
        jedis.set("a1","111");
        System.out.println(jedis.get("a1"));
        jedis.append("a1","aaaaa");
        System.out.println(jedis.get("a1"));
        jedis.setnx("a2","222");
        System.out.println(jedis.incr("a2"));
    }
}

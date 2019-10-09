package com.jedis.client.dao;

import redis.clients.jedis.Jedis;

public class JedisSupplier {

    private static Jedis jedis;

    private JedisSupplier(){
        if(jedis != null){
            throw new IllegalStateException("You can use getJedis method instead of calling the constructor");
        }
    }

    public static synchronized Jedis getJedis() {
        if(jedis == null){
            synchronized (JedisSupplier.class){
                jedis = new Jedis();
            }
        }
        return jedis;
    }
}

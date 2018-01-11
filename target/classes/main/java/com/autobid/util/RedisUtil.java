package com.autobid.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SuppressWarnings("deprecation,unused")
public final class RedisUtil {

    //Redis的端口号
    //private static int PORT = 6379;

    //访问密码
    //private static String AUTH = "admin";

    //private static int TIMEOUT = 10000;

    private static JedisPool jedisPool = null;

    /*
      初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
            int MAX_TOTAL = 1024;
            config.setMaxTotal(MAX_TOTAL);
            int MAX_IDLE = 200;
            config.setMaxIdle(MAX_IDLE);
            int MAX_WAIT = 10000;
            config.setMaxWaitMillis(MAX_WAIT);
//            boolean TEST_ON_BORROW = true;
            config.setTestOnBorrow(true);
            //jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
            String ADDR = "localhost";
            jedisPool = new JedisPool(config, ADDR);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     *
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                return jedisPool.getResource();
            } else {
                return null;
                //return jedisPool;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /*
     *
      释放jedis资源
      @param jedis
    */

	public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }
}
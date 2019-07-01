package main;

import redis.clients.jedis.Jedis;

public class Main {
    public static void main(String[] args) {
        // The "rediss" scheme instructs jedis to open a SSL/TLS connection.
        Jedis jedis = new Jedis("rediss://clustercfg.redis-cache-naboo.70w7sj.euw1.cache.amazonaws.com:6379");
        jedis.auth("q95cGujseOmyimoi");
        System.out.println(jedis.ping());
        jedis.close();
    }
}

package main;

import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0)
            throw new IllegalArgumentException("You must provide conf.properties file path");
        Properties props = new Properties();
        props.load(new FileInputStream(args[0]));

        System.setProperty("javax.net.ssl.keyStore", props.getProperty("javax.net.ssl.keyStore"));
        System.setProperty("javax.net.ssl.keyStorePassword", props.getProperty("javax.net.ssl.keyStorePassword"));
        System.setProperty("javax.net.ssl.trustStore", props.getProperty("javax.net.ssl.trustStore"));
        System.setProperty("javax.net.ssl.trustStorePassword", props.getProperty("javax.net.ssl.trustStorePassword"));
        System.setProperty("javax.net.debug", props.getProperty("javax.net.debug"));

        // The "rediss" scheme instructs jedis to open a SSL/TLS connection.
        Jedis jedis = new Jedis("rediss://" + props.getProperty("redis.host") + ":" + props.getProperty("redis.port"));
        jedis.auth(props.getProperty("redis.password"));
        System.out.println(jedis.ping());
        jedis.close();
    }
}

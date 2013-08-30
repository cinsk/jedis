package redis.clients.main;

import java.io.*;
import redis.clients.jedis.*;

//import redis.clients.main.SentinelPool;

class Repl {
    public static void main(String[] args) {
        SentinelPool pool = new SentinelPool(new JedisPoolConfig(), "localhost",
                                          "master0");

        while (true) {
            Jedis jedis = pool.getResource();
            System.out.println("hello world\n");
            try {
                jedis.set("foo", "bar");
                String foobar = jedis.get("foo");
                System.out.println("foo: " + foobar + "\n");
            }
            catch (Exception e) {
                // JedisConnectionException when disconnected.
                // JedisDataException when READONLY slave
                System.out.println(e.getClass() + ": " + e.getMessage());
                ;
            }
            finally {
                pool.returnResource(jedis);
            }

            try {
                Thread.sleep(1000);
            }
            catch (Exception e) {
            }

        }
    }
}

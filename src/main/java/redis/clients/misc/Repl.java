package redis.clients.misc;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.net.URI;
import java.net.URISyntaxException;

import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.*;

//import redis.clients.main.SentinelPool;

class Repl {
    public static void sleep(long msec, String msg) {
        System.err.println("sleep(" + String.valueOf(msec) + "): " + msg);
        try {
            Thread.sleep(msec);
        }
        catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        List<URI> endpoints = new ArrayList<URI>();
        try {
            endpoints.add(new URI("//localhost:6381"));
            endpoints.add(new URI("//localhost:6380"));
            endpoints.add(new URI("//localhost:6379"));
        }
        catch (URISyntaxException e) {
            System.err.println("invalid URI");
        }

        MasterJedis jedis = new MasterJedis(new JedisPoolConfig(), endpoints);
        // MasterPool pool = new MasterPool(new JedisPoolConfig(), endpoints);

        while (true) {
            System.err.println("while loop\n");
            try {
                //jedis.select(10);
                jedis.set("foo", "bar");
                jedis.set("bar", "foo");
                String foobar = jedis.get("foo");
                System.err.println("GET foo: " + foobar);

                try {
                    MasterTransaction tran = jedis.multi();
                    tran.set("foo", "FOO (set by transaction)");
                    tran.set("bar", "BAR (set by transaction)");
                    Response<String> foo = tran.get("foo");
                    Response<String> bar = tran.get("bar");

                    sleep(5000, "before transaction exec");

                    tran.exec();
                    System.err.println("TRAN GET foo: " + foo.get());
                    System.err.println("TRAN GET bar: " + foo.get());
                }
                catch (JedisException e) {
                    // If you try to call get() from Response, when
                    // pipe.sync() returned false, you'll get
                    // JedisDataException.
                    //
                    // Since MasterPipeline automatically release
                    // Jedis, you don't need to handle anything.
                    System.err.println("USER-ERR: TRANS failed: " + e.getClass());
                    e.printStackTrace();
                }

                try {
                    MasterPipeline pipe = jedis.pipelined();
                    Response<String> foo = pipe.get("foo");
                    Response<String> bar = pipe.get("bar");
                    sleep(5000, "before pipeline sync");

                    pipe.sync();

                    System.err.println("PIPE GET foo: " + foo.get());
                    System.err.println("PIPE GET bar: " + foo.get());
                }
                catch (JedisException e) {
                    // If you try to call get() from Response, when
                    // pipe.sync() returned false, you'll get
                    // JedisDataException.
                    //
                    // Since MasterPipeline automatically release
                    // Jedis, you don't need to handle anything.
                    System.err.println("USER-ERR: PIPE failed: " + e.getClass());
                    e.printStackTrace();
                }
            }
            catch (Exception e) {
                // JedisConnectionException when disconnected.
                // JedisDataException when READONLY slave
                System.err.println("USER-ERR: " + e.getClass() + ": " + e.getMessage());
                e.printStackTrace();
            }
            finally {
                // pool.returnResource(jedis);
            }

            sleep(1000, "done iteraction");
        }
    }
}

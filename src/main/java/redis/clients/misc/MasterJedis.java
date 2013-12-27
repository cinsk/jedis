package redis.clients.misc;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;

import redis.clients.util.Pool;
import redis.clients.util.SafeEncoder;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.*;

import java.util.*;
import java.net.URI;


import redis.clients.misc.MasterPool;
//import redis.clients.misc.ChainableTransaction;
//import redis.clients.misc.RoundRobinPool;



public class MasterJedis implements JedisCommands {
    // BinaryJedisCommands {
    private Pool<Jedis> masterPool;

    public MasterJedis(final Config poolConfig, List<URI> endpoints) {
        this.masterPool = new MasterPool(poolConfig, endpoints);
    }

    // TODO: provide overloaded constructors (e.g. for timeout, etc.)

    // String set(byte[] key, byte[] value);
    public String set(final String key, final String value) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.set(key, value);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // byte[] get(byte[] key);
    public String get(final String key) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.get(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    //Boolean exists(byte[] key);
    public Boolean exists(final String key) {
        Jedis j = null;
        Boolean result = null;

        try {
            j = masterPool.getResource();
            result = j.exists(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // String type(byte[] key);
    public String type(final String key) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.type(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long expire(byte[] key, int seconds);
    public Long expire(final String key, final int seconds) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.expire(key, seconds);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long expireAt(byte[] key, long unixTime);
    public Long expireAt(final String key, final long unixTime) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.expireAt(key, unixTime);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long ttl(byte[] key);
    public Long ttl(String key) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.ttl(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    public Boolean setbit(final String key,
                          final long offset, final boolean value) {
        Jedis j = null;
        Boolean result = null;

        try {
            j = masterPool.getResource();
            result = j.setbit(key, offset, value);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    public Boolean getbit(final String key, final long offset) {
        Jedis j = null;
        Boolean result = null;

        try {
            j = masterPool.getResource();
            result = j.getbit(key, offset);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    public Long setrange(String key, long offset, String value) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.setrange(key, offset, value);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    public String getrange(String key, long startOffset, long endOffset) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.getrange(key, startOffset, endOffset);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // byte[] getSet(byte[] key, byte[] value);
    public String getSet(final String key, final String value) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.getSet(key, value);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long setnx(byte[] key, byte[] value);
    public Long setnx(String key, String value) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.setnx(key, value);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // String setex(byte[] key, int seconds, byte[] value);
    public String setex(final String key,
                        final int seconds, final String value) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.setex(key, seconds, value);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long decrBy(byte[] key, long integer);
    public Long decrBy(final String key, final long integer) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.decrBy(key, integer);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long decr(byte[] key);
    public Long decr(final String key) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.decr(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long incrBy(byte[] key, long integer);
    public Long incrBy(final String key, final long integer) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.incrBy(key, integer);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long incr(byte[] key);
    public Long incr(final String key) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.incr(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long append(byte[] key, byte[] value);
    public Long append(final String key, final String value) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.append(key, value);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // byte[] substr(byte[] key, int start, int end);
    public String substr(final String key, final int start, final int end) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.substr(key, start, end);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long hset(byte[] key, byte[] field, byte[] value);
    public Long hset(final String key, final String field, final String value) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.hset(key, field, value);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // byte[] hget(byte[] key, byte[] field);
    public String hget(final String key, final String field) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.hget(key, field);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long hsetnx(byte[] key, byte[] field, byte[] value);
    public Long hsetnx(final String key,
                       final String field, final String value) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.hsetnx(key, field, value);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // String hmset(byte[] key, Map<byte[], byte[]> hash);
    public String hmset(final String key, final Map<String, String> hash) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.hmset(key, hash);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // List<byte[]> hmget(byte[] key, byte[]... fields);
    public List<String> hmget(final String key, final String... fields) {
        Jedis j = null;
        List<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.hmget(key, fields);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    //Long hincrBy(byte[] key, byte[] field, long value);
    public Long hincrBy(final String key,
                        final String field, final long value) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.hincrBy(key, field, value);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Boolean hexists(byte[] key, byte[] field);
    public Boolean hexists(final String key, final String field) {
        Jedis j = null;
        Boolean result = null;

        try {
            j = masterPool.getResource();
            result = j.hexists(key, field);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long hdel(byte[] key, byte[]... field);
    public Long hdel(final String key, final String... field) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.hdel(key, field);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long hlen(byte[] key);
    public Long hlen(final String key) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.hlen(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Set<byte[]> hkeys(byte[] key);
    public Set<String> hkeys(final String key) {
        Jedis j = null;
        Set<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.hkeys(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Collection<byte[]> hvals(byte[] key);
    public List<String> hvals(final String key) {
        Jedis j = null;
        List<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.hvals(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Map<byte[], byte[]> hgetAll(byte[] key);
    public Map<String, String> hgetAll(final String key) {
        Jedis j = null;
        Map<String, String> result = null;

        try {
            j = masterPool.getResource();
            result = j.hgetAll(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long rpush(byte[] key, byte[]... string);
    public Long rpush(final String key, final String... string) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.rpush(key, string);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long lpush(byte[] key, byte[]... string);
    public Long lpush(final String key, final String... string) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.lpush(key, string);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long llen(byte[] key);
    public Long llen(final String key) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.llen(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    //List<byte[]> lrange(byte[] key, int start, int end);
    public List<String> lrange(final String key,
                               final long start, final long end) {
        Jedis j = null;
        List<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.lrange(key, start, end);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // String ltrim(byte[] key, int start, int end);
    public String ltrim(final String key, final long start, final long end) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.ltrim(key, start, end);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // byte[] lindex(byte[] key, int index);
    public String lindex(final String key, final long index) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.lindex(key, index);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // String lset(byte[] key, int index, byte[] value);
    public String lset(final String key, final long index, final String value) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.lset(key, index, value);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long lrem(byte[] key, int count, byte[] value);
    public Long lrem(final String key, final long count, final String value) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.lrem(key, count, value);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // byte[] lpop(byte[] key);
    public String lpop(final String key) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.lpop(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // byte[] rpop(byte[] key);
    public String rpop(final String key) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.rpop(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long sadd(byte[] key, byte[]... member);
    public Long sadd(final String key, final String... member) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.sadd(key, member);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Set<byte[]> smembers(byte[] key);
    public Set<String> smembers(final String key) {
        Jedis j = null;
        Set<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.smembers(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long srem(byte[] key, byte[]... member);
    public Long srem(final String key, final String... member) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.srem(key, member);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // byte[] spop(byte[] key);
    public String spop(final String key) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.spop(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long scard(byte[] key);
    public Long scard(final String key) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.scard(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Boolean sismember(byte[] key, byte[] member);
    public Boolean sismember(final String key, final String member) {
        Jedis j = null;
        Boolean result = null;

        try {
            j = masterPool.getResource();
            result = j.sismember(key, member);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // byte[] srandmember(byte[] key);
    public String srandmember(final String key) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.srandmember(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long zadd(byte[] key, double score, byte[] member);
    public Long zadd(final String key,
                     final double score, final String member) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.zadd(key, score, member);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long zadd(byte[] key, Map<Double, byte[]> scoreMembers);
    public Long zadd(final String key, final Map<Double, String> scoreMembers) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.zadd(key, scoreMembers);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Set<byte[]> zrange(byte[] key, int start, int end);
    public Set<String> zrange(final String key,
                              final long start, final long end) {
        Jedis j = null;
        Set<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrange(key, start, end);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long zrem(byte[] key, byte[]... member);
    public Long zrem(final String key, final String... member) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.zrem(key, member);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Double zincrby(byte[] key, double score, byte[] member);
    public Double zincrby(final String key,
                          final double score, final String member) {
        Jedis j = null;
        Double result = null;

        try {
            j = masterPool.getResource();
            result = j.zincrby(key, score, member);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Long zrank(byte[] key, byte[] member);
    public Long zrank(final String key, final String member) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.zrank(key, member);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long zrevrank(byte[] key, byte[] member);
    public Long zrevrank(final String key, final String member) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.zrevrank(key, member);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Set<byte[]> zrevrange(byte[] key, int start, int end);
    public Set<String> zrevrange(final String key,
                                 final long start, final long end) {
        Jedis j = null;
        Set<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrevrange(key, start, end);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Set<Tuple> zrangeWithScores(byte[] key, int start, int end);
    public Set<Tuple> zrangeWithScores(final String key,
                                       final long start, final long end) {
        Jedis j = null;
        Set<Tuple> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrangeWithScores(key, start, end);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Set<Tuple> zrevrangeWithScores(byte[] key, int start, int end);
    public Set<Tuple> zrevrangeWithScores(final String key,
                                          final long start, final long end) {
        Jedis j = null;
        Set<Tuple> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrevrangeWithScores(key, start, end);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long zcard(byte[] key);
    public Long zcard(final String key) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.zcard(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Double zscore(byte[] key, byte[] member);
    public Double zscore(final String key, final String member) {
        Jedis j = null;
        Double result = null;

        try {
            j = masterPool.getResource();
            result = j.zscore(key, member);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // List<byte[]> sort(byte[] key);
    public List<String> sort(final String key) {
        Jedis j = null;
        List<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.sort(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // List<byte[]> sort(byte[] key, SortingParams sortingParameters);
    public List<String> sort(final String key,
                             final SortingParams sortingParameters) {
        Jedis j = null;
        List<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.sort(key, sortingParameters);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Long zcount(byte[] key, double min, double max);
    public Long zcount(final String key, final double min, final double max) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.zcount(key, min, max);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Long zcount(byte[] key, byte[] min, byte[] max);
    public Long zcount(String key, String min, String max) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.zcount(key, min, max);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Set<byte[]> zrangeByScore(byte[] key, double min, double max);
    public Set<String> zrangeByScore(final String key,
                                     final double min, final double max) {
        Jedis j = null;
        Set<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrangeByScore(key, min, max);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count);
    public Set<String> zrangeByScore(String key, final double min,
                                     final double max, final int offset,
                                     final int count) {
        Jedis j = null;
        Set<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrangeByScore(key, min, max, offset, count);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    public Set<String> zrangeByScore(final String key, final String min,
                                     final String max, final int offset,
                                     final int count) {
        Jedis j = null;
        Set<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrangeByScore(key, min, max, offset, count);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    public Set<String> zrangeByScore(final String key,
                                     final String min, final String max) {
        Jedis j = null;
        Set<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrangeByScore(key, min, max);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max);
    public Set<Tuple> zrangeByScoreWithScores(final String key,
                                              final double min,
                                              final double max) {
        Jedis j = null;
        Set<Tuple> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrangeByScoreWithScores(key, min, max);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    public Set<Tuple> zrangeByScoreWithScores(byte[] key, final double min,
                                              final double max,
                                              final int offset,
                                              final int count) {
        Jedis j = null;
        Set<Tuple> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrangeByScoreWithScores(key, min, max, offset, count);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max);
    public Set<Tuple> zrangeByScoreWithScores(final String key,
                                              final String min,
                                              final String max) {
        Jedis j = null;
        Set<Tuple> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrangeByScoreWithScores(key, min, max);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count);
    public Set<Tuple> zrangeByScoreWithScores(final String key,
                                              final double min,
                                              final double max,
                                              final int offset,
                                              final int count) {
        Jedis j = null;
        Set<Tuple> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrangeByScoreWithScores(key, min, max, offset, count);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    public Set<Tuple> zrangeByScoreWithScores(final String key,
                                              final String min,
                                              final String max,
                                              final int offset,
                                              final int count) {
        Jedis j = null;
        Set<Tuple> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrangeByScoreWithScores(key, min, max, offset, count);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Set<byte[]> zrevrangeByScore(byte[] key, double max, double min);
    public Set<String> zrevrangeByScore(final String key,
                                        final double max, final double min) {
        Jedis j = null;
        Set<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrevrangeByScore(key, max, min);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    public Set<String> zrevrangeByScore(final String key,
                                        final String max, final String min) {
        Jedis j = null;
        Set<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrevrangeByScore(key, max, min);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    //Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count);
    public Set<String> zrevrangeByScore(final String key,
                                        final double max, final double min,
                                        final int offset, final int count) {
        Jedis j = null;
        Set<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrevrangeByScore(key, max, min, offset, count);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    public Set<String> zrevrangeByScore(final String key,
                                        final String max, final String min,
                                        final int offset, final int count) {
        Jedis j = null;
        Set<String> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrevrangeByScore(key, max, min, offset, count);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    //Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min);
    //Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count);

    //Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min);
    public Set<Tuple> zrevrangeByScoreWithScores(final String key,
                                                 final double max,
                                                 final double min) {
        Jedis j = null;
        Set<Tuple> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrevrangeByScoreWithScores(key, max, min);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(final String key,
                                                 final String max,
                                                 final String min) {
        Jedis j = null;
        Set<Tuple> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrevrangeByScoreWithScores(key, max, min);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count);
    public Set<Tuple> zrevrangeByScoreWithScores(final String key,
                                                 final double max,
                                                 final double min,
                                                 final int offset,
                                                 final int count) {
        Jedis j = null;
        Set<Tuple> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrevrangeByScoreWithScores(key, max, min, offset, count);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    public Set<Tuple> zrevrangeByScoreWithScores(final String key,
                                                 final String max,
                                                 final String min,
                                                 final int offset,
                                                 final int count) {
        Jedis j = null;
        Set<Tuple> result = null;

        try {
            j = masterPool.getResource();
            result = j.zrevrangeByScoreWithScores(key, max, min, offset, count);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min);
    // Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count);

    // Long zremrangeByRank(byte[] key, int start, int end);
    public Long zremrangeByRank(final String key,
                                final long start, final long end) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.zremrangeByRank(key, start, end);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Long zremrangeByScore(byte[] key, double start, double end);
    public Long zremrangeByScore(final String key,
                                 final double start, final double end) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.zremrangeByScore(key, start, end);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }


    // Long zremrangeByScore(byte[] key, byte[] start, byte[] end);
    public Long zremrangeByScore(final String key,
                                 final String start, final String end) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.zremrangeByScore(key, start, end);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long linsert(byte[] key, LIST_POSITION where, byte[] pivot, byte[] value);
    public Long linsert(final String key, final Client.LIST_POSITION where,
                        final String pivot, final String value) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.linsert(key, where, pivot, value);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long objectRefcount(byte[] key);
    public Long objectRefcount(String string) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.objectRefcount(string);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    public Long objectIdletime(final String key) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.objectIdletime(key);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // byte[] objectEncoding(byte[] key);
    public String objectEncoding(final String string) {
        Jedis j = null;
        String result = null;

        try {
            j = masterPool.getResource();
            result = j.objectEncoding(string);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long lpushx(byte[] key, byte[] string);
    public Long lpushx(final String key, final String string) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.lpushx(key, string);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // Long rpushx(byte[] key, byte[] string);
    public Long rpushx(final String key, final String string) {
        Jedis j = null;
        Long result = null;

        try {
            j = masterPool.getResource();
            result = j.rpushx(key, string);
            masterPool.returnResource(j);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
        return result;
    }

    // From BinaryJedis
    // public Transaction multi();
    //public List<Object> multi(final TransactionBlock jedisTransaction);

    public MasterTransaction multi() {
        Jedis j = null;
        Transaction result = null;

        try {
            j = masterPool.getResource();
            result = j.multi();
            // masterPool.returnResource(j);
            return new MasterTransaction(masterPool, j, result);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
    }

    public MasterPipeline pipelined() {
        Jedis j = null;
        Pipeline result = null;

        try {
            j = masterPool.getResource();
            result = j.pipelined();
            // masterPool.returnResource(j);
            return new MasterPipeline(masterPool, j, result);
        }
        catch (JedisException e) {
            if (j != null)
                masterPool.returnBrokenResource(j);
            throw e;
        }
    }
}

package redis.clients.misc;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.util.Pool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.*;
import redis.clients.jedis.BinaryClient.LIST_POSITION;

import redis.clients.misc.MasterJedis;

public class MasterPipeline {
    protected Pool<Jedis> pool;
    protected Jedis jedis;
    protected Pipeline pipeline;

    public MasterPipeline(Pool<Jedis> pool, Jedis jedis,
                          Pipeline pipe) {
        this.pool = pool;
        this.jedis = jedis;
        this.pipeline = pipe;
    }

    /**
     * Syncronize pipeline by reading all responses. This operation close the
     * pipeline. In order to get return values from pipelined commands, capture
     * the different Response<?> of the commands you execute.
     */
    public Boolean sync() {
        try {
            this.pipeline.sync();
            this.pool.returnResource(this.jedis);
            return true;
        }
        catch (JedisException e) {
            System.err.println("CATCH PIPELINE: " + e.getClass());
            this.pool.returnBrokenResource(this.jedis);
            throw e;
        }
    }


    /**
     * Syncronize pipeline by reading all responses. This operation close the
     * pipeline. Whenever possible try to avoid using this version and use
     * Pipeline.sync() as it won't go through all the responses and generate the
     * right response type (usually it is a waste of time).
     *
     * @return A list of all the responses in the order you executed them.
     * @see sync
     */
    public List<Object> syncAndReturnAll() {
        List<Object> result = null;

        try {
            result = this.pipeline.syncAndReturnAll();
            this.pool.returnResource(this.jedis);
        }
        catch (JedisException e) {
            System.err.println("CATCH PIPELINE: " + e.getClass());
            this.pool.returnBrokenResource(this.jedis);
        }
        return result;
    }

    public Response<Long> append(String key, String value) {
        return this.pipeline.append(key, value);
    }

    public Response<Long> append(byte[] key, byte[] value) {
        return this.pipeline.append(key, value);
    }

    public Response<List<String>> blpop(String... args) {
        return this.pipeline.blpop(args);
    }

    public Response<List<String>> blpop(byte[]... args) {
        return this.pipeline.blpop(args);
    }

    public Response<List<String>> brpop(String... args) {
        return this.pipeline.brpop(args);
    }

    public Response<List<String>> brpop(byte[]... args) {
        return this.pipeline.brpop(args);
    }

    public Response<Long> decr(String key) {
        return this.pipeline.decr(key);
    }

    public Response<Long> decr(byte[] key) {
        return this.pipeline.decr(key);
    }

    public Response<Long> decrBy(String key, long integer) {
        return this.pipeline.decrBy(key, integer);
    }

    public Response<Long> decrBy(byte[] key, long integer) {
        return this.pipeline.decrBy(key, integer);
    }

    public Response<Long> del(String... keys) {
        return this.pipeline.del(keys);
    }

    public Response<Long> del(byte[]... keys) {
        return this.pipeline.del(keys);
    }

    public Response<String> echo(String string) {
        return this.pipeline.echo(string);
    }

    public Response<String> echo(byte[] string) {
        return this.pipeline.echo(string);
    }

    public Response<Boolean> exists(String key) {
        return this.pipeline.exists(key);
    }

    public Response<Boolean> exists(byte[] key) {
        return this.pipeline.exists(key);
    }

    public Response<Long> expire(String key, int seconds) {
        return this.pipeline.expire(key, seconds);
    }

    public Response<Long> expire(byte[] key, int seconds) {
        return this.pipeline.expire(key, seconds);
    }

    public Response<Long> expireAt(String key, long unixTime) {
        return this.pipeline.expireAt(key, unixTime);
    }

    public Response<Long> expireAt(byte[] key, long unixTime) {
        return this.pipeline.expireAt(key, unixTime);
    }

    public Response<String> get(String key) {
        return this.pipeline.get(key);
    }

    public Response<byte[]> get(byte[] key) {
        return this.pipeline.get(key);
    }

    public Response<Boolean> getbit(String key, long offset) {
        return this.pipeline.getbit(key, offset);
    }

    public Response<String> getrange(String key, long startOffset, long endOffset) {
        return this.pipeline.getrange(key, startOffset, endOffset);
    }

    public Response<String> getSet(String key, String value) {
        return this.pipeline.getSet(key, value);
    }

    public Response<byte[]> getSet(byte[] key, byte[] value) {
        return this.pipeline.getSet(key, value);
    }

    public Response<Long> hdel(String key, String field) {
        return this.pipeline.hdel(key, field);
    }

    public Response<Long> hdel(byte[] key, byte[] field) {
        return this.pipeline.hdel(key, field);
    }

    public Response<Boolean> hexists(String key, String field) {
        return this.pipeline.hexists(key, field);
    }

    public Response<Boolean> hexists(byte[] key, byte[] field) {
        return this.pipeline.hexists(key, field);
    }

    public Response<String> hget(String key, String field) {
        return this.pipeline.hget(key, field);
    }

    public Response<String> hget(byte[] key, byte[] field) {
        return this.pipeline.hget(key, field);
    }

    public Response<Map<String, String>> hgetAll(String key) {
        return this.pipeline.hgetAll(key);
    }

    public Response<Map<String, String>> hgetAll(byte[] key) {
        return this.pipeline.hgetAll(key);
    }

    public Response<Long> hincrBy(String key, String field, long value) {
        return this.pipeline.hincrBy(key, field, value);
    }

    public Response<Long> hincrBy(byte[] key, byte[] field, long value) {
        return this.pipeline.hincrBy(key, field, value);
    }

    public Response<Set<String>> hkeys(String key) {
        return this.pipeline.hkeys(key);
    }

    public Response<Set<String>> hkeys(byte[] key) {
        return this.pipeline.hkeys(key);
    }

    public Response<Long> hlen(String key) {
        return this.pipeline.hlen(key);
    }

    public Response<Long> hlen(byte[] key) {
        return this.pipeline.hlen(key);
    }

    public Response<List<String>> hmget(String key, String... fields) {
        return this.pipeline.hmget(key, fields);
    }

    public Response<List<String>> hmget(byte[] key, byte[]... fields) {
        return this.pipeline.hmget(key, fields);
    }

    public Response<String> hmset(String key, Map<String, String> hash) {
        return this.pipeline.hmset(key, hash);
    }

    public Response<String> hmset(byte[] key, Map<byte[], byte[]> hash) {
        return this.pipeline.hmset(key, hash);
    }

    public Response<Long> hset(String key, String field, String value) {
        return this.pipeline.hset(key, field, value);
    }

    public Response<Long> hset(byte[] key, byte[] field, byte[] value) {
        return this.pipeline.hset(key, field, value);
    }

    public Response<Long> hsetnx(String key, String field, String value) {
        return this.pipeline.hsetnx(key, field, value);
    }

    public Response<Long> hsetnx(byte[] key, byte[] field, byte[] value) {
        return this.pipeline.hsetnx(key, field, value);
    }

    public Response<List<String>> hvals(String key) {
        return this.pipeline.hvals(key);
    }

    public Response<List<String>> hvals(byte[] key) {
        return this.pipeline.hvals(key);
    }

    public Response<Long> incr(String key) {
        return this.pipeline.incr(key);
    }

    public Response<Long> incr(byte[] key) {
        return this.pipeline.incr(key);
    }

    public Response<Long> incrBy(String key, long integer) {
        return this.pipeline.incrBy(key, integer);
    }

    public Response<Long> incrBy(byte[] key, long integer) {
        return this.pipeline.incrBy(key, integer);
    }

    public Response<Set<String>> keys(String pattern) {
        return this.pipeline.keys(pattern);
    }

    public Response<Set<String>> keys(byte[] pattern) {
        return this.pipeline.keys(pattern);
    }

    public Response<String> lindex(String key, int index) {
        return this.pipeline.lindex(key, index);
    }

    public Response<String> lindex(byte[] key, int index) {
        return this.pipeline.lindex(key, index);
    }

    public Response<Long> linsert(String key, LIST_POSITION where, String pivot, String value) {
        return this.pipeline.linsert(key, where, pivot, value);
    }

    public Response<Long> linsert(byte[] key, LIST_POSITION where, byte[] pivot, byte[] value) {
        return this.pipeline.linsert(key, where, pivot, value);
    }

    public Response<Long> llen(String key) {
        return this.pipeline.llen(key);
    }

    public Response<Long> llen(byte[] key) {
        return this.pipeline.llen(key);
    }

    public Response<String> lpop(String key) {
        return this.pipeline.lpop(key);
    }

    public Response<String> lpop(byte[] key) {
        return this.pipeline.lpop(key);
    }

    public Response<Long> lpush(String key, String string) {
        return this.pipeline.lpush(key, string);
    }

    public Response<Long> lpush(byte[] key, byte[] string) {
        return this.pipeline.lpush(key, string);
    }

    public Response<Long> lpushx(String key, String string) {
        return this.pipeline.lpushx(key, string);
    }

    public Response<Long> lpushx(byte[] key, byte[] bytes) {
        return this.pipeline.lpushx(key, bytes);
    }

    public Response<List<String>> lrange(String key, long start, long end) {
        return this.pipeline.lrange(key, start, end);
    }

    public Response<List<String>> lrange(byte[] key, long start, long end) {
        return this.pipeline.lrange(key, start, end);
    }

    public Response<Long> lrem(String key, long count, String value) {
        return this.pipeline.lrem(key, count, value);
    }

    public Response<Long> lrem(byte[] key, long count, byte[] value) {
        return this.pipeline.lrem(key, count, value);
    }

    public Response<String> lset(String key, long index, String value) {
        return this.pipeline.lset(key, index, value);
    }

    public Response<String> lset(byte[] key, long index, byte[] value) {
        return this.pipeline.lset(key, index, value);
    }

    public Response<String> ltrim(String key, long start, long end) {
        return this.pipeline.ltrim(key, start, end);
    }

    public Response<String> ltrim(byte[] key, long start, long end) {
        return this.pipeline.ltrim(key, start, end);
    }

    public Response<List<String>> mget(String... keys) {
        return this.pipeline.mget(keys);
    }

    public Response<List<String>> mget(byte[]... keys) {
        return this.pipeline.mget(keys);
    }

    public Response<Long> move(String key, int dbIndex) {
        return this.pipeline.move(key, dbIndex);
    }

    public Response<Long> move(byte[] key, int dbIndex) {
        return this.pipeline.move(key, dbIndex);
    }

    public Response<String> mset(String... keysvalues) {
        return this.pipeline.mset(keysvalues);
    }

    public Response<String> mset(byte[]... keysvalues) {
        return this.pipeline.mset(keysvalues);
    }

    public Response<Long> msetnx(String... keysvalues) {
        return this.pipeline.msetnx(keysvalues);
    }

    public Response<Long> msetnx(byte[]... keysvalues) {
        return this.pipeline.msetnx(keysvalues);
    }

    public Response<Long> persist(String key) {
        return this.pipeline.persist(key);
    }

    public Response<Long> persist(byte[] key) {
        return this.pipeline.persist(key);
    }

    public Response<String> rename(String oldkey, String newkey) {
        return this.pipeline.rename(oldkey, newkey);
    }

    public Response<String> rename(byte[] oldkey, byte[] newkey) {
        return this.pipeline.rename(oldkey, newkey);
    }

    public Response<Long> renamenx(String oldkey, String newkey) {
        return this.pipeline.renamenx(oldkey, newkey);
    }

    public Response<Long> renamenx(byte[] oldkey, byte[] newkey) {
        return this.pipeline.renamenx(oldkey, newkey);
    }

    public Response<String> rpop(String key) {
        return this.pipeline.rpop(key);
    }

    public Response<String> rpop(byte[] key) {
        return this.pipeline.rpop(key);
    }

    public Response<String> rpoplpush(String srckey, String dstkey) {
        return this.pipeline.rpoplpush(srckey, dstkey);
    }

    public Response<String> rpoplpush(byte[] srckey, byte[] dstkey) {
        return this.pipeline.rpoplpush(srckey, dstkey);
    }

    public Response<Long> rpush(String key, String string) {
        return this.pipeline.rpush(key, string);
    }

    public Response<Long> rpush(byte[] key, byte[] string) {
        return this.pipeline.rpush(key, string);
    }

    public Response<Long> rpushx(String key, String string) {
        return this.pipeline.rpushx(key, string);
    }

    public Response<Long> rpushx(byte[] key, byte[] string) {
        return this.pipeline.rpushx(key, string);
    }

    public Response<Long> sadd(String key, String member) {
        return this.pipeline.sadd(key, member);
    }

    public Response<Long> sadd(byte[] key, byte[] member) {
        return this.pipeline.sadd(key, member);
    }

    public Response<Long> scard(String key) {
        return this.pipeline.scard(key);
    }

    public Response<Long> scard(byte[] key) {
        return this.pipeline.scard(key);
    }

    public Response<Set<String>> sdiff(String... keys) {
        return this.pipeline.sdiff(keys);
    }

    public Response<Set<String>> sdiff(byte[]... keys) {
        return this.pipeline.sdiff(keys);
    }

    public Response<Long> sdiffstore(String dstkey, String... keys) {
        return this.pipeline.sdiffstore(dstkey, keys);
    }

    public Response<Long> sdiffstore(byte[] dstkey, byte[]... keys) {
        return this.pipeline.sdiffstore(dstkey, keys);
    }

    public Response<String> set(String key, String value) {
        return this.pipeline.set(key, value);
    }

    public Response<String> set(byte[] key, byte[] value) {
        return this.pipeline.set(key, value);
    }

    public Response<Boolean> setbit(String key, long offset, boolean value) {
        return this.pipeline.setbit(key, offset, value);
    }

    public Response<String> setex(String key, int seconds, String value) {
        return this.pipeline.setex(key, seconds, value);
    }

    public Response<String> setex(byte[] key, int seconds, byte[] value) {
        return this.pipeline.setex(key, seconds, value);
    }

    public Response<Long> setnx(String key, String value) {
        return this.pipeline.setnx(key, value);
    }

    public Response<Long> setnx(byte[] key, byte[] value) {
        return this.pipeline.setnx(key, value);
    }

    public Response<Long> setrange(String key, long offset, String value) {
        return this.pipeline.setrange(key, offset, value);
    }

    public Response<Set<String>> sinter(String... keys) {
        return this.pipeline.sinter(keys);
    }

    public Response<Set<String>> sinter(byte[]... keys) {
        return this.pipeline.sinter(keys);
    }

    public Response<Long> sinterstore(String dstkey, String... keys) {
        return this.pipeline.sinterstore(dstkey, keys);
    }

    public Response<Long> sinterstore(byte[] dstkey, byte[]... keys) {
        return this.pipeline.sinterstore(dstkey, keys);
    }

    public Response<Boolean> sismember(String key, String member) {
        return this.pipeline.sismember(key, member);
    }

    public Response<Boolean> sismember(byte[] key, byte[] member) {
        return this.pipeline.sismember(key, member);
    }

    public Response<Set<String>> smembers(String key) {
        return this.pipeline.smembers(key);
    }

    public Response<Set<String>> smembers(byte[] key) {
        return this.pipeline.smembers(key);
    }

    public Response<Long> smove(String srckey, String dstkey, String member) {
        return this.pipeline.smove(srckey, dstkey, member);
    }

    public Response<Long> smove(byte[] srckey, byte[] dstkey, byte[] member) {
        return this.pipeline.smove(srckey, dstkey, member);
    }

    public Response<Long> sort(String key) {
        return this.pipeline.sort(key);
    }

    public Response<Long> sort(byte[] key) {
        return this.pipeline.sort(key);
    }

    public Response<List<String>> sort(String key, SortingParams sortingParameters) {
        return this.pipeline.sort(key, sortingParameters);
    }

    public Response<List<String>> sort(byte[] key, SortingParams sortingParameters) {
        return this.pipeline.sort(key, sortingParameters);
    }

    public Response<List<String>> sort(String key, SortingParams sortingParameters, String dstkey) {
        return this.pipeline.sort(key, sortingParameters, dstkey);
    }

    public Response<List<String>> sort(byte[] key, SortingParams sortingParameters, byte[] dstkey) {
        return this.pipeline.sort(key, sortingParameters, dstkey);
    }

    public Response<List<String>> sort(String key, String dstkey) {
        return this.pipeline.sort(key, dstkey);
    }

    public Response<List<String>> sort(byte[] key, byte[] dstkey) {
        return this.pipeline.sort(key, dstkey);
    }

    public Response<String> spop(String key) {
        return this.pipeline.spop(key);
    }

    public Response<String> spop(byte[] key) {
        return this.pipeline.spop(key);
    }

    public Response<String> srandmember(String key) {
        return this.pipeline.srandmember(key);
    }

    public Response<String> srandmember(byte[] key) {
        return this.pipeline.srandmember(key);
    }

    public Response<Long> srem(String key, String member) {
        return this.pipeline.srem(key, member);
    }

    public Response<Long> srem(byte[] key, byte[] member) {
        return this.pipeline.srem(key, member);
    }

    public Response<Long> strlen(String key) {
        return this.pipeline.strlen(key);
    }

    public Response<Long> strlen(byte[] key) {
        return this.pipeline.strlen(key);
    }

    public Response<String> substr(String key, int start, int end) {
        return this.pipeline.substr(key, start, end);
    }

    public Response<String> substr(byte[] key, int start, int end) {
        return this.pipeline.substr(key, start, end);
    }

    public Response<Set<String>> sunion(String... keys) {
        return this.pipeline.sunion(keys);
    }

    public Response<Set<String>> sunion(byte[]... keys) {
        return this.pipeline.sunion(keys);
    }

    public Response<Long> sunionstore(String dstkey, String... keys) {
        return this.pipeline.sunionstore(dstkey, keys);
    }

    public Response<Long> sunionstore(byte[] dstkey, byte[]... keys) {
        return this.pipeline.sunionstore(dstkey, keys);
    }

    public Response<Long> ttl(String key) {
        return this.pipeline.ttl(key);
    }

    public Response<Long> ttl(byte[] key) {
        return this.pipeline.ttl(key);
    }

    public Response<String> type(String key) {
        return this.pipeline.type(key);
    }

    public Response<String> type(byte[] key) {
        return this.pipeline.type(key);
    }

    public Response<String> watch(String... keys) {
        return this.pipeline.watch(keys);
    }

    public Response<String> watch(byte[]... keys) {
        return this.pipeline.watch(keys);
    }

    public Response<Long> zadd(String key, double score, String member) {
        return this.pipeline.zadd(key, score, member);
    }

    public Response<Long> zadd(byte[] key, double score, byte[] member) {
        return this.pipeline.zadd(key, score, member);
    }

    public Response<Long> zcard(String key) {
        return this.pipeline.zcard(key);
    }

    public Response<Long> zcard(byte[] key) {
        return this.pipeline.zcard(key);
    }

    public Response<Long> zcount(String key, double min, double max) {
        return this.pipeline.zcount(key, min, max);
    }

    public Response<Long> zcount(byte[] key, double min, double max) {
        return this.pipeline.zcount(key, min, max);
    }

    public Response<Double> zincrby(String key, double score, String member) {
        return this.pipeline.zincrby(key, score, member);
    }

    public Response<Double> zincrby(byte[] key, double score, byte[] member) {
        return this.pipeline.zincrby(key, score, member);
    }

    public Response<Long> zinterstore(String dstkey, String... sets) {
        return this.pipeline.zinterstore(dstkey, sets);
    }

    public Response<Long> zinterstore(byte[] dstkey, byte[]... sets) {
        return this.pipeline.zinterstore(dstkey, sets);
    }

    public Response<Long> zinterstore(String dstkey, ZParams params, String... sets) {
        return this.pipeline.zinterstore(dstkey, params, sets);
    }

    public Response<Long> zinterstore(byte[] dstkey, ZParams params, byte[]... sets) {
        return this.pipeline.zinterstore(dstkey, params, sets);
    }

    public Response<Set<String>> zrange(String key, int start, int end) {
        return this.pipeline.zrange(key, start, end);
    }

    public Response<Set<String>> zrange(byte[] key, int start, int end) {
        return this.pipeline.zrange(key, start, end);
    }

    public Response<Set<String>> zrangeByScore(String key, double min, double max) {
        return this.pipeline.zrangeByScore(key, min, max);
    }

    public Response<Set<String>> zrangeByScore(byte[] key, double min, double max) {
        return this.pipeline.zrangeByScore(key, min, max);
    }

    public Response<Set<String>> zrangeByScore(String key, String min, String max) {
        return this.pipeline.zrangeByScore(key, min, max);
    }

    public Response<Set<String>> zrangeByScore(byte[] key, byte[] min, byte[] max) {
        return this.pipeline.zrangeByScore(key, min, max);
    }

    public Response<Set<String>> zrangeByScore(String key, double min, double max, int offset, int count) {
        return this.pipeline.zrangeByScore(key, min, max, offset, count);
    }

    public Response<Set<String>> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
        return this.pipeline.zrangeByScore(key, min, max, offset, count);
    }

    public Response<Set<String>> zrangeByScore(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return this.pipeline.zrangeByScore(key, min, max, offset, count);
    }

    public Response<Set<Tuple>> zrangeByScoreWithScores(String key, double min, double max) {
        return this.pipeline.zrangeByScoreWithScores(key, min, max);
    }

    public Response<Set<Tuple>> zrangeByScoreWithScores(byte[] key, double min, double max) {
        return this.pipeline.zrangeByScoreWithScores(key, min, max);
    }

    public Response<Set<Tuple>> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max) {
        return this.pipeline.zrangeByScoreWithScores(key, min, max);
    }

    public Response<Set<Tuple>> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
        return this.pipeline.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    public Response<Set<Tuple>> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
        return this.pipeline.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    public Response<Set<Tuple>> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count) {
        return this.pipeline.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    public Response<Set<String>> zrevrangeByScore(String key, double max, double min) {
        return this.pipeline.zrevrangeByScore(key, max, min);
    }

    public Response<Set<String>> zrevrangeByScore(byte[] key, double max, double min) {
        return this.pipeline.zrevrangeByScore(key, max, min);
    }

    public Response<Set<String>> zrevrangeByScore(String key, String max, String min) {
        return this.pipeline.zrevrangeByScore(key, max, min);
    }

    public Response<Set<String>> zrevrangeByScore(byte[] key, byte[] max, byte[] min) {
        return this.pipeline.zrevrangeByScore(key, max, min);
    }

    public Response<Set<String>> zrevrangeByScore(String key, double max, double min, int offset, int count) {
        return this.pipeline.zrevrangeByScore(key, max, min, offset, count);
    }

    public Response<Set<String>> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
        return this.pipeline.zrevrangeByScore(key, max, min, offset, count);
    }

    public Response<Set<String>> zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return this.pipeline.zrevrangeByScore(key, max, min, offset, count);
    }

    public Response<Set<Tuple>> zrevrangeByScoreWithScores(String key, double max, double min) {
        return this.pipeline.zrevrangeByScoreWithScores(key, max, min);
    }

    public Response<Set<Tuple>> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
        return this.pipeline.zrevrangeByScoreWithScores(key, max, min);
    }

    public Response<Set<Tuple>> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min) {
        return this.pipeline.zrevrangeByScoreWithScores(key, max, min);
    }

    public Response<Set<Tuple>> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
        return this.pipeline.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    public Response<Set<Tuple>> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
        return this.pipeline.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    public Response<Set<Tuple>> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count) {
        return this.pipeline.zrevrangeByScoreWithScores(key, max, min, offset, count);
    }

    public Response<Set<Tuple>> zrangeWithScores(String key, int start, int end) {
        return this.pipeline.zrangeWithScores(key, start, end);
    }

    public Response<Set<Tuple>> zrangeWithScores(byte[] key, int start, int end) {
        return this.pipeline.zrangeWithScores(key, start, end);
    }

    public Response<Long> zrank(String key, String member) {
        return this.pipeline.zrank(key, member);
    }

    public Response<Long> zrank(byte[] key, byte[] member) {
        return this.pipeline.zrank(key, member);
    }

    public Response<Long> zrem(String key, String member) {
        return this.pipeline.zrem(key, member);
    }

    public Response<Long> zrem(byte[] key, byte[] member) {
        return this.pipeline.zrem(key, member);
    }

    public Response<Long> zremrangeByRank(String key, int start, int end) {
        return this.pipeline.zremrangeByRank(key, start, end);
    }

    public Response<Long> zremrangeByRank(byte[] key, int start, int end) {
        return this.pipeline.zremrangeByRank(key, start, end);
    }

    public Response<Long> zremrangeByScore(String key, double start, double end) {
        return this.pipeline.zremrangeByScore(key, start, end);
    }

    public Response<Long> zremrangeByScore(byte[] key, double start, double end) {
        return this.pipeline.zremrangeByScore(key, start, end);
    }

    public Response<Long> zremrangeByScore(byte[] key, byte[] start, byte[] end) {
        return this.pipeline.zremrangeByScore(key, start, end);
    }

    public Response<Set<String>> zrevrange(String key, int start, int end) {
        return this.pipeline.zrevrange(key, start, end);
    }

    public Response<Set<String>> zrevrange(byte[] key, int start, int end) {
        return this.pipeline.zrevrange(key, start, end);
    }

    public Response<Set<Tuple>> zrevrangeWithScores(String key, int start, int end) {
        return this.pipeline.zrevrangeWithScores(key, start, end);
    }

    public Response<Set<Tuple>> zrevrangeWithScores(byte[] key, int start, int end) {
        return this.pipeline.zrevrangeWithScores(key, start, end);
    }

    public Response<Long> zrevrank(String key, String member) {
        return this.pipeline.zrevrank(key, member);
    }

    public Response<Long> zrevrank(byte[] key, byte[] member) {
        return this.pipeline.zrevrank(key, member);
    }

    public Response<Double> zscore(String key, String member) {
        return this.pipeline.zscore(key, member);
    }

    public Response<Double> zscore(byte[] key, byte[] member) {
        return this.pipeline.zscore(key, member);
    }

    public Response<Long> zunionstore(String dstkey, String... sets) {
        return this.pipeline.zunionstore(dstkey, sets);
    }

    public Response<Long> zunionstore(byte[] dstkey, byte[]... sets) {
        return this.pipeline.zunionstore(dstkey, sets);
    }

    public Response<Long> zunionstore(String dstkey, ZParams params, String... sets) {
        return this.pipeline.zunionstore(dstkey, params, sets);
    }

    public Response<Long> zunionstore(byte[] dstkey, ZParams params, byte[]... sets) {
        return this.pipeline.zunionstore(dstkey, params, sets);
    }

    public Response<String> bgrewriteaof() {
        return this.pipeline.bgrewriteaof();
    }

    public Response<String> bgsave() {
        return this.pipeline.bgsave();
    }

    public Response<String> configGet(String pattern) {
        return this.pipeline.configGet(pattern);
    }

    public Response<String> configSet(String parameter, String value) {
        return this.pipeline.configSet(parameter, value);
    }

    public Response<String> brpoplpush(String source, String destination, int timeout) {
        return this.pipeline.brpoplpush(source, destination, timeout);
    }

    public Response<String> brpoplpush(byte[] source, byte[] destination, int timeout) {
        return this.pipeline.brpoplpush(source, destination, timeout);
    }

    public Response<String> configResetStat() {
        return this.pipeline.configResetStat();
    }

    public Response<String> save() {
        return this.pipeline.save();
    }

    public Response<Long> lastsave() {
        return this.pipeline.lastsave();
    }

    public Response<String> discard() {
        return this.pipeline.discard();
    }

    public Response<List<Object>> exec() {
        return this.pipeline.exec();
    }

    public void multi() {
        this.pipeline.multi();
    }

    public Response<Long> publish(String channel, String message) {
        return this.pipeline.publish(channel, message);
    }

    public Response<Long> publish(byte[] channel, byte[] message) {
        return this.pipeline.publish(channel, message);
    }

    public Response<String> flushDB() {
        return this.pipeline.flushDB();
    }

    public Response<String> flushAll() {
        return this.pipeline.flushAll();
    }

    public Response<String> info() {
        return this.pipeline.info();
    }

    public Response<Long> dbSize() {
        return this.pipeline.dbSize();
    }

    public Response<String> shutdown() {
        return this.pipeline.shutdown();
    }

    public Response<String> ping() {
        return this.pipeline.ping();
    }

    public Response<String> randomKey() {
        return this.pipeline.randomKey();
    }

    public Response<String> select(int index) {
        return this.pipeline.select(index);
    }
}

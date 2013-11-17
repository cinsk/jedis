package redis.clients.misc;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.util.Pool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Response;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.Tuple;
import redis.clients.jedis.exceptions.*;
import redis.clients.jedis.BinaryClient.LIST_POSITION;

import redis.clients.misc.MasterJedis;

public class MasterTransaction {
    protected Pool<Jedis> pool;
    protected Jedis jedis;
    protected Transaction transaction;

    public MasterTransaction(Pool<Jedis> pool, Jedis jedis,
                             Transaction trans) {
        this.pool = pool;
        this.jedis = jedis;
        this.transaction = trans;
    }

    public List<Object> exec() {
        List<Object> result = null;
        try {
            result = this.transaction.exec();
            this.pool.returnResource(this.jedis);
        }
        catch (JedisException e) {
            System.err.println("CATCH EXEC: " + e.getClass());
            this.pool.returnBrokenResource(this.jedis);
            throw e;
        }
        return result;
    }

    public Response<Long> append(String key, String value) {
        return this.transaction.append(key, value);
    }

    public Response<List<String>> blpop(String... args) {
        return this.transaction.blpop(args);
    }

    public Response<List<String>> brpop(String... args) {
        return this.transaction.brpop(args);
    }

    public Response<Long> decr(String key) {
        return this.transaction.decr(key);
    }

    public Response<Long> decrBy(String key, long integer) {
        return this.transaction.decrBy(key, integer);
    }

    public Response<Long> del(String... keys) {
        return this.transaction.del(keys);
    }

    public Response<String> echo(String string) {
        return this.transaction.echo(string);
    }

    public Response<Boolean> exists(String key) {
        return this.transaction.exists(key);
    }

    public Response<Long> expire(String key, int seconds) {
        return this.transaction.expire(key, seconds);
    }

    public Response<Long> expireAt(String key, long unixTime) {
        return this.transaction.expireAt(key, unixTime);
    }

    public Response<String> get(String key) {
        return this.transaction.get(key);
    }

    public Response<Boolean> getbit(String key, long offset) {
        return this.transaction.getbit(key, offset);
    }

    public Response<String> getrange(String key, long startOffset,
            long endOffset) {
        return this.transaction.getrange(key, startOffset, endOffset);
    }

    public Response<String> getSet(String key, String value) {
        return this.transaction.getSet(key, value);
    }

    public Response<Long> hdel(String key, String field) {
        return this.transaction.hdel(key, field);
    }

    public Response<Boolean> hexists(String key, String field) {
        return this.transaction.hexists(key, field);
    }

    public Response<String> hget(String key, String field) {
        return this.transaction.hget(key, field);
    }

    public Response<Map<String, String>> hgetAll(String key) {
        return this.transaction.hgetAll(key);
    }

    public Response<Long> hincrBy(String key, String field, long value) {
        return this.transaction.hincrBy(key, field, value);
    }

    public Response<Set<String>> hkeys(String key) {
        return this.transaction.hkeys(key);
    }

    public Response<Long> hlen(String key) {
        return this.transaction.hlen(key);
    }

    public Response<List<String>> hmget(String key, String... fields) {
        return this.transaction.hmget(key, fields);
    }

    public Response<String> hmset(String key, Map<String, String> hash) {
        return this.transaction.hmset(key, hash);
    }

    public Response<Long> hset(String key, String field, String value) {
        return this.transaction.hset(key, field, value);
    }

    public Response<Long> hsetnx(String key, String field, String value) {
        return this.transaction.hsetnx(key, field, value);
    }

    public Response<List<String>> hvals(String key) {
        return this.transaction.hvals(key);
    }

    public Response<Long> incr(String key) {
        return this.transaction.incr(key);
    }

    public Response<Long> incrBy(String key, long integer) {
        return this.transaction.incrBy(key, integer);
    }

    public Response<Set<String>> keys(String pattern) {
        return this.transaction.keys(pattern);
    }

    public Response<String> lindex(String key, int index) {
        return this.transaction.lindex(key, index);
    }

    public Response<Long> linsert(String key, LIST_POSITION where,
                                  String pivot, String value) {
        return this.transaction.linsert(key, where, pivot, value);
    }

    public Response<Long> llen(String key) {
        return this.transaction.llen(key);
    }

    public Response<String> lpop(String key) {
        return this.transaction.lpop(key);
    }

    public Response<Long> lpush(String key, String string) {
        return this.transaction.lpush(key, string);
    }

    public Response<Long> lpushx(String key, String string) {
        return this.transaction.lpushx(key, string);
    }

    public Response<List<String>> lrange(String key, long start, long end) {
        return this.transaction.lrange(key, start, end);
    }

    public Response<Long> lrem(String key, long count, String value) {
        return this.transaction.lrem(key, count, value);
    }

    public Response<String> lset(String key, long index, String value) {
        return this.transaction.lset(key, index, value);
    }

    public Response<String> ltrim(String key, long start, long end) {
        return this.transaction.ltrim(key, start, end);
    }

    public Response<List<String>> mget(String... keys) {
        return this.transaction.mget(keys);
    }

    public Response<Long> move(String key, int dbIndex) {
        return this.transaction.move(key, dbIndex);
    }

    public Response<String> mset(String... keysvalues) {
        return this.transaction.mset(keysvalues);
    }

    public Response<Long> msetnx(String... keysvalues) {
        return this.transaction.msetnx(keysvalues);
    }

    public Response<Long> persist(String key) {
        return this.transaction.persist(key);
    }

    public Response<String> rename(String oldkey, String newkey) {
        return this.transaction.rename(oldkey, newkey);
    }

    public Response<Long> renamenx(String oldkey, String newkey) {
        return this.transaction.renamenx(oldkey, newkey);
    }

    public Response<String> rpop(String key) {
        return this.transaction.rpop(key);
    }

    public Response<String> rpoplpush(String srckey, String dstkey) {
        return this.transaction.rpoplpush(srckey, dstkey);
    }

    public Response<Long> rpush(String key, String string) {
        return this.transaction.rpush(key, string);
    }

    public Response<Long> rpushx(String key, String string) {
        return this.transaction.rpushx(key, string);
    }

    public Response<Long> sadd(String key, String member) {
        return this.transaction.sadd(key, member);
    }

    public Response<Long> scard(String key) {
        return this.transaction.scard(key);
    }

    public Response<Set<String>> sdiff(String... keys) {
        return this.transaction.sdiff(keys);
    }

    public Response<Long> sdiffstore(String dstkey, String... keys) {
        return this.transaction.sdiffstore(dstkey, keys);
    }

    public Response<String> set(String key, String value) {
        return this.transaction.set(key, value);
    }

    public Response<Boolean> setbit(String key, long offset, boolean value) {
        return this.transaction.setbit(key, offset, value);
    }

    public Response<String> setex(String key, int seconds, String value) {
        return this.transaction.setex(key, seconds, value);
    }

    public Response<Long> setnx(String key, String value) {
        return this.transaction.setnx(key, value);
    }

    public Response<Long> setrange(String key, long offset, String value) {
        return this.transaction.setrange(key, offset, value);
    }

    public Response<Set<String>> sinter(String... keys) {
        return this.transaction.sinter(keys);
    }

    public Response<Long> sinterstore(String dstkey, String... keys) {
        return this.transaction.sinterstore(dstkey, keys);
    }

    public Response<Boolean> sismember(String key, String member) {
        return this.transaction.sismember(key, member);
    }

    public Response<Set<String>> smembers(String key) {
        return this.transaction.smembers(key);
    }

    public Response<Long> smove(String srckey, String dstkey, String member) {
        return this.transaction.smove(srckey, dstkey, member);
    }

    public Response<List<String>> sort(String key) {
        return this.transaction.sort(key);
    }

    public Response<List<String>> sort(String key,
            SortingParams sortingParameters) {
        return this.transaction.sort(key, sortingParameters);
    }

    public Response<List<String>> sort(String key,
            SortingParams sortingParameters, String dstkey) {
        return this.transaction.sort(key, sortingParameters, dstkey);
    }

    public Response<List<String>> sort(String key, String dstkey) {
        return this.transaction.sort(key, dstkey);
    }

    public Response<String> spop(String key) {
        return this.transaction.spop(key);
    }

    public Response<String> srandmember(String key) {
        return this.transaction.srandmember(key);
    }

    public Response<Long> srem(String key, String member) {
        return this.transaction.srem(key, member);
    }

    public Response<Long> strlen(String key) {
        return this.transaction.strlen(key);
    }

    public Response<String> substr(String key, int start, int end) {
        return this.transaction.substr(key, start, end);
    }

    public Response<Set<String>> sunion(String... keys) {
        return this.transaction.sunion(keys);
    }

    public Response<Long> sunionstore(String dstkey, String... keys) {
        return this.transaction.sunionstore(dstkey, keys);
    }

    public Response<Long> ttl(String key) {
        return this.transaction.ttl(key);
    }

    public Response<String> type(String key) {
        return this.transaction.type(key);
    }

    public Response<Long> zadd(String key, double score, String member) {
        return this.transaction.zadd(key, score, member);
    }

    public Response<Long> zcard(String key) {
        return this.transaction.zcard(key);
    }

    public Response<Long> zcount(String key, double min, double max) {
        return this.transaction.zcount(key, min, max);
    }

    public Response<Double> zincrby(String key, double score, String member) {
        return this.transaction.zincrby(key, score, member);
    }

    public Response<Long> zinterstore(String dstkey, String... sets) {
        return this.transaction.zinterstore(dstkey, sets);
    }

    public Response<Long> zinterstore(String dstkey, ZParams params,
            String... sets) {
        return this.transaction.zinterstore(dstkey, params, sets);
    }

    public Response<Set<String>> zrange(String key, int start, int end) {
        return this.transaction.zrange(key, start, end);
    }

    public Response<Set<String>> zrangeByScore(String key, double min,
            double max) {
        return this.transaction.zrangeByScore(key, min, max);
    }

    public Response<Set<String>> zrangeByScore(String key, String min,
            String max) {
        return this.transaction.zrangeByScore(key, min, max);
    }

    public Response<Set<String>> zrangeByScore(String key, double min,
            double max, int offset, int count) {
        return this.transaction.zrangeByScore(key, min, max, offset, count);
    }

    public Response<Set<Tuple>> zrangeByScoreWithScores(String key, double min,
            double max) {
        return this.transaction.zrangeByScoreWithScores(key, min, max);
    }

    public Response<Set<Tuple>> zrangeByScoreWithScores(String key, double min,
            double max, int offset, int count) {
        return this.transaction.zrangeByScoreWithScores(key, min, max, offset, count);
    }

    public Response<Set<Tuple>> zrangeWithScores(String key, int start, int end) {
        return this.transaction.zrangeWithScores(key, start, end);
    }

    public Response<Long> zrank(String key, String member) {
        return this.transaction.zrank(key, member);
    }

    public Response<Long> zrem(String key, String member) {
        return this.transaction.zrem(key, member);
    }

    public Response<Long> zremrangeByRank(String key, int start, int end) {
        return this.transaction.zremrangeByRank(key, start, end);
    }

    public Response<Long> zremrangeByScore(String key, double start, double end) {
        return this.transaction.zremrangeByScore(key, start, end);
    }

    public Response<Set<String>> zrevrange(String key, int start, int end) {
        return this.transaction.zrevrange(key, start, end);
    }

    public Response<Set<Tuple>> zrevrangeWithScores(String key, int start,
            int end) {
        return this.transaction.zrevrangeWithScores(key, start, end);
    }

    public Response<Long> zrevrank(String key, String member) {
        return this.transaction.zrevrank(key, member);
    }

    public Response<Double> zscore(String key, String member) {
        return this.transaction.zscore(key, member);
    }

    public Response<Long> zunionstore(String dstkey, String... sets) {
        return this.transaction.zunionstore(dstkey, sets);
    }

    public Response<Long> zunionstore(String dstkey, ZParams params,
            String... sets) {
        return this.transaction.zunionstore(dstkey, params, sets);
    }

    public Response<String> bgrewriteaof() {
        return this.transaction.bgrewriteaof();
    }

    public Response<String> bgsave() {
        return this.transaction.bgsave();
    }

    public Response<String> configGet(String pattern) {
        return this.transaction.configGet(pattern);
    }

    public Response<String> configSet(String parameter, String value) {
        return this.transaction.configSet(parameter, value);
    }

    public Response<String> brpoplpush(String source, String destination,
            int timeout) {
        return this.transaction.brpoplpush(source, destination, timeout);
    }

    public Response<String> configResetStat() {
        return this.transaction.configResetStat();
    }

    public Response<String> save() {
        return this.transaction.save();
    }

    public Response<Long> lastsave() {
        return this.transaction.lastsave();
    }

    public Response<Long> publish(String channel, String message) {
        return this.transaction.publish(channel, message);
    }

    public Response<Long> publish(byte[] channel, byte[] message) {
        return this.transaction.publish(channel, message);
    }
}

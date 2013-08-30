package redis.clients.main;

import java.net.URI;
import java.util.List;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;

import redis.clients.jedis.*;
import redis.clients.util.Pool;

public class SentinelPool extends Pool<Jedis> {

    public SentinelPool(final Config poolConfig, final String host, final String masterName) {
        this(poolConfig, host, Protocol.DEFAULT_SENTINEL_PORT, Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE, masterName);
    }

    public SentinelPool(String host, int port, String masterName) {
        this(new Config(), host, port, Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE, masterName);
    }

    public SentinelPool(final String host, final String masterName) {
        URI uri = URI.create(host);
        if (uri.getScheme() != null && uri.getScheme().equals("redis")) {
            String h = uri.getHost();
            int port = uri.getPort();
            String password = uri.getUserInfo().split(":", 2)[1];
            int database = Integer.parseInt(uri.getPath().split("/", 2)[1]);
            this.internalPool =
                    new GenericObjectPool(new JedisFactory(h, port,
                                                           Protocol.DEFAULT_TIMEOUT, password, database, masterName), new Config());
        } else {
            this.internalPool =
                    new GenericObjectPool(new JedisFactory(host,
                                                           Protocol.DEFAULT_SENTINEL_PORT, Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE, masterName), new Config());
        }
    }

    public SentinelPool(final URI uri, final String masterName) {
        String h = uri.getHost();
        int port = uri.getPort();
        String password = uri.getUserInfo().split(":", 2)[1];
        int database = Integer.parseInt(uri.getPath().split("/", 2)[1]);
        this.internalPool = new GenericObjectPool(new JedisFactory(h, port,
                                                                   Protocol.DEFAULT_TIMEOUT, password, database, masterName), new Config());
    }

    public SentinelPool(final Config poolConfig, final String host, int port,
                        int timeout, final String password, final String masterName) {
        this(poolConfig, host, port, timeout, password, Protocol.DEFAULT_DATABASE, masterName);
    }

    public SentinelPool(final Config poolConfig, final String host, final int port, String masterName) {
        this(poolConfig, host, port, Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE, masterName);
    }

    public SentinelPool(final Config poolConfig, final String host, final int port, final int timeout, String masterName) {
        this(poolConfig, host, port, timeout, null, Protocol.DEFAULT_DATABASE, masterName);
    }

    public SentinelPool(final Config poolConfig, final String host, int port, int timeout, final String password,
                        final int database, String masterName) {
        super(poolConfig, new JedisFactory(host, port, timeout, password, database, masterName));
    }


    public void returnBrokenResource(final BinaryJedis resource) {
        returnBrokenResourceObject(resource);
    }

    public void returnResource(final BinaryJedis resource) {
        returnResourceObject(resource);
    }

    /**
     * PoolableObjectFactory custom impl.
     */
    private static class JedisFactory extends BasePoolableObjectFactory {
        private final String host;
        private final int port;
        private final int timeout;
        private final String password;
        private final int database;
        private final String master;

        public JedisFactory(final String host, final int port,
                            final int timeout, final String password,
                            final int database,
                            final String master) {
            super();
            this.host = host;
            this.port = port;
            this.timeout = timeout;
            this.password = password;
            this.database = database;
            this.master = master;
        }

        public Object makeObject() throws Exception {
            Jedis sentinel = new Jedis(this.host, this.port, this.timeout);
            sentinel.connect();
            if (null != this.password) {
                sentinel.auth(this.password);
            }

            List<String> result = sentinel.sentinelGetMasterAddrByName(master);

            System.err.println("HOST: " + result.get(0));
            System.err.println("PORT: " + result.get(1));

            sentinel.disconnect();

            final Jedis jedis = new Jedis(result.get(0), // address
                                          Integer.parseInt(result.get(1)), // port
                                          this.timeout);

            jedis.connect();
            if (null != this.password) {
                jedis.auth(this.password);
            }
            if( database != 0 ) {
                jedis.select(database);
            }

            return jedis;
        }

        public void destroyObject(final Object obj) throws Exception {
            if (obj instanceof Jedis) {
                final Jedis jedis = (Jedis) obj;
                if (jedis.isConnected()) {
                    try {
                        try {
                            jedis.quit();
                        } catch (Exception e) {
                        }
                        jedis.disconnect();
                    } catch (Exception e) {

                    }
                }
            }
        }

        public boolean validateObject(final Object obj) {
            if (obj instanceof Jedis) {
                final Jedis jedis = (Jedis) obj;
                try {
                    return jedis.isConnected() && jedis.ping().equals("PONG");
                } catch (final Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}

package redis.clients.misc;

import java.net.URI;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;

import redis.clients.jedis.*;
import redis.clients.util.Pool;
import redis.clients.jedis.exceptions.*;

public class MasterPool extends Pool<Jedis> {
    public MasterPool(final Config poolConfig, List<URI> endpoints,
                      int timeout) {
        super(poolConfig, new MasterFactory(endpoints, timeout));
    }

    public MasterPool(final Config poolConfig, List<URI> endpoints) {
        this(poolConfig, endpoints, Protocol.DEFAULT_TIMEOUT);
    }

    /*
    public MasterPool(final URI uri) {
        List<URI> endpoints = new ArrayList<URI>();
        endpoints.add(uri);
        this(poolConfig, endpoints);
    }

    public MasterPool(String host, int port) {
        this(poolConfig, new URI(host + ":" + String.valueOf(port)));
    }

    public MasterPool(final Config poolConfig, final String host) {
        this(poolConfig, new URI(host + ":" + Protocol.DEFAULT_PORT));
    }
    */

    public void returnBrokenResource(final BinaryJedis resource) {
        returnBrokenResourceObject(resource);
    }

    public void returnResource(final BinaryJedis resource) {
        returnResourceObject(resource);
    }

    /**
     * PoolableObjectFactory custom impl.
     */
    private static class MasterFactory extends BasePoolableObjectFactory {
        private List<URI> candidates;
        private final int timeout;

        public MasterFactory(List<URI> endpoints,
                             final int timeout) {
            super();
            this.candidates = new ArrayList<URI>(endpoints);
            this.timeout = timeout;
        }

        private URI nextCandidate() {
            URI uri = candidates.get(0);
            Collections.rotate(candidates, -1);
            return uri;
        }

        public Object makeObject() throws Exception {
            int ntries = 0;

            for (URI uri: this.candidates) {
                ntries++;
                System.out.println("Trying " + uri.toString());
                try {
                    Jedis conn = new Jedis(uri.getHost(), uri.getPort(),
                                           this.timeout);

                    if (uri.getUserInfo() != null) {
                        String tokens[] = uri.getUserInfo().split(":", 2);
                        String password = null;
                        if (tokens.length == 2 && tokens[1].length() > 0)
                            password = tokens[1];
                        if (null != password) {
                            conn.auth(password);
                        }
                    }

                    JedisInfo info = new JedisInfo(conn);
                    if (info.compareVersion("2.6") < 0)
                        throw new UnsupportedOperationException("redis 2.6 or higher required");
                    System.out.println("role: " + info.get("role"));
                    if ("master".equals(info.get("role"))) {
                        Collections.rotate(this.candidates, -ntries);
                        return conn;
                    }
                }
                catch (JedisException e) {
                    /* ignore */
                    System.err.println("warning: connection failed: "
                                       + uri.toString());
                }
                catch (UnsupportedOperationException e) {
                    /* ignore */
                    System.err.println("warning: unsupported: "
                                       + uri.toString());
                }
            }
            Collections.rotate(this.candidates, -ntries);
            throw new JedisConnectionException("no alive endpoint");
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
                    if (!jedis.isConnected())
                        return false;

                    if (!jedis.ping().equals("PONG"))
                        return false;

                    // TODO: if not master, return false;
                    return true;
                } catch (final Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}

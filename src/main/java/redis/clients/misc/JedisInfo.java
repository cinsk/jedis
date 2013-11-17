package redis.clients.misc;

import redis.clients.jedis.Jedis;
import java.util.HashMap;
import java.util.Map;

class JedisInfo extends HashMap<String, String> {
    private boolean loaded;
    private Jedis jedis;
    public JedisInfo(Jedis jedis) {
        super();
        loaded = false;
        this.jedis = jedis;

        load();
    }

    public int compareVersion(String version) {
        String myver = this.get("redis_version");

        String[] mine = this.get("redis_version").split("\\.");
        String[] yours = version.split("\\.");

        int i = 0;
        while (i < mine.length && i < yours.length &&
               mine[i].equals(yours[i]))
            i++;

        if (i < mine.length && i < yours.length) {
            int diff = Integer.valueOf(mine[i])
                    .compareTo(Integer.valueOf(yours[i]));
            return diff < 0 ? -1 : diff == 0 ? 0 : 1;
        }

        return mine.length < yours.length ? -1 :
                (mine.length == yours.length ? 0 : 1);
    }

    public void load() {
        if (loaded)
            return;

        String info = jedis.info();
        String[] entries = info.split("\r?\n");

        for (int i = 0; i < entries.length; i++) {
            if (entries[i].length() == 0)
                continue;
            if (entries[i].startsWith("#"))
                continue;

            String tokens[] = entries[i].split(":", 2);
            if (tokens.length != 2)
                System.err.println("error: unrecognized line: " + entries[i]);
            else {
                this.put(tokens[0], tokens[1]);
            }
        }

        loaded = true;
    }

}

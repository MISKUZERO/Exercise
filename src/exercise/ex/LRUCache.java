package exercise.ex;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        cache = new LinkedHashMap<>(capacity, .75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        Integer ret = cache.get(key);
        return ret == null ? -1 : ret;
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}

package exercise.ex;


import java.util.HashMap;

public class LRUCacheBuild {

    public static void main(String[] args) {
        LRUCacheBuild cache = new LRUCacheBuild(2);
        cache.put(2, 1);
        cache.put(3, 2);
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        cache.put(4, 3);
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    private static class CacheLine {

        CacheLine pre;
        CacheLine next;
        final Integer key;
        final Integer val;

        public CacheLine(Integer key, Integer val) {
            this.key = key;
            this.val = val;
        }
    }

    private static class TimeQueue {

        private final CacheLine root;
        private CacheLine tail;

        public TimeQueue() {
            root = new CacheLine(0, 0);
            tail = root;
        }

        void enq(CacheLine cacheLine) {
            cacheLine.pre = tail;
            tail.next = cacheLine;
            tail = cacheLine;
        }

        CacheLine poll() {
            CacheLine c = root.next;
            root.next = c.next;
            c.next.pre = root;
            return c;
        }

        void remove(CacheLine cacheLine) {
            if (cacheLine == tail) {
                tail = cacheLine.pre;
                tail.next = null;
                return;
            }
            cacheLine.pre.next = cacheLine.next;
            cacheLine.next.pre = cacheLine.pre;
        }

    }

    private final HashMap<Integer, CacheLine> cache;
    private final TimeQueue timeQueue;//访问时间队列，按照最后一次访问时间排序
    private final int cacheCapacity;

    public LRUCacheBuild(int capacity) {
        cache = new HashMap<>(capacity);
        timeQueue = new TimeQueue();
        cacheCapacity = capacity;
    }

    public int get(int key) {
        CacheLine cacheLine = cache.get(key);
        if (cacheLine != null) {
            TimeQueue timeQueue = this.timeQueue;
            timeQueue.remove(cacheLine);//更新最后一次访问时间
            timeQueue.enq(cacheLine);
            return cacheLine.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        HashMap<Integer, CacheLine> cache = this.cache;
        TimeQueue timeQueue = this.timeQueue;
        CacheLine newCacheLine = new CacheLine(key, value), pre = cache.put(key, newCacheLine);
        if (pre != null)
            timeQueue.remove(pre);
        timeQueue.enq(newCacheLine);
        if (cache.size() > cacheCapacity)
            cache.remove(timeQueue.poll().key);
    }
}

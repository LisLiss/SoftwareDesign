import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class LRUCacheTest {
    private Random random = new Random();

    @Test
    public void testPut() {
        int capacity = 100;
        LRUCache<Integer, String> lruCache = new LRUCache<>(capacity);
        for (int i = 0; i < 100; i++) {
            lruCache.put(i, "abc" + Integer.toString(i));
        }
        ArrayList allKeys = lruCache.getAll();
        for (int i = 0; i < 100; i++) {
            Assert.assertEquals(allKeys.get(i), i);
        }
    }

    @Test
    public void testGet() {
        int capacity = 10;
        LRUCache<Integer, String> lruCache = new LRUCache<>(capacity);
        for (int i = 0; i < 10; i++) {
            lruCache.put(i, "abc" + Integer.toString(i));
        }
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(lruCache.get(i), "abc" + Integer.toString(i));
        }
        Assert.assertNull(lruCache.get(10));
    }

    @Test
    public void testOrder() {
        int capacity = 100;
        LRUCache<Integer, String> lruCache = new LRUCache<>(capacity);
        for (int i = 0; i < 100; i++) {
            lruCache.put(i, "abc" + Integer.toString(i));
        }
        for (int i = 0; i < 10; i++) {
            lruCache.put(0, "abc0");
        }
        ArrayList allKeys = lruCache.getAll();
        for (int i = 0; i < 99; i++) {
            Assert.assertEquals(allKeys.get(i), i + 1);
        }
        Assert.assertEquals(allKeys.get(99), 0);
    }

    @Test
    public void testOverflow() {
        int capacity = 10;
        LRUCache<Integer, String> lruCache = new LRUCache<>(capacity);
        for (int i = 0; i < 13; i++) {
            lruCache.put(i, "abc" + Integer.toString(i));
        }
        for (int i = 3; i < 13; i++) {
            Assert.assertEquals(lruCache.get(i), "abc" + Integer.toString(i));
        }
        Assert.assertNull(lruCache.get(0));
        Assert.assertNull(lruCache.get(1));
        Assert.assertNull(lruCache.get(2));
    }

    @Test
    public void testRemove() {
        int capacity = 10;
        LRUCache<Integer, String> lruCache = new LRUCache<>(capacity);
        for (int i = 0; i < 10; i++) {
            lruCache.put(i, "abc" + Integer.toString(i));
        }
        lruCache.remove(0);
        lruCache.remove(1);
        lruCache.remove(2);
        Assert.assertNull(lruCache.get(0));
        Assert.assertNull(lruCache.get(1));
        Assert.assertNull(lruCache.get(2));
    }

    @Test
    public void testCapacity() {
        for (int i = 0; i < 100; i++) {
            int capacity = random.nextInt(1000) + 1;
            LRUCache<Integer, String> lruCache = new LRUCache<>(capacity);
            Assert.assertEquals(capacity, lruCache.getCapacity());
        }
    }

    @Test
    public void testSize() {
        int capacity = random.nextInt(100) + 1;
        LRUCache<Integer, String> lruCache = new LRUCache<>(capacity);
        for (int i = 0; i < 100; i++) {
            lruCache.put(i, "abc" + Integer.toString(i));
            Assert.assertEquals(Math.min(i + 1, capacity), lruCache.getSize());
        }
    }

}

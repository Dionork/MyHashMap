package org.course.aston;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test
    void put() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        map.put("key1", "value");
        map.put("key2", "value");
        assertEquals(3, map.size());
    }

    @Test
    void constructorWith3Parameters() {
        MyHashMap<String, String> map = new MyHashMap<>(10, 0.75F, 13);
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, "value" + i);
        }
        assertEquals(10, map.size());
    }

    @Test
    void constructorWith1Parameter() {
        MyHashMap<String, String> map = new MyHashMap<>(10);
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, "value" + i);
        }
        assertEquals(10, map.size());
    }

    @Test
    void constructorWith2Parameter() {
        MyHashMap<String, String> map = new MyHashMap<>(10, 0.75F);
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, "value" + i);
        }
        assertEquals(10, map.size());
    }

    @Test
    void constructorCopyMap() {
        MyHashMap<String, String> map = new MyHashMap<>(10);
        for (int i = 0; i < 10; i++) {
            map.put("key" + i, "value" + i);
        }
        MyHashMap<String, String> map2 = new MyHashMap<>(map);
        assertEquals(10, map2.size());
    }

    @Test
    void putNull() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", null);
        assertNull(map.get("key"));
    }

    @Test
    void putNullKey() {
        MyHashMap<String, String> map = new MyHashMap<>();
        String consoleOutput = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            map.put(null, "value");
            capture.flush();
            consoleOutput = outputStream.toString().trim();

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("Введите значение ключа!", consoleOutput);
    }

    @Test
    void putNullValue() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", null);
        assertNull(map.get("key"));
    }

    @Test
    void putIdenticalKey() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("Aa", "value");
        map.put("BB", "value1");
        assertEquals(2, map.size());
    }

    @Test
    void get() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        assertEquals("value", map.get("key"));
    }

    @Test
    void getNull() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", null);
        assertNull(map.get("key"));
    }

    @Test
    void getNullKey() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        assertNull(map.get(null));
    }

    @Test
    void values() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        Set<String> set = map.values();
        assertEquals(1, set.size());
    }

    @Test
    void keySet() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        Set<String> set = map.keySet();
        assertEquals(1, set.size());
    }

    @Test
    void entrySet() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        Set<MyHashMap>set = map.entrySet();
        assertEquals(1, set.size());

    }

    @Test
    void delete() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        map.delete("key");
        assertEquals(0, map.size());
    }

    @Test
    void size() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        assertEquals(1, map.size());
    }

    @Test
    void hash() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        int h;
        assertEquals((h = "key".hashCode()) ^ (h >>> 16), map.hash("key"));
    }

    @Test
    void resize() {
        int capacity = 1;
        MyHashMap<String, String> map = new MyHashMap<>(capacity);
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, "value");
        }
        assertEquals(20, map.size());
    }
}
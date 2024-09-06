package org.course.aston;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    void get() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        assertEquals("value", map.get("key"));
    }

    @Test
    void values() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        String consoleOutput = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            map.values();
            capture.flush();
            consoleOutput = outputStream.toString().trim();

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("value", consoleOutput);
    }

    @Test
    void keySet() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        String consoleOutput = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            map.keySet();
            capture.flush();
            consoleOutput = outputStream.toString().trim();

        } catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals("key", consoleOutput);
    }

    @Test
    void entrySet() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key", "value");
        String consoleOutput[];
        String key = null;
        String value = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(outputStream);
            System.setOut(capture);
            map.entrySet();
            capture.flush();
            consoleOutput = outputStream.toString().split(" ");
            key = consoleOutput[0].trim();
            value = consoleOutput[1].trim();

        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("key", key);
        assertEquals("value", value);
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
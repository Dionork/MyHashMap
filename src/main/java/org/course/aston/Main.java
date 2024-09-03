package org.course.aston;

public class Main {
    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap();
        for (int i = 0; i < 10; i++) {
            map.put(i, String.valueOf(i));
        }
        map.entrySet();
        System.out.println("_________________");
        for (int i = 0; i < 3; i++) {
            map.put(i, "ЗАМЕНИЛ ЗНАЧЕНИЕ " + i);
        }
        map.entrySet();
        System.out.println("_________________");
        for (int i = 0; i < 9; i++) {
            map.delete(i);
        }
        map.entrySet();
        map.values();
        map.keySet();
        System.out.println("_________________");
        System.out.println(map.size());
        System.out.println("_________________");
        map.get(0); //null
        map.get(9);
    }

}

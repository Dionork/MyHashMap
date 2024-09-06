package org.course.aston;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap();
        for (int i = 0; i < 20; i++) {
            map.put("key" + i, "value" + i);
        }
        System.out.println("________________");
        for (int i = 0; i < 20; i++) {
            System.out.println(map.get("key" + i));
        }
        map.entrySet();
        System.out.println(map.size());
        System.out.println("________________");
        for (int i = 5; i < 9; i++) {
            System.out.println("Удалено: " + map.get("key" + i));
            map.delete("key" + i);

        }
        map.entrySet();
        System.out.println("________________");
        for (int i = 0; i < 3; i++) {
            System.out.println("Заменил: " + map.get("key" + i));
            map.put("key" + i, "value" + i + i);
        }
        System.out.println("________________");
        System.out.println(map.size());
        map.put("1Pupkin", "Pupkin");
        map.put("2Nagan", "Nagan");
        map.put("1Pupkin", "Pupkin"); // Дубль
        map.put("3Pavlivich", "Pavlivich");
        map.put("1Pupkin", "Pupkin"); // Дубль
        map.put("4Manovich", "Manovich");
        map.put("5Krasavchik", "Krasavchik");
        map.put("6Pupkin", "Pupkin");
        map.put("7Nagan", "Nagan");
        map.put("8Pavlivich", "Pavlivich");
        map.put("9Manovich", "Manovich");
        map.put("10Krasavchik", "Krasavchik");
        map.put("11Pupkin", "Pupkin");
        map.put("12Nagan", "Nagan");
        map.put("13Pavlivich", "Pavlivich");
        map.put("14Manovich", "Manovich");
        map.put("15Krasavchik", "Krasavchik");
        map.put("16Pupkin", "Pupkin");
        map.put("17Nagan", "Nagan");
        map.put("18Pavlivich", "Pavlivich");
        map.put("19Manovich", "Manovich");
        map.put("20Krasavchik", "Krasavchik");
        map.put("21Pupkin", "Pupkin");
        map.put("22Nagan", "Nagan");
        map.entrySet();
        System.out.println("________________");
        map.keySet();
        System.out.println("________________");
        map.values();
        System.out.println("________________");
        System.out.println(map.size());
    }
}
package org.course.aston;
public class Main {
    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap();
//        for (int i = 0; i < 20; i++) {
//            map.put("key" + i, "value" + i);
//        }
//        map.entrySet();
//        System.out.println(map.size());
//        System.out.println("________________");
//        for (int i = 5; i < 9; i++) {
//            System.out.println("Удалено: " + map.get("key" + i));
//            map.delete("key" + i);
//
//        }
//        map.entrySet();
//        System.out.println("________________");
//        for (int i = 0; i < 3; i++) {
//            System.out.println("Заменил: " + map.get("key" + i));
//            map.put("key" + i, "value" + i+i);
//        }
        map.put("1","Pupkin");
        map.put("2","Nagan");
        map.put("3","Pavlivich");
        map.put("4","Manovich");
        map.put("5","Krasavchik");
        map.put("6","Pupkin");
        map.put("7","Nagan");
        map.put("8","Pavlivich");
        map.put("9","Manovich");
        map.put("10","Krasavchik");
        map.put("11","Pupkin");
        map.put("12","Nagan");
        map.put("13","Pavlivich");
        map.put("14","Manovich");
        map.put("15","Krasavchik");
        map.put("16","Pupkin");
        map.put("17","Nagan");
        map.put("18","Pavlivich");
        map.put("19","Manovich");
        map.put("20","Krasavchik");
        map.put("21","Pupkin");
        map.put("22","Nagan");
        map.entrySet();
        System.out.println(map.size());
    }

}

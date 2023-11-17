package by.it.group251004.savenok.lesson11;

import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        MyHashSet<Integer> hashSet = new MyHashSet<>();
        HashSet<Integer> hashSet1 = new HashSet<>();
        hashSet.add(10);
        hashSet.add(20);
        hashSet1.add(10);
        hashSet1.add(20);
        System.out.println(hashSet);
        System.out.println(hashSet1);
    }
}

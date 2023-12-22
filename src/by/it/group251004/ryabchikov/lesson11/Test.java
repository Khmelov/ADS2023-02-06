package by.it.group251004.ryabchikov.lesson11;


import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        HashSet<Integer> set1 = new HashSet<>();
        MyHashSet<Integer> set2 = new MyHashSet<>();
        set1.add(null);
        set1.add(2);
        set1.add(3);
        set1.add(3);
        set1.add(3);

        set2.add(1);
        set2.add(3);
        set2.add(3);
        set2.add(3);
        set2.add(100);


        System.out.println(set1);
        System.out.println(set2);
    }
}

package by.it.group251004.savenok.lesson09;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        ListC<String> listC = new ListC<>();
        list.add("1");
        list.add("2");
        list.add("3");
        listC.add("4");
        listC.add("5");
        listC.add("6");
        listC.retainAll(list);
        System.out.println(listC);
    }

}

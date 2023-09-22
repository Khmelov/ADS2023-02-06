package by.it.group251002.yanucevich.lesson09;
import java.util.ArrayList;
import java.util.List;

public class Rnnr {
    public static void main(String[] args){
        List<String> myList = new ListB<>();
        List<String> arList = new ArrayList<>();

        myList.add("Hey!"); arList.add("Hey!");
        myList.add("You"); arList.add("You");
        myList.add("there!"); arList.add("there!");
        System.out.println("Actual:   "+myList+"\nExpected: "+arList+"\n");
        myList.remove(2); arList.remove(2);
        System.out.println("Actual:   "+myList+"\nExpected: "+arList+"\n");
        System.out.println("Actual:   "+myList.size()+"\nExpected: "+arList.size()+"\n");
        myList.add("tester1"); arList.add("tester1");
        myList.add("tester2"); arList.add("tester2");
        myList.add("tester3"); arList.add("tester3");
        myList.add("tester4"); arList.add("tester4");
        myList.add(2,"key"); arList.add(2,"key");
        System.out.println("Actual:   "+myList+"\nExpected: "+arList+"\n");




    }

}

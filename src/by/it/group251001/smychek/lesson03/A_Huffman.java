package by.it.group251001.smychek.lesson03;

import org.junit.runner.manipulation.Sorter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class A_Huffman {

    //Изучите классы Node InternalNode LeafNode
   public class Node implements Comparable<Node>{
       Node left, right;
       int frequency;
       Character z;

       @Override
       public int compareTo(Node o) {
           return Integer.compare(frequency, o.frequency);
       }
   }

   Node NewNode(Character c, int x){
       Node a = new Node();
       a.z=c;
       a.frequency=x;
       a.left=null;
       a.right=null;
       return a;
   }

   Node merge(Node l, Node r){
       Node a = new Node();
       a.left=l;
       a.right=r;
       a.frequency=l.frequency+r.frequency;
       a.z=l.z;
       return a;
   }

   void writeCodes(Map<Character, String> codes, Node a, String code){
       if(a.left != null){
           writeCodes(codes, a.left, code + '0');
       }
       if(a.right != null){
           writeCodes(codes, a.right, code + '1');
       }
       if((a.right == null)&&(a.left == null))
           codes.put(a.z, code);
   }

   void writeTree(Node a){
       if(a.left != null){
           System.out.print('0');
           writeTree(a.left);
       }
       System.out.print(a.z);
       if(a.right != null){
           System.out.print('1');
           writeTree(a.right);
       }
   }

    static private Map<Character, String> codes = new TreeMap<>();


    //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
    String encode(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String s = scanner.next();

        Map<Character, Integer> count = new HashMap<>();
        for (int i=0;i<s.length();++i) {
            count.put(s.charAt(i), count.getOrDefault(s.charAt(i), 0) + 1);
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (var x : count.entrySet()) {
            priorityQueue.add(NewNode(x.getKey(), x.getValue()));
        }

        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll(), right = priorityQueue.poll();
            priorityQueue.add(merge(left, right));
        }

        Node Coded = priorityQueue.poll();

        writeCodes(codes, Coded, "");


        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();++i)
        sb.append(codes.get(s.charAt(i)));

        return sb.toString();
        //01001100100111
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group251001/smychek/lesson03/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        long startTime = System.currentTimeMillis();
        String result = instance.encode(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("%d %d\n", codes.size(), result.length());
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
        }
        System.out.println(result);
    }

}

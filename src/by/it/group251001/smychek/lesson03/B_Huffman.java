package by.it.group251001.smychek.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class B_Huffman {

    public class Node{
        Node left;
        Node right;
        Character z;
    }

    Node NewNode(Character z){
        Node a = new Node();
        a.z = z;
        a.left = null;
        a.right = null;
        return a;
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

    String decode(File file) throws FileNotFoundException {
        StringBuilder result=new StringBuilder();
        //прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();

        Node HafTree = NewNode('$');

        for(int i=0;i<count;++i){
            String z = scanner.next();
            String code = scanner.next();
            Node Temp = HafTree;
            for(int j=0;j<code.length()-1;++j){
                if(code.charAt(j)=='0') {
                    if (Temp.left == null)
                        Temp.left = NewNode('$');
                    Temp = Temp.left;
                }else {
                    if (Temp.right == null)
                        Temp.right = NewNode('$');
                    Temp = Temp.right;
                }
            }
            if(code.charAt(code.length()-1)=='0')
                Temp.left = NewNode(z.charAt(0));
            else
                Temp.right = NewNode(z.charAt(0));
        }

        String z = scanner.next();
        Node Temp = HafTree;
        StringBuilder sb = new StringBuilder();


        for(int i=0;i<z.length();++i){
            if (z.charAt(i)=='0')
                Temp = Temp.left;
            else
                Temp = Temp.right;
            if ((Temp.left == null) && (Temp.right == null)){
                sb.append(Temp.z);
                Temp = HafTree;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group251001/smychek/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }

}

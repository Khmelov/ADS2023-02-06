package by.it.group251002.samoilenko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {
   int diff( char on,char tw){
       if (on==tw) return 0;
       else return 1;
   }

   int EditDistTD(String one, String two,int i,int j,int [][] D) {

       if (D[i][j] == -1) {
           if (i == 0) {
               D[i][j] = j;
           } else {
               if (j == 0) {
                   D[i][j] = i;
               } else {
                   int ins = EditDistTD(one, two, i, j - 1, D) + 1;
                   int del = EditDistTD(one, two, i - 1, j, D) + 1;
                   int c;
                   c = diff(one.charAt(i - 1), two.charAt(j - 1));
                   int sub = EditDistTD(one, two, i - 1, j - 1, D) + c;
                   D[i][j] = Math.min(Math.min(ins, del), sub);
               }
           }
       }
       return D[i][j];
   }

   int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
       int n=one.length();
       int m=two.length();
       int[][] D=new int[n+1][m+1];

       for(int i=0;i<=n;i++) {
           for (int j = 0; j <= m; j++) {
               D[i][j] = -1;
           }
       }





        int result =EditDistTD(one,two,n,m,D);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

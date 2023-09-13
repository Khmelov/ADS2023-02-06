package by.it.group251001.karpekov.lesson07;

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


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


        int [][] d = new int[one.length()+1][two.length()+1];

        for (int i = 0; i < one.length()+1; i++)
        {
            for (int j = 0; j < two.length()+1; j++)
            {
                d[i][j]=dCalc(d, i, j, one, two);
            }
        }

        int result = d[one.length()][two.length()];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    int m(char s1, char s2)
    {
        if (s1==s2) return 0;
        else return 1;
    }

    int dCalc (int[][] d, int i, int j, String s1, String s2)
    {
        int res;
        if (i == 0 && j == 0) res = 0;
        else if (i==0 && j>0) res = j;
        else if (j==0 && i>0) res = i;
        else {
            res = Math.min(dCalc(d, i, j-1, s1, s2)+1,  Math.min(dCalc(d, i-1, j, s1, s2)+1, dCalc(d, i-1, j-1, s1, s2) + m(s1.charAt(i-1), s2.charAt(j-1)) ));
        }
        return res;
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

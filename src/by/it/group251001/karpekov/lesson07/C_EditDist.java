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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!


        String result = "";
        int [][] d = new int[two.length()+1][one.length()+1];

        int[] str1 = new int[one.length()+1];
        int[] str2 = new int[one.length()+1];

        for (int j = 0; j < str1.length; j++) str1[j] = j;
        d[0]=str1;

        for (int i = 1; i <= two.length(); i++)
        {
            str2[0] = i;
            for (int j = 1; j < str2.length; j++)
            {
                str2[j] = min(str2[j-1]+1, str1[j-1] + m(one.charAt(j-1), two.charAt(i-1)), str1[j]+1);
            }
            d[i] = str2.clone();
            str1 = str2.clone();
        }

        int i = two.length();
        int j = one.length();
        while(i>0 && j>0)
        {
            // delete
            int res = d[i-1][j]+1;
            int rcase = 1;
            if (d[i][j-1]+1 < res)
            {
                // add
                res = d[i][j-1]+1;
                rcase = 2;
            }
            if (d[i-1][j-1] + m(one.charAt(j-1), two.charAt(i-1)) < res)
            {
                // replace / match
                res = d[i-1][j-1] + m(one.charAt(j-1), two.charAt(i-1));
                if (m(one.charAt(j-1), two.charAt(i-1)) == 1)
                    // replace
                    rcase = 3;
                    // match
                else rcase = 4;
            }

            if (rcase == 1) {
                result = "-" + one.charAt(j-1) + "," + result;
                i--;
            }
            else if (rcase == 2) {
                result = "+" + two.charAt(i-1) + "," + result;
                j--;
            }
            else if (rcase == 3) {
                result = "~" + two.charAt(i - 1) + "," + result;
                i--;
                j--;
            }
            else if (rcase == 4) {
                result = "#,"+ result;
                i--;
                j--;

            }


        }


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private static int min(int n1, int n2, int n3) {
        return Math.min(Math.min(n1, n2), n3);
    }
    int m(char s1, char s2)
    {
        if (s1==s2) return 0;
        else return 1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
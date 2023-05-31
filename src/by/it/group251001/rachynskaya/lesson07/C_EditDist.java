package by.it.group251001.rachynskaya.lesson07;

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

        int[][] Tabl = new int[one.length() + 1][two.length() + 1];
        int n = one.length(), m = two.length();
        for (int i = 0; i <= m; i++) {
            Tabl[0][i] = i;
        }
        for (int j = 0; j <= n; j++) {
            Tabl[j][0] = j;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                Tabl[i][j] = Tabl[i - 1][j] + 1;
                if (Tabl[i][j - 1] + 1 < Tabl[i][j]) Tabl[i][j] = Tabl[i][j - 1] + 1;
                int tmp = Tabl[i - 1][j - 1];
                if (one.charAt(i - 1) != two.charAt(j - 1)) tmp++;
                if (tmp < Tabl[i][j]) Tabl[i][j] = tmp;
            }
        }
        int tmp;
        char[] A = new char[one.length() + 1];
        char[] B = new char[two.length() + 1];

        for (int i = 1; i < A.length; i++) {
            A[i] = one.charAt(i - 1);
        }

        for (int i = 1; i < B.length; i++) {
            B[i] = two.charAt(i - 1);
        }
        StringBuilder result = new StringBuilder();

        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (A[i] == B[j]) tmp = 0;
            else tmp = 1;
            if (Tabl[i][j] == Tabl[i - 1][j - 1] + tmp) {
                result.insert(0, ',');
                result.insert(0, tmp == 1 ? "~" : "#");
                if (tmp == 1) {
                    result.insert(1, B[j]);
                }
                i--;
                j--;
            } else if (Tabl[i][j] == Tabl[i][j - 1] + 1) {
                result.insert(0, ',');
                result.insert(0, '+');
                result.insert(1, B[j]);
                j--;
            } else {
                result.insert(0, ',');
                result.insert(0, '-');
                result.insert(1, A[i]);
                i--;
            }
        }

        while (i > 0) {
            result.insert(0, ',');
            result.insert(0, '-');
            result.insert(1, A[i]);
            i--;
        }
        while (j > 0) {
            result.insert(0, ',');
            result.insert(0, '+');
            result.insert(1, B[j]);
            j--;
        }

        return result.toString();
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
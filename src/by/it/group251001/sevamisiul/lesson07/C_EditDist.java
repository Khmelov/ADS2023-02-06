package by.it.group251001.sevamisiul.lesson07;

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
        int m = one.length(), n = two.length();
        int[][] D = new int[m + 1][n + 1];
        char[][] P = new char[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            D[i][0] = i;
            P[i][0] = '-';
        }
        for (int i = 0; i <= n; i++) {
            D[0][i] = i;
            P[0][i] = '+';
        }

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                int flag = (one.charAt(i - 1) == two.charAt(j - 1)) ? 0 : 1;
                if(D[i][j - 1] < D[i - 1][j] && D[i][j - 1] < D[i - 1][j - 1] + flag) {
                    D[i][j] = D[i][j - 1] + 1;
                    P[i][j] = '+';
                }
                else if(D[i - 1][j] < D[i - 1][j - 1] + flag) {
                    D[i][j] = D[i - 1][j] + 1;
                    P[i][j] = '-';
                }
                else {
                    D[i][j] = D[i - 1][j - 1] + flag;
                    P[i][j] = (flag == 1) ? '~' : '#';
                }
            }

        StringBuilder ans = new StringBuilder();
        int i = m, j = n;
        while(Math.max(i,j)>0) {
            char c = P[i][j];
            ans.append(c);
            ans.append(',');
            if(c == '~' || c == '#') {
                i--;
                j--;
            }
            else if(c == '-') {
                i--;
            }
            else {
                j--;
            }
        }
        return ans.toString();
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
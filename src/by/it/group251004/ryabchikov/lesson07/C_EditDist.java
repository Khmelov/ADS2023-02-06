package by.it.group251004.ryabchikov.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
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


        int lengthOfFirstString = one.length();
        int lengthOfSecondString = two.length();
        int[][] M = new int[lengthOfFirstString + 1][lengthOfSecondString + 1];

        for (int i = 0; i <= lengthOfSecondString; i++)
            M[0][i] = i;
        for (int i = 0; i <= lengthOfFirstString; i++)
            M[i][0] = i;

        for (int i = 1; i <= lengthOfFirstString; i++) {
            for (int j = 1; j <= lengthOfSecondString; j++) {
                int insert = M[i][j - 1] + 1;
                int delete = M[i - 1][j] + 1;
                int match = M[i - 1][j - 1] + (one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1);

                int min = insert;
                if (delete < min){
                    min = delete;
                }
                if (match < min){
                    min = match;
                }
                M[i][j] = min;
            }
        }

        StringBuilder result = new StringBuilder();

        int i = lengthOfFirstString, j = lengthOfSecondString;

        while (i != 0 || j != 0){
            if (i > 0 && j > 0 && M[i - 1][j - 1] + (one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1) == M[i][j]){
                if(one.charAt(i - 1) == two.charAt(j - 1)){
                    result.insert(0, "#,");
                }
                else {
                    result.insert(0, "~" + two.charAt(j - 1) + ",");
                }
                i--;
                j--;
            }
            else if (j > 0 && M[i][j] == M[i][j - 1] + 1) {
                result.insert(0, "+" + two.charAt(j - 1) + ",");
                j--;
            }
            else if (i > 0 && M[i][j] == M[i - 1][j] + 1) {
                result.insert(0, "-" + one.charAt(i - 1) + ",");
                i--;
            }
        }

        return result.toString();
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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
package by.it.group251002.baranovskaia.lesson07;

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

        final int N = one.length() + 1;
        final int M = two.length() + 1;
        int[][] distancesTable = new int[N][M];

        //Fill 1-st raw and 1-st column with string indexes
        for (int i = 0; i < N; ++i)
            distancesTable[i][0] = i;
        for (int j = 0; j < M; ++j)
            distancesTable[0][j] = j;

        for (int i = 1; i < N; ++i)
            for (int j = 1; j < M; ++j) {
                int ins = distancesTable[i][j - 1] + 1; //If insert element - take left one form the table + 1
                int del = distancesTable[i - 1][j] + 1; //If delete element - take one above from the table + 1
                int sub = distancesTable[i - 1][j - 1] + ((one.charAt(i-1) == two.charAt(j-1)) ? 0 : 1); //If change element - take one from diagonal + 1, if it's not the same element
                distancesTable[i][j] = Math.min(Math.min(ins, del), sub); //Take minimum of values above
            }

        //Make string with sequence of operations and elements in reverse order
        String revResult = "";
        int i = N - 1;
        int j = M - 1;
        while (i != 0 || j != 0){
            if (distancesTable[i][j] == distancesTable[i][j - 1] + 1) { //If equals to left element - it's insert operation
                revResult += "+" + two.charAt(j - 1) + ",";
                --j;
            }
            else if (distancesTable[i][j] == distancesTable[i - 1][j] + 1) { //If equals to element above - it's delete operation
                revResult += "-" + one.charAt(i - 1) + ",";
                --i;
            }
            else {
                if (distancesTable[i][j] == distancesTable[i - 1][j - 1]) //If equals to diagonal and didn't make sequence longer - it's copy operation
                    revResult += "#,";
                else //If equals to diagonal and made sequence longer - it's replace operation
                    revResult += "~" + two.charAt(j - 1) + ",";
                --i;
                --j;
            }
        }

        //Reverse string with operations and symbols (very simple algorithm)
        String result = "";
        int count = revResult.length() - 2;
        while (count >= 0) {
            int length = 0;
            String oper_plus_elem = "";
            while (count >= 0 && revResult.charAt(count) != ',') {
                --count;
                ++length;
            }
            oper_plus_elem += revResult.substring(count + 1, count + 1 + length);
            result += oper_plus_elem + ",";
            --count;
        }

        /* !!!NOTICE!!! */
        /*
        --------------------------------------------------------------------------------------------------------
        This program provides different from expected result in 2-nd and 3-rd cases, but they are CORRECT either
        --------------------------------------------------------------------------------------------------------
         */

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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
package by.it.group251003.Nasevich_Ilya.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.min;

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
        int[][] dp = new int[one.length() + 1][two.length() + 1];

        for(int i = 0; i <= one.length(); i++){
            dp[i][0] = i;
        }

        for(int j = 0; j <= two.length(); j++){
            dp[0][j] = j;
        }

        for(int i = 1; i <= one.length(); i++){
            for(int j = 1; j <= two.length(); j++){
                int c = (one.charAt(i - 1) != two.charAt(j - 1) ? 1 : 0);
                dp[i][j] = min(min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + c);
            }
        }

        List<String> res = new ArrayList<>();
        int i = one.length(), j = two.length();
        while(i > 0 || j > 0){
            int c = (i > 0 && j > 0 && one.charAt(i - 1) != two.charAt(j - 1) ? 1 : 0);

            if(i > 0 && j > 0 && dp[i][j] == dp[i - 1][j - 1] + c){
                res.add(c == 0 ? "#" : "~" + two.charAt(j - 1));
                i--;
                j--;
            }else if(i > 0 && dp[i][j] == dp[i - 1][j] + 1){
                res.add("-" + one.charAt(i - 1));
                i--;
            }else if(j > 0 && dp[i][j] == dp[i][j - 1] + 1){
                res.add("+" + two.charAt(j - 1));
                j--;
            }
        }

        Collections.reverse(res);
        StringBuilder sb = new StringBuilder();
        for (String s : res) {
            sb.append(s);
            sb.append(',');
        }

        return sb.toString();
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
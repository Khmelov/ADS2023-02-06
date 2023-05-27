package by.it.group251001.smychek.lesson07;

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
        String result = "";
        int[][] dp = new int[one.length() + 1][two.length() + 1];
        for(int i=0; i<= one.length(); ++i)
            dp[i][0] = i;
        for(int j=0; j<= two.length(); ++j)
            dp[0][j] = j;

        for(int i=1;i<=one.length();++i)
            for(int j=1;j<=two.length();++j){

                dp[i][j] = dp[i-1][j] + 1;
                if (dp[i][j] > dp[i][j-1] + 1)
                    dp[i][j] = dp[i][j - 1] + 1;
                int res = 1;
                if(one.charAt(i-1) == two.charAt(j-1))
                    res = 0;
                if(dp[i][j] > dp[i-1][j-1] + res)
                    dp[i][j] = dp[i - 1][j - 1] + res;
            }
        int i = one.length();
        int j = two.length();

        while((i>0) && (j>0)){
            String temp = "";
            int res = 1;
            if (one.charAt(i - 1) == two.charAt(j - 1))
                res = 0;
            if(dp[i - 1][j - 1] + res == dp[i][j]){
                if(res == 0)
                    temp = "#";
                else
                    temp = "~" + two.charAt(j - 1);
            } else
                if (dp[i - 1][j] + 1 == dp[i][j])
                    temp = "-" + one.charAt(i - 1);
                else
                    temp = "+" + two.charAt(j - 1);
            if((temp.charAt(0) == '#') || (temp.charAt(0)=='~')){
                --i;
                --j;
            }
            if(temp.charAt(0) == '-')
                --i;
            if(temp.charAt(0) == '+')
                --j;
            result = "," + temp + result;

        }
        while(j > 0){
            result = ",+" + two.charAt(j - 1) + result;
            --j;
        }
        while(i > 0){
            result = ",-" + one.charAt(i - 1) + result;
            --i;
        }
        result = result.substring(1, result.length());
        result = result + ",";
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
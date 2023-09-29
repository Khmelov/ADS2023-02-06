package by.it.group251001.churavskiy.lesson07;

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

    String calculate(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];
        String[][] s = new String[x.length() + 1][y.length() + 1];
        int a,b,c;
        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                    if (j==y.length() || j ==0)
                    {
                        s[i][j]="";
                    }
                    else{
                        s[i][j] = "+" + y.charAt(i) + ",";
                    }
                }
                else if (j == 0) {
                    dp[i][j] = i;
                    if (i==x.length() || i==0)
                    {
                        s[i][j]="";
                    }
                    else{s[i][j]="-"+x.charAt(j)+",";}
                }
                else {
                    a=dp[i - 1][j - 1] + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1));
                    b=dp[i - 1][j] + 1;
                    c=dp[i][j - 1] + 1;
                    if (a<b && a<c)
                    {
                        dp[i][j]=a;
                        s[i][j]=s[i-1][j-1];
                        if (costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1))==1)
                        {
                            s[i][j]=s[i][j]+"~"+y.charAt(j-1)+",";
                        }
                        else
                        {
                            s[i][j]+="#,";
                        }
                    } else if (b<=a && b<=c) {
                        dp[i][j]=b;
                        s[i][j]=s[i-1][j]+"-"+x.charAt(i-1)+",";
                    } else  {
                        dp[i][j]=c;
                        s[i][j]=s[i][j-1]+"+"+y.charAt(j-1)+",";
                    }
                }
            }
        }

        return s[x.length()][y.length()];
    }

    private int costOfSubstitution(char a, char b) {
        if (a==b)
        {
            return 0;
        }
        return 1;
    }

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return calculate(one,two);
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
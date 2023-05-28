package by.it.group251002.lapus_vitaliy.lesson07;

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
        StringBuffer result = new StringBuffer();
        int n=one.length();
        int m=two.length();
        int[][] arr=new int[m+1][n+1];
        for(int i=0;i<n+1;i++)
        {
            arr[0][i]=i;
        }

        for(int i=1;i<=m;i++)
        {
            arr[i][0]=arr[i-1][0]+1;
            for(int j=1;j<=n;j++)
            {
                arr[i][j]=arr[i][j-1]+1;
                if(arr[i][j]>arr[i-1][j])
                {
                    arr[i][j]=arr[i-1][j]+1;
                }
                if (one.charAt(j-1)==two.charAt(i-1))
                {
                    if(arr[i][j]>arr[i-1][j-1])
                    {
                        arr[i][j]=arr[i-1][j-1];
                    }
                }
                else
                {
                    if(arr[i][j]>arr[i-1][j-1]+1)
                    {
                        arr[i][j]=arr[i-1][j-1]+1;
                    }
                }
            }
        }

        int i=m;
        int j=n;
        int buf;
        while(i!=0 && j!=0)
        {
            buf=arr[i][j-1];
            if(arr[i][j]>arr[i][j-1])
            {
                result.insert(0,',');
                result.insert(0,one.charAt(j-1));
                result.insert(0,'+');
                j--;
            } else if (arr[i][j]>arr[i-1][j]) {
                result.insert(0,',');
                result.insert(0,two.charAt(i-1));
                result.insert(0,'-');
                i--;
            } else if (one.charAt(j-1)==two.charAt(i-1)) {
                result.insert(0,',');
                result.insert(0,'#');
                i--;
                j--;
            } else if (arr[i][j]>=arr[i-1][j-1]) {
                result.insert(0,',');
                result.insert(0,one.charAt(i-1));
                result.insert(0,'~');
                i--;
                j--;
            }
        }
        while(i!=0)
        {
            result.insert(0,',');
            result.insert(0,two.charAt(i-1));
            result.insert(0,'-');
            i--;
        }
        while(j!=0)
        {
            result.insert(0,',');
            result.insert(0,one.charAt(j-1));
            result.insert(0,'+');
            j--;
        }



        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
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
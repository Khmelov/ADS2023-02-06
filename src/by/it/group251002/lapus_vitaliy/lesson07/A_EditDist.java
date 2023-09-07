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

    int leven(int i, int j,int[][] arr,String one, String two)
    {
        if(arr[i][j]>99999)
        {
            if(i==0)
            {
                arr[i][j]=j;
            }
            else if(j==0)
            {
                arr[i][j]=i;
            }
            else
            {
                int a=leven(i,j-1,arr,one,two)+1;
                int b=leven(i-1,j,arr,one,two)+1;
                int c;
                if(one.charAt(j-1)==two.charAt(i-1))
                {
                    c=leven(i-1,j-1,arr,one,two);
                }
                else {
                    c = leven(i - 1, j - 1, arr,one,two) +1;
                }
                arr[i][j]=Math.min(a,Math.min(b,c));
            }
        }
        return arr[i][j];
    }
    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        int n=one.length();
        int m=two.length();
        int[][] arr=new int[m+1][n+1];
        for(int i=1;i<m+1;i++)
        {
            for(int j=1;j<n+1;j++)
            {
                arr[i][j]=100000;
            }
        }
        for(int i=0;i<n+1;i++)
        {
            arr[0][i]=i;
        }
        for(int i=0;i<m+1;i++)
        {
            arr[i][0]=i;
        }

        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {
                arr[i][j]=leven(i,j,arr,one,two);
            }
        }
        int result;
        result=arr[m][n];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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

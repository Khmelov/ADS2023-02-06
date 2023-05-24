package by.it.group251002.kulik.lesson07;
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
    Итерационно вычислить расстояние редактирования двух данных непустых строк
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

public class B_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n=one.length();
        int m=two.length();
        int[][] arr=new int[2][n+1];
        for(int i=0;i<n+1;i++)
        {
            arr[0][i]=i;
        }
        arr[1][0]=1;

        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {
                arr[1][j]=arr[1][j-1]+1;
                if(arr[1][j]>arr[0][j])
                {
                    arr[1][j]=arr[0][j]+1;
                }
                if (one.charAt(j-1)==two.charAt(i-1))
                {
                    if(arr[1][j]>arr[0][j-1])
                    {
                        arr[1][j]=arr[0][j-1];
                    }
                }
                else
                {
                    if(arr[1][j]>arr[0][j-1]+1)
                    {
                        arr[1][j]=arr[0][j-1]+1;
                    }
                }
            }
            arr[0]=arr[1].clone();
            arr[1][0]=arr[0][0]+1;
        }
        int result;
        result= arr[0][n];


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
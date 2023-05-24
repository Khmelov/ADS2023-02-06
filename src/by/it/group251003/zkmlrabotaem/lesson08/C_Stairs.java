package by.it.group251003.zkmlrabotaem.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Даны число 1<=n<=100 ступенек лестницы и
целые числа −10000<=a[1],…,a[n]<=10000, которыми помечены ступеньки.
Найдите максимальную сумму, которую можно получить, идя по лестнице
снизу вверх (от нулевой до n-й ступеньки), каждый раз поднимаясь на
одну или на две ступеньки.
Sample Input 1:
2
1 2
Sample Output 1:
3
Sample Input 2:
2
2 -1
Sample Output 2:
1
Sample Input 3:
3
-1 2 1
Sample Output 3:
3
*/

public class C_Stairs {

    int getMaxSum(InputStream stream ) {
        Scanner scanner = new Scanner(stream);
        int n=scanner.nextInt();
        int s[]=new int[n+1];
        for (int i = 1; i <= n; i++) {
            s[i]=scanner.nextInt();
            s[0]=0;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int cur=0;
        int result = 0;
        int[]m=new int[n+1];
        m[1]=s[0];
        for (int i=2;i<=n;i++)
        {


            if (m[i-1]>m[i-2])
            {
                m[i]=m[i-1]+s[i];

            }
            else{
                m[i]=m[i-2]+s[i];

            }
        }
        result=m[n];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res=instance.getMaxSum(stream);
        System.out.println(res);
    }

}
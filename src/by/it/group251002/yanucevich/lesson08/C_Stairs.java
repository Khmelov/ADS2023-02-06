package by.it.group251002.yanucevich.lesson08;

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
        int stairs[]=new int[n];
        for (int i = 0; i < n; i++) {
            stairs[i]=scanner.nextInt();
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int result = 0;

        int[] sums = new int[n];
        for (int i=0;i<n;i++){
            if(i>1) {
                sums[i]=stairs[i]+((sums[i-1]<sums[i-2])?sums[i-2]:sums[i-1]);
            }
            else{
                if(i==0){
                    sums[i]=stairs[i];
                }
                else{
                    sums[i]=stairs[i]+((sums[i-1]<0)?0:sums[i-1]);
                }
            }
        }/*
        int i=n-1;
        while(i>1){
            result+=stairs[i];
            if((stairs[i-1]>=0)&&(stairs[i-2]>=0)){
                i--;
            }
            else{
                if(stairs[i-1]<stairs[i-2]){
                    i-=2;
                }
                else{
                    i--;
                }
            }
        }
        result+=stairs[i];
        if((i==1)&&(stairs[i-1]>=0)){
            result+=stairs[0];
        }
*/
        result=sums[n-1];
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

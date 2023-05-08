package by.it.group251002.yanucevich.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак с повторами

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        сколько есть вариантов золотых слитков
                     (каждый можно использовать множество раз).
Следующая строка содержит n целых чисел, задающих веса слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.


Sample Input:
10 3
1 4 8
Sample Output:
10

Sample Input 2:

15 3
2 8 16
Sample Output 2:
14

*/

public class A_Knapsack {
    int getMax(int[] Arr, int max){
        int result=0;
        for(int i=0; i< Arr.length;i++){
            if((result<Arr[i])&&(Arr[i]<=max)){
                result=Arr[i];
            }
        }
        return result;
    }
    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int w=scanner.nextInt();
        int n=scanner.nextInt();
        int gold[]=new int[n];
        for (int i = 0; i < n; i++) {
            gold[i]=scanner.nextInt();
        }
        int[] possibleSacks = new int[w+1];
        for (int i=0;i<=w;i++){
            possibleSacks[i]=0;
            for(int j=0;j<n;j++){
                if (gold[j]<=i){
                    possibleSacks[i]=Integer.max(possibleSacks[i],possibleSacks[i-gold[j]]+gold[j]);
                }
            }
        }


/*
        int[] sacks = new int[w+1];
        sacks[0]=0;
        int maxCriteria, opt;
        for(int i=1;i<=w;i++){
            sacks[i]=0;
            for(int j=0;j<i;j++){
                maxCriteria=(i-sacks[j]);
                opt=getMax(gold,maxCriteria);
                if(sacks[i]<opt+sacks[j]){
                    sacks[i]=opt+sacks[j];
                }
            }
        }
*/
        int result = possibleSacks[w];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }
}

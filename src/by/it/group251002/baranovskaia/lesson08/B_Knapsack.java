package by.it.group251002.baranovskaia.lesson08;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак без повторов

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        число золотых слитков
                    (каждый можно использовать только один раз).
Следующая строка содержит n целых чисел, задающих веса каждого из слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.

Sample Input:
10 3
1 4 8
Sample Output:
9

*/

public class B_Knapsack {

    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int w=scanner.nextInt();
        int n=scanner.nextInt();
        int gold[]=new int[n];
        for (int i = 0; i < n; i++) {
            gold[i]=scanner.nextInt();
        }

        //Each array cell with index i contains 1 if it's possible to fill knapsack in completely with gold bars we have
        //if knapsack would have capacity of i, 0 otherwise
        int[] arr = new int[w + 1];

        arr[0] = 1; //With 0 knapsack capacity it's always possible to fill it in completely

        for (int i = 0; i < gold.length; ++i) //All gold bars
            for(int j = w; j >= gold[i]; --j) //All positions from max weight to weight of current gold bar
                if (arr[j - gold[i]] == 1) //If we have space for new gold bar then set it in array
                    arr[j] = 1;

        //Watching from the end of array, first 1 we meet is our answer
        int result = 0;
        for(int i = w;  ; --i)
            if (arr[i] > 0) {
                result = i;
                break;
            }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }

}

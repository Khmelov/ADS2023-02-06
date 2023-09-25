package by.it.group251001.zhidkov.lesson08;

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
    int getMaxSum(InputStream stream) {

        // Чтение входных данных
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt(); // Число ступенек лестницы
        int stairs[] = new int[n]; // Числа, которыми помечены ступеньки
        for (int i = 0; i < n; i++) {
            stairs[i] = scanner.nextInt();
        }
        int[] sum = new int[n + 1];
        sum[0] = 0;
        sum[1] = stairs[0];

        // Заполнение массива сумм методом динамического программирования
        for (int i = 2; i <= n; i++)
            sum[i] = Math.max(stairs[i - 1] + sum[i - 1], stairs[i - 1] + sum[i - 2]);

        int result = sum[n]; // Максимальная сумма

        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson08/dataC.txt");
        C_Stairs instance = new C_Stairs();
        int res = instance.getMaxSum(stream);
        System.out.println(res);
    }
}


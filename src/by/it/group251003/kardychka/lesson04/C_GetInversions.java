package by.it.group251003.kardychka.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {
    private int numberInversions = 0;
    private void mergeSort(int[] nums, int left, int right){
        if (left == right)
            return;

        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
        return;
    }
    private void merge(int[] nums, int left, int mid, int right){
        int[] sorted = new int[right+1];
        int leftArrInd = left;
        int rightArrInd = mid + 1;
        int numsArrInd = 0;

        while ((leftArrInd <= mid) && (rightArrInd <= right)){
            if (nums[leftArrInd] <= nums[rightArrInd]) {
                sorted[numsArrInd++] = nums[leftArrInd++];
                numberInversions += rightArrInd - mid - 1;
            }
            else
                sorted[numsArrInd++] = nums[rightArrInd++];
        }
        while (leftArrInd <= mid) {
            sorted[numsArrInd++] = nums[leftArrInd++];
            numberInversions += rightArrInd - mid - 1;
        }
        while (rightArrInd <= right)
            sorted[numsArrInd++] = nums[rightArrInd++];

        for (int i = left; i <= right; i++)
            nums[i] = sorted[i - left];
        return;
    }
    int calc(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        mergeSort(nums, 0, nums.length-1);
        return numberInversions;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}

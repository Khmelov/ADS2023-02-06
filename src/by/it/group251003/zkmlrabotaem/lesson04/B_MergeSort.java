package by.it.group251003.zkmlrabotaem.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {
    private void mergeSort(int[] nums, int left, int right){
        if (right == left)
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
        int rightArrInd = mid+1;
        int numsArrInd = 0;

        while ((leftArrInd <= mid) && (rightArrInd <= right)){
            if (nums[leftArrInd] <= nums[rightArrInd])
                sorted[numsArrInd++] = nums[leftArrInd++];
            else
                sorted[numsArrInd++] = nums[rightArrInd++];
        }
        while (leftArrInd <= mid)
            sorted[numsArrInd++] = nums[leftArrInd++];
        while (rightArrInd <= right)
            sorted[numsArrInd++] = nums[rightArrInd++];
        for (int i = left; i <= right; i++)
            nums[i] = sorted[i - left];
        return;
    }
    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
            System.out.println(nums[i]);
        }
        mergeSort(nums, 0, nums.length-1);
        return nums;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataB.txt");
        by.it.group251003.zkmlrabotaem.lesson04.B_MergeSort instance = new by.it.group251003.zkmlrabotaem.lesson04.B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }


}

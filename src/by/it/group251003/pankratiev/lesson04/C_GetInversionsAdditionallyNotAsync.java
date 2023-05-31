package by.it.group251003.pankratiev.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_GetInversionsAdditionallyNotAsync {
    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!

        result = mergeSort(a.clone(), 0, n - 1);

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int mergeSort(int[] array, int left, int right) {
        int result = 0;
        if (right > left) {
            int mid = left + (right - left) / 2;
            result += mergeSort(array, left, mid);
            result += mergeSort(array, mid + 1, right);
            result += merge(array, left, mid, right);
        }
        return result;
    }

    private int merge(int[] array, int left, int mid, int right) {

        int result = 0;

        int[] temp = new int[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right)
            if (array[i] > array[j]) {
                temp[k++] = array[j++];
                result += mid - i + 1;
            }
            else
                temp[k++] = array[i++];

        while (i <= mid)
            temp[k++] = array[i++];

        while (j <= right)
            temp[k++] = array[j++];

        System.arraycopy(temp, 0, array, left, temp.length);

        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/group251003/pankratiev/lesson04/dataC.txt");
        C_GetInversionsAdditionallyNotAsync instance = new C_GetInversionsAdditionallyNotAsync();
        long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        long finishTime = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(finishTime - startTime);
    }
}

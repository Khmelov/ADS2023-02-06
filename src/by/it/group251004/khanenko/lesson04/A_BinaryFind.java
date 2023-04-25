package by.it.group251004.khanenko.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_BinaryFind {
    int[] findIndex(InputStream stream) throws FileNotFoundException {
        Scanner scanner = new Scanner(stream);

        int sizeSortedArr = scanner.nextInt();
        int[] arr = new int[sizeSortedArr];
        for (int i = 0; i < sizeSortedArr; i++) {
            arr[i] = scanner.nextInt();
        }
        int sizeArrOfIndexes = scanner.nextInt();
        int[] result = new int[sizeArrOfIndexes];
        for (int i = 0; i < sizeArrOfIndexes; i++) {
            int value = scanner.nextInt();
            int left = 0;
            int right = sizeSortedArr - 1;
            result[i] = -1;
            do{
                int mid = (left + right) / 2;
                if (arr[mid] == value)
                {
                    result[i] = mid + 1;
                    break;
                }
                else
                if (value > arr[mid])
                    left = mid;
                else
                    right = mid;
            } while (right - left > 1);

            if (arr[left] == value)
                result[i] =  left + 1;
            else if (arr[right] == value)
                result[i] = right  + 1;
        }
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataA.txt");
        A_BinaryFind instance = new A_BinaryFind();
        //long startTime = System.currentTimeMillis();
        int[] result=instance.findIndex(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index:result){
            System.out.print(index+" ");
        }
    }

}

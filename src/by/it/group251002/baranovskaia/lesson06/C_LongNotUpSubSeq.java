package by.it.group251002.baranovskaia.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: наибольшая невозростающая подпоследовательность

Дано:
    целое число 1<=n<=1E5 ( ОБРАТИТЕ ВНИМАНИЕ НА РАЗМЕРНОСТЬ! )
    массив A[1…n] натуральных чисел, не превосходящих 2E9.

Необходимо:
    Выведите максимальное 1<=k<=n, для которого гарантированно найдётся
    подпоследовательность индексов i[1]<i[2]<…<i[k] <= длины k,
    для которой каждый элемент A[i[k]] не больше любого предыдущего
    т.е. для всех 1<=j<k, A[i[j]]>=A[i[j+1]].

    В первой строке выведите её длину k,
    во второй - её индексы i[1]<i[2]<…<i[k]
    соблюдая A[i[1]]>=A[i[2]]>= ... >=A[i[n]].

    (индекс начинается с 1)

Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ

    Sample Input:
    5
    5 3 4 4 2

    Sample Output:
    4
    1 3 4 5
*/


public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //общая длина последовательности
        int n = scanner.nextInt();
        int[] m = new int[n];
        //читаем всю последовательность
        for (int i = 0; i < n; i++) {
            m[i] = scanner.nextInt();
        }
        //тут реализуйте логику задачи методами динамического программирования (!!!)
        int result = 0;

        int[] maxSequences = new int[n]; //Array contains maximum length of sequences, ended by element with corresponding index
        int[] previousSequenceElements = new int[n]; //Each element of array contains index of the largest previous sequence element
        int[] indexesPrint = new int[n]; //Contains final indexes of sequence to output in reverse order
        int lastMaxSequenceElementIndex = 0;

        for (int i = 0; i < n; ++i) {
            maxSequences[i] = 1; //Minimum length of sequence
            for (int j = 0; j < i; ++j) {

                //If last sequence element greater or equal to current - update maximum length of sequence ended by current element if it's reasonable
                if (m[j] >= m[i] && maxSequences[j] + 1 > maxSequences[i]) {
                    maxSequences[i] = maxSequences[j] + 1;
                    previousSequenceElements[i] = j;
                }

            }

            //If we got bigger sequence - update maximum size and update last element of the largest sequence
            if (result < maxSequences[i]) {
                result = maxSequences[i];
                lastMaxSequenceElementIndex = i;
            }
        }

        //Here array with indexes to output is filled in reverse order
        int count = 0;
        int i = lastMaxSequenceElementIndex;
        while (previousSequenceElements[i] > 0 || i != 0){
            indexesPrint[count] = i + 1;
            i = previousSequenceElements[i];
            ++count;
        }
        if (previousSequenceElements[i] == 0 && i == 0){
            indexesPrint[count] = 1;
            ++count;
        }

        //Print indexes
        for (int j = count - 1; j >= 0; --j)
            System.out.print(indexesPrint[j] + " ");


        System.out.println("\n");

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }

}
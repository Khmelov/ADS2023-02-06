package by.it.group251002.baranovskaia.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Arrays;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Рекурсивно вычислить расстояние редактирования двух данных непустых строк

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    0

    Sample Input 2:
    short
    ports
    Sample Output 2:
    3

    Sample Input 3:
    distance
    editing
    Sample Output 3:
    5

*/

public class A_EditDist {


    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        final int N = one.length() + 1;
        final int M = two.length() + 1;
        int[][] distancesTable = new int[N][M];

        //Fill distancesTable with (-1)
        for (int i = 0; i < N; ++i)
            Arrays.fill(distancesTable[i], -1);

        editDistTD(distancesTable, one, two, N - 1, M - 1);

        int result = distancesTable[N - 1][M - 1];

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int editDistTD(int[][] distancesTable, String one, String two, int i, int j){
        if (distancesTable[i][j] == -1) //If cell have to be filled
            if (i == 0) // If it's 1-st raw, just fill it with string indexes[0..two.length()]
                distancesTable[i][j] = j;
            else if (j == 0) // If it's 1-st raw, just fill it with string indexes[0..one.length()]
                distancesTable[i][j] = i;
            else {
                int ins = editDistTD(distancesTable, one, two, i, j - 1) + 1; //If insert element - take left one form the table + 1
                int del = editDistTD(distancesTable, one, two, i - 1, j) + 1; //If delete element - take one above from the table + 1
                int sub = editDistTD(distancesTable, one, two, i - 1, j - 1) + ((one.charAt(i-1) == two.charAt(j-1)) ? 0 : 1); //If change element - take one from diagonal + 1, if it's not the same element
                distancesTable[i][j] = Math.min(Math.min(ins, del), sub); //Take minimum of values above
            }
        return distancesTable[i][j];
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        A_EditDist instance = new A_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }
}

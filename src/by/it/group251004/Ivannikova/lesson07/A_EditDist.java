package by.it.group251004.Ivannikova.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

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

    int findMinOfThree(int a, int b, int c) {
        return Integer.min(Integer.min(a, b), c);
    }

    int findDist(String s1, String s2, int m, int n) {
        if (m == 0 && n == 0)
            return 0;
        if (m == 0)
            return n;
        if (n == 0)
            return m;
        int term = s1.charAt(m - 1) == s2.charAt(n - 1) ? 0 : 1;
        return findMinOfThree(findDist(s1, s2, m, n - 1) + 1, //insert
                findDist(s1, s2, m - 1, n) + 1, //delete
                findDist(s1, s2, m - 1, n - 1) + term); //change
    }
    int getDistanceEdinting(String s1, String s2) {
        return findDist(s1, s2, s1.length(), s2.length());
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

package by.it.group251001.shyrynski.lesson07;

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
    public static int CountD(int a, int b, String word1, String word2) {

        if (a == 0 || b == 0) {
            return a + b;
        }

        int result = CountD(a - 1, b, word1, word2) + 1;
        if (result > CountD(a, b - 1, word1, word2) + 1) {
            result = CountD(a, b - 1, word1, word2) + 1;
        }

        int tmp = CountD(a - 1, b - 1, word1, word2);
        if (word1.charAt(a - 1) != word2.charAt(b - 1)) {
            tmp++;
        }

        if (tmp < result) {
            result = tmp;
        }
        return result;
    }

    int getDistanceEdinting(String one, String two) {
        return CountD(two.length(), one.length(), two, one);
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
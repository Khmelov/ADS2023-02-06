package by.it.group251001.besedinAndrei.lesson07;

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
    Итерационно вычислить расстояние редактирования двух данных непустых строк

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

public class B_EditDist {

    int getDistanceEdinting(String one, String two) {
        // !!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] matrix = new int[one.length() + 1][two.length() + 1];

        for (int i = 0; i <= two.length(); i++) {
            matrix[0][i] = i;
        }

        for (int j = 0; j <= one.length(); j++) {
            matrix[j][0] = j;
        }

        for (int i = 1; i <= one.length(); i++) {
            for (int j = 1; j <= two.length(); j++) {

                matrix[i][j] = matrix[i - 1][j] + 1;

                if (matrix[i][j - 1] + 1 < matrix[i][j]) {
                    matrix[i][j] = matrix[i][j - 1] + 1;
                }

                int temp = matrix[i - 1][j - 1];

                if (one.charAt(i - 1) != two.charAt(j - 1)) {
                    temp++;
                }

                if (temp < matrix[i][j]) {
                    matrix[i][j] = temp;
                }
            }
        }

        return matrix[one.length()][two.length()];
        // !!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}
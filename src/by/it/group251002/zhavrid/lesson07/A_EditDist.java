package by.it.group251002.zhavrid.lesson07;

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


    int getDistanceEdinting(String one, String two) {
        int[][] d = new int[one.length() + 1][two.length() + 1];

        // инициализируем первую строку и первый столбец
        for (int i = 0; i <= one.length(); i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <= two.length(); j++) {
            d[0][j] = j;
        }

        // заполняем массив по формуле
        for (int j = 1; j <= two.length(); j++) {
            for (int i = 1; i <= one.length(); i++) {
                int insertion = d[i][j - 1] + 1;
                int deletion = d[i - 1][j] + 1;
                int match = d[i - 1][j - 1];
                int mismatch = d[i - 1][j - 1] + 1;

                if (one.charAt(i - 1) == two.charAt(j - 1)) {
                    d[i][j] = Math.min(Math.min(insertion, deletion), match);
                } else {
                    d[i][j] = Math.min(Math.min(insertion, deletion), mismatch);
                }
            }
        }

        // возвращаем ответ
        return d[one.length()][two.length()];
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
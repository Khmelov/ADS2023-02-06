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
        int n = one.length();
        int m = two.length();

        // Создаем матрицу
        int[][] d = new int[n+1][m+1];

        // Заполняем первый столбец и первую строку
        for (int i = 0; i <= n; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <= m; j++) {
            d[0][j] = j;
        }

        // Заполняем оставшиеся ячейки
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int diff = one.charAt(i-1) == two.charAt(j-1) ? 0 : 1;
                d[i][j] = Math.min(
                        d[i-1][j] + 1,     // удаление символа из строки one
                        Math.min(
                                d[i][j-1] + 1,     // вставка символа в строку one
                                d[i-1][j-1] + diff // замена символа в строке one на символ в строке two
                        )
                );
            }
        }

        // Возвращаем результат - значение в ячейке (n, m)
        return d[n][m];
    }



    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        B_EditDist instance = new B_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
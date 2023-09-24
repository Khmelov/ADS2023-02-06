package by.it.group251003.zkmlrabotaem.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
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
    public static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] arr = new int[one.length() + 1][two.length() + 1];

        for (int x = 0; x <= one.length(); x++) {
            for (int y = 0; y <= two.length(); y++) {
                if (x==0&&y==0)
                {
                    arr[x][y]=0;
                }
                else if (x == 0) {
                    arr[x][y] = y;
                }
                else if (y == 0) {
                    arr[x][y] = x;
                }
                else {
                    arr[x][y] = min(arr[x - 1][y - 1] + (one.charAt(x - 1) == two.charAt(y - 1) ? 0 : 1), arr[x - 1][y] + 1, arr[x][y - 1] + 1);
                }
            }
        }


        int result = arr[one.length()][two.length()];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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
package by.it.group251001.litvinovich.lesson07;

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


    int getMin(int i, int j, int[][] matr) {
        int min = Math.min(matr[i - 1][j], matr[i][j - 1]);
        min = Math.min(min, matr[i - 1][j - 1]);
        return min;
    }
    int getCount(String one, String two, int i, int j, int[][] matr) {
        if(one.charAt(i - 1) == two.charAt(j - 1))
            return matr[i - 1][j - 1];
        else
            return 1 + getMin(i, j, matr);
    }
    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] matr = new int[one.length() + 1][two.length() + 1];
        for(int i = 0; i < one.length() + 1; i++) {
            matr[i][0] = i;
        }
        for(int i = 0; i < two.length() + 1; i++) {
            matr[0][i] = i;
        }
        for(int i = 1; i < one.length() + 1; i++) {
            for(int j = 1; j < two.length() + 1; j++) {
                matr[i][j] = getCount(one, two, i, j, matr);
            }
        }
        int result = matr[one.length()][two.length()];
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
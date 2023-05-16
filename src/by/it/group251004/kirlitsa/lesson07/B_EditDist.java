package by.it.group251004.kirlitsa.lesson07;

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


    int isCharEqual(Character a, Character b) {
        return a.equals(b) ? 0 : 1;
    }

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] arrAnswer = new int[one.length() + 1][two.length() + 1];
        for (int i = 0; i < arrAnswer.length; i++)
            arrAnswer[i][0] = i;
        for (int i = 0; i < arrAnswer[0].length; i++)
            arrAnswer[0][i] = i;
        for (int i = 1; i < arrAnswer.length; i++)
            for (int j = 1; j < arrAnswer[0].length; j++) {
                int diff = isCharEqual(one.charAt(i - 1), two.charAt(j - 1));
                arrAnswer[i][j] = Math.min(arrAnswer[i - 1][j] + 1, Math.min(arrAnswer[i][j - 1] + 1, arrAnswer[i - 1][j - 1] + diff));
            }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return arrAnswer[one.length()][two.length()];
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
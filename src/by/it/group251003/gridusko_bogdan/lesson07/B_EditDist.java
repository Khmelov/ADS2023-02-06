package by.it.group251003.gridusko_bogdan.lesson07;

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
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int minLength = Math.min(one.length(), two.length()) + 1;
        int maxLength = two.length() + one.length() - (minLength - 1) + 1;
        if (one.length() > two.length()){
            String tmp = one;
            one = two;
            two = tmp;
        }
        int prevLine[] = new int[minLength];
        int currLine[] = new int[minLength];

        for (int i = 0; i < minLength; i++)
            prevLine[i] = i;
        for (int i = 1; i < maxLength; i++) {
            currLine[0] = i;
            for (int j = 1; j < minLength; j++) {
                currLine[j] = Math.min(Math.min(prevLine[j], currLine[j-1]) + 1,
                        prevLine[j - 1] + (one.charAt(j-1) == two.charAt(i-1)? 0 : 1));
            }
            for (int j = 0; j < minLength; j++)
                prevLine[j] = currLine[j];
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return currLine[minLength-1];
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
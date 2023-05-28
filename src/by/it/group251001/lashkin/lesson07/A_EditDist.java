package by.it.group251001.lashkin.lesson07;

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

    int levenshtein(int l1, String one, int l2, String two){
        if (l1 == 0) return l2;
        else
        if (l2 == 0) return l1;
        else
        if (l1 == 1 && l2 == 1 && one.charAt(0) == two.charAt(0)) return 0;
        else
        if (l1 == 1 && l2 == 1 && one.charAt(0) != two.charAt(0)) return 1;
        else
            return Math.min(Math.min(levenshtein(l1 - 1,one,l2,two) + 1,levenshtein(l1,one,l2 - 1,two) + 1),levenshtein(l1 - 1,one,l2 - 1,two) + ((one.charAt(l1 - 1) == two.charAt(l2 - 1))?0:1));
    }
    int getDistanceEdinting(String one, String two) {
        return levenshtein(one.length(), one, two.length(), two);
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
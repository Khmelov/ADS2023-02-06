package by.it.group251004.shmargun.lesson07;

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

    public static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }

    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        //Если строка one пустая (isEmpty()), то возвращается длина строки two
        if (one.isEmpty()) {
            return two.length();
        }

        //Если строка two пустая, то возвращается длина строки one
        if (two.isEmpty()) {
            return one.length();
        }
        //Операция замены (substitution): Вызывается функция getDistanceEdinting для подстрок one.substring(1)
        // и two.substring(1), а затем добавляется 1, если первые символы строк one и two не совпадаю
        int substitution = getDistanceEdinting(one.substring(1), two.substring(1)) + (one.charAt(0) == two.charAt(0) ? 0 : 1);
        //для строки one и подстроки two.substring(1), а затем добавляется 1, так как требуется вставить символ из two в one
        int insertion = getDistanceEdinting(one, two.substring(1)) + 1;
//Вызывается функция getDistanceEdinting для подстроки one.substring(1) и строки two,
// а затем добавляется 1, так как требуется удалить символ из one
        int deletion = getDistanceEdinting(one.substring(1), two) + 1;
//возвращает минимальное значение из трех операций, используя вспомогательную функцию min
        return min(substitution, insertion, deletion);
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

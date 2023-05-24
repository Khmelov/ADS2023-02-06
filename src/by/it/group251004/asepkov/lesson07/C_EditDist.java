package by.it.group251004.asepkov.lesson07;
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
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки
    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,
    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,
    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,
    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    int isCharEqual(Character a, Character b) {
        return a.equals(b) ? 0 : 1;
    }

    String getDistanceEdinting(String one, String two) {
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

        StringBuffer result = new StringBuffer();
        int i = one.length();
        int j = two.length();
        while (i > 0 && j > 0) {
            int tempDiff = isCharEqual(one.charAt(i - 1), two.charAt(j - 1));
            char symbol = (one.charAt(i - 1) == two.charAt(j - 1)) ? '#' : '~';
            if (arrAnswer[i][j] == arrAnswer[i - 1][j - 1] + tempDiff) {
                result.insert(0, ',');
                result.insert(0, symbol);
                if (symbol == '~') result.insert(1, two.charAt(j - 1));
                i--; j--;
            } else if (arrAnswer[i][j] == arrAnswer[i - 1][j] + 1) {
                result.insert(0, ',');
                result.insert(0, one.charAt(i-- - 1));
                result.insert(0, '-');

            } else {
                result.insert(0, ',');
                result.insert(0, two.charAt(j-- - 1));
                result.insert(0, '+');
            }
        }
        while(i > 0) {
            result.insert(0, ',');
            result.insert(0, one.charAt(i-- - 1));
            result.insert(0, '-');
        }
        while(j > 0) {
            result.insert(0, ',');
            result.insert(0, two.charAt(j-- - 1));
            result.insert(0, '+');
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}
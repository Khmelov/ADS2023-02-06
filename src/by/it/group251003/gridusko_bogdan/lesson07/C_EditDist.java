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

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int minLength = Math.min(one.length(), two.length()) + 1;
        int maxLength = two.length() + one.length() - (minLength - 1)+1;
        if (one.length() > two.length()){
            String tmp = one;
            one = two;
            two = tmp;
        }
        int[][] table = new int[maxLength][minLength];
        for (int i = 0; i < minLength; i++){
            table[0][i] = i;
        }

        for (int i = 1; i < maxLength; i++){
            table[i][0] =  i;
            for (int j = 1; j < minLength; j++){
                table[i][j] = Math.min(Math.min(table[i-1][j], table[i][j-1]) + 1,
                        table[i-1][j - 1] + (one.charAt(j-1) == two.charAt(i-1)? 0 : 1));
            }
        }
        StringBuilder result = new StringBuilder("");
        int i = maxLength - 1;
        int j = minLength - 1;
        while (i > 0 && j > 0){
            if (table[i-1][j] + 1 == table[i][j]){
                result.append("," + two.charAt(j - 1) + "+");
                i--;
            }
            else if (table[i][j - 1] + 1 == table[i][j]){
                result.append("," + two.charAt(j - 1) + "-");
                j--;
            }
            else if(table[i - 1][j - 1] == table[i][j]){
                result.append("," + "#");
                i--;
                j--;
            }
            else {
                result.append("," + two.charAt(j - 1) + "~");
                j--;
                i--;
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result.reverse().toString();
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
package by.it.group251001.sidorovich.lesson07;

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
int minimum(int a1,int a2,int a3){
    int min = a1;
    if (a2 < min){
        min = a2;
    }
    if (a3 < min){
        min = a3;
    }
    return min;
    }
    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int l1 = one.length();
        int l2 = two.length();
        int[][] arr = new int[l1 + 1][l2 + 1];

        for (int y = 0; y <= l2; y++){
            arr[0][y] = y;
        }
        for (int x = 0; x <= l2; x++){
            arr[x][0] = x;
        }

arr[0][0]=0;

        for (int x = 1; x <= l1; x++) {
            for (int y = 1; y <= l2; y++) {
                int a = arr[x][y - 1] + 1;
                int b = arr[x - 1][y] + 1;
                int c = arr[x - 1][y - 1] + (one.charAt(x - 1) == two.charAt(y - 1) ? 0 : 1);

             //  minimum(a,b,c);
                arr[x][y] = minimum(a,b,c);;
            }
        }

        StringBuilder result = new StringBuilder();

        int x = l1, y = l2;

        while (x != 0 || y != 0){
            if (x > 0 && y > 0 && arr[x - 1][y - 1] + (one.charAt(x - 1) == two.charAt(y - 1) ? 0 : 1) == arr[x][y]){
                if(one.charAt(x - 1) == two.charAt(y - 1)){
                    result.insert(0, "#,");
                }else {
                    result.insert(0, "~" + two.charAt(y - 1) + ",");
                }
                x--;
                y--;
            } else if (y > 0 && arr[x][y] == arr[x][y - 1] + 1) {
                result.insert(0, "+" + two.charAt(y - 1) + ",");
                y--;
            } else if ( x > 0 && arr[x][y] == arr[x - 1][y] + 1) {
                result.insert(0, "-" + one.charAt(x - 1) + ",");
                x--;
            }
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
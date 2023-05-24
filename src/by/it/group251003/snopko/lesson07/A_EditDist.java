package by.it.group251003.snopko.lesson07;

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
    int diff (char a, char b){
        if (a != b ) return 1;
        return 0;
    }
    int GetDist (int i, int j, String one, String two){
        if (i == 0) {return j;} else
            if (j == 0) {return i;} else
            {
                int ins = 0, del = 0, sub = 0;
                ins = GetDist (i, j - 1, one, two)  + 1;
                del = GetDist (i - 1, j, one, two) + 1;
                sub = GetDist (i - 1, j  - 1, one, two)  + diff(one.charAt(i - 1), two.charAt(j - 1));
                return Math.min(ins, Math.min(del,sub));
            }

    }

    int getDistanceEdinting(String one, String two) {

        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n = one.length(), m = two.length();
        int dp[][] = new int[n + 1][m + 1];
        for (int i = 0; i < n + 1; i++)
            for (int j = 0; j < m + 1; j++)
                dp[i][j] = Integer.MAX_VALUE;
        for (int i = 0; i < n + 1; i++){
            for (int j = 0; j < m + 1; j++){
                if(dp[i][j] == Integer.MAX_VALUE) {dp[i][j] = GetDist (i,j,one,two);}
            }
        }

        int result = 0;
        result = dp[n][m];
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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

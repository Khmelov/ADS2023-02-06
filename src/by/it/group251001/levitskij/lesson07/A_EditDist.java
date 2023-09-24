package by.it.group251001.levitskij.lesson07;

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


    void calc(String one, String two, int[][] a, int i, int j){
       if (a[i-1][j]==-1){
           calc(one, two, a, i-1, j);
       }
       if (a[i][j-1]==-1){
            calc(one, two, a, i, j-1);
       }
       if (a[i-1][j-1]==-1){
            calc(one, two, a, i-1, j-1);
       }
       int dif = (one.charAt(i-1)==two.charAt(j-1))?0:1;
       a[i][j] = Integer.min(a[i-1][j]+1, Integer.min(a[i][j-1]+1, a[i-1][j-1]+dif));
    }
    int getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int[][] matrix = new int[one.length()+1][two.length()+1];
        for (int i = 0; i < one.length()+1; i++){
            matrix[i][0] = i;
        }
        for (int j = 0; j < two.length()+1; j++){
            matrix[0][j] = j;
        }
        for (int i = 1; i < one.length()+1; i++) {
            for (int j = 1; j < two.length() + 1; j++) {
                matrix[i][j] = -1;
            }
        }
        calc(one, two, matrix, one.length(), two.length());
        int result = matrix[one.length()][two.length()];
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

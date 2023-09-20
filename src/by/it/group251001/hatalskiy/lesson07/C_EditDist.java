package by.it.group251001.hatalskiy.lesson07;
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
    # #

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
        int M = one.length();
        int N = two.length();
        int[][] D = new int[M + 1][N + 1];
        D[0][0] = 0;
        for (int j = 1; j <= N; j++)
            D[0][j] = D[0][j - 1] + 1;
        for (int i = 1; i <= M; i++) {
            D[i][0] = D[i - 1][0] + 1;
            for (int j = 1; j <= N; j++){
                D[i][j] = Math.min(Math.min(D[i-1][j] + 1,D[i][j - 1] + 1), D[i - 1][j - 1] + ((one.charAt(i - 1) == two.charAt(j - 1))?0:1) );
            }
        }
        String result = "";

        int i = M;
        int j = N;
        while (i >= 1 && j >= 1){
            if (Math.min(Math.min(D[i-1][j] + 1,D[i][j - 1] + 1), D[i - 1][j - 1] + ((one.charAt(i - 1) == two.charAt(j - 1))?0:1))== D[i-1][j] + 1 ){
                result = "-" + one.charAt(i - 1) + " " + result;
                i--;}
            else if (Math.min(Math.min(D[i-1][j] + 1,D[i][j - 1] + 1), D[i - 1][j - 1] + ((one.charAt(i - 1) == two.charAt(j - 1))?0:1))== D[i][j - 1] + 1 ){
                result = "+" + two.charAt(j - 1) + " " + result;
                j--;}
            else if (Math.min(Math.min(D[i-1][j] + 1,D[i][j - 1] + 1), D[i - 1][j - 1] + ((one.charAt(i - 1) == two.charAt(j - 1))?0:1)) == D[i - 1][j - 1] + ((one.charAt(i - 1) == two.charAt(j - 1))?0:1)){
                if (one.charAt(i - 1) == two.charAt(j - 1)){
                    result = "#," + result;
                    j--;
                    i--;}
                else{
                    result = "~" + one.charAt(i - 1) + " " + result;
                    j--;
                    i--;}
            }
        }
        while (i >= 1){
            result = "-" + one.charAt(i - 1) + " " + result;
            i--;
        }
        while (j >= 1){
            result = "-" + two.charAt(j - 1) + " " + result;
            j--;
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
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
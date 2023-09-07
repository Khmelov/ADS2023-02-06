package by.it.group251002.samoilenko.lesson07;

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
    int diff( char on,char tw){
        if (on==tw) return 0;
        else return 1;
    }
    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        int n=one.length();
        int m=two.length();
        int[][] D=new int[n+1][m+1];
        for(int i=0;i<=n;i++){
            D[i][0]=i;
        }
        for(int j=0;j<=m;j++){
            D[0][j]=j;
        }
        int c;
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                c=diff(one.charAt(i-1),two.charAt(j-1));
                D[i][j]=Math.min(Math.min(D[i-1][j]+1,D[i][j-1]+1),D[i-1][j-1]+c);
            }
        }
        int i=n;
        int j=m;
        StringBuilder result = new StringBuilder();
        while(i!=0 && j!=0) {
            if (D[i][j] == D[i - 1][j] +1 ) {
                result.insert(0, "-" + one.charAt(i - 1) + ",");
                if(i!=0)
                i--;
            } else {
                if(D[i][j] == D[i][j-1] + 1 ) {
                    result.insert(0, "+" + two.charAt(j - 1) + ",");
                    if(j!=0)
                    j--;
                }
                else {
                  if (diff(one.charAt(i-1),two.charAt(j-1))==0){
                      result.insert(0,"#,");
                  }
                  else{
                      result.insert(0,"~"+two.charAt(j-1)+",");
                  }
                  if(i!=0)
                  i--;
                  if(j!=0)
                  j--;
                }
            }

        }
        if (i==0 && j==1){
            result.insert(0, "+" + two.charAt(j - 1) + ",");
        }
        if(j==0 && i==1){
            result.insert(0, "-" + one.charAt(i - 1) + ",");
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return String.valueOf(result);
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
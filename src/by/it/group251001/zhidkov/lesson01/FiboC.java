package by.it.group251001.zhidkov.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private final long startTime = System.currentTimeMillis();
    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 999999999;
        int m = 321;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        long[] Arr = new long[10 * m];
        Arr[0] = 0;
        Arr[1] = 1;
        Arr[2] = 1;
        int i = 3;
        boolean flag = true;
        while (flag) {
            Arr[i] = (Arr[i-1] + Arr[i-2]) % m;
            if (Arr[i-1]==Arr[0] && Arr[i]==Arr[1]) {
                flag = false;
            }
            else {
                i++;
            }
        }
        long num = n % (i - 1);

        return Arr[(int)num];
    }
}
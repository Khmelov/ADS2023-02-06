package by.it.group251002.filistovich.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        int F1 = 1, F2=1;
        int count = 2;
        while ((F1 % m !=0) || (F2 % m !=1)){
            F2 = F1+F2;
            F1 = F2-F1;
            F1 %= m;
            F2 %= m;
            count++;
        }
        n %= count;
        for (int i=1; i<=n; i++) {
            F2 = F1+F2;
            F1 = F2-F1;
            F1 %= m;
            F2 %= m;
        }

        return F1;
    }


}


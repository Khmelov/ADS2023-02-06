package by.it.group251001.lashkin.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * Время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private final long startTime = System.currentTimeMillis();

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
        long eps = 0;
        long previousNum = 0;
        long currentNum = 1;
        for(int i = 0; i < m * m; i++) {
            long temp = currentNum;
            currentNum = (previousNum + currentNum) % m;
            previousNum = temp;
            if (previousNum == 0 && currentNum == 1)
                eps= i + 1;
        }
        n %= eps;
        previousNum = 0;
        currentNum = 1;
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        for(int i = 0; i < n - 1; i++) {
            long temp = currentNum;
            currentNum = (previousNum + currentNum) % m;
            previousNum = temp;
        }
        return currentNum % m;
    }

}


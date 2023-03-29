package by.it.group251003.zdanovich.lesson01;

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
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {

        if (n <= 1) {
            return (int) n;
        }

        int[] fib = new int[6 * m];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i < 6 * m; i++) {
            fib[i] = (fib[i - 1] + fib[i - 2]) % m;
            if (fib[i - 1] == 0 && fib[i] == 1) {
                n %= i - 1;
                return fib[(int) n];
            }
        }
        return 0L;
    }


}


package by.it.group251001.shyrynski.lesson01;

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

    private static long[][] matrixMltp(long[][] m1, long[][] m2, int m){
        long[][] mtr = new long[2][2];
        mtr[0][0] = (m1[0][0] * m2[0][0] + m1[0][1] * m2[1][0]) % m;
        mtr[0][1] = (m1[0][0] * m2[0][1] + m1[0][1] * m2[1][1]) % m;
        mtr[1][0] = (m1[1][0] * m2[0][0] + m1[1][1] * m2[1][0]) % m;
        mtr[1][1] = (m1[1][0] * m2[0][1] + m1[1][1] * m2[1][1]) % m;
        return mtr;
    }

    private static long[][] binPowMatrix(long[][] mat, long n, int m){
        if (n == 0)
        {
            long[][] mtr = new long[2][2];
            mtr[0][0] = 1;
            mtr[0][1] = 0;
            mtr[1][0] = 0;
            mtr[1][1] = 1;
            return mtr;
        }
        if (n % 2 == 1)
        {
            long[][] mtr = binPowMatrix(mat, n - 1, m);
            return matrixMltp(mtr, mat, m);
        }
        else
        {
            long[][] mtr = binPowMatrix(mat, n / 2, m);
            return matrixMltp(mtr, mtr, m);
        }
    }

    long fasterC(long n, int m) {
        long[][] mtr = new long[2][2];
        mtr[0][0] = 1;
        mtr[0][1] = 1;
        mtr[1][0] = 1;
        mtr[1][1] = 0;
        return binPowMatrix(mtr, n, m)[0][1];
    }


}


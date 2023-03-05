package by.it.group251001.mikhei.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */

public class FiboC {

    private final long startTime = System.currentTimeMillis();

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 10;
        int m = 2;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    int[][] multiply(int[][] a, int[][] b, int mod) {
        int[][] res = new int[a.length][b[0].length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                    res[i][j] %= mod;
                }
            }
        }

        return res;
    }

    int[][] binPow(int[][] x, long k, int mod) {
        if (k == 0) return new int[][]{{1, 0}, {0, 1}};

        if (k % 2 == 1) return multiply(binPow(x, k - 1, mod), x, mod);

        int[][] res = binPow(x, k / 2, mod);

        return multiply(res, res, mod);
    }

    long fasterC(long n, int m) {
        int[][] res = new int[][]{{0, 1}, {1, 1}};

        res = binPow(res, n - 1, m);

        return res[1][1];
    }


}


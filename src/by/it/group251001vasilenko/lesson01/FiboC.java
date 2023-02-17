package by.it.group251001vasilenko.lesson01;

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

        int[][] fibonacciMatrix = new int[][]{{1, 1},
                {1, 0}};
        return matrixPower(fibonacciMatrix, n , m)[0][1];
    }

    private static int[][] matrixPower(int[][] matrix, long n, int m) {

        if (n == 0) {
            return new int[][] {{1, 0},
                    {0, 1}};
        }

        if (n % 2 == 1) {
            int[][] temp = matrixPower(matrix, n - 1, m);
            return multiplyMatrix(temp, matrix, m);
        }
        else {
            int[][] temp = matrixPower(matrix, n / 2, m);
            return multiplyMatrix(temp, temp, m);
        }
    }
    private static int[][] multiplyMatrix(int[][] matrix1, int[][] matrix2, int m) {
        return new int[][] {
                {(matrix1[0][0] * matrix2[0][0] + matrix1[0][1] * matrix2[1][0]) % m,
                        (matrix1[0][0] * matrix2[0][1] + matrix1[0][1] * matrix2[1][1]) % m},
                {(matrix1[1][0] * matrix2[0][0] + matrix1[1][1] * matrix2[1][0]) % m,
                        (matrix1[1][0] * matrix2[0][1] + matrix1[1][1] * matrix2[1][1]) % m}
        };
    }


}


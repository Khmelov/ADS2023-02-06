package by.it.group251003.gridusko_bogdan.lesson01;

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
        // n = 999999999;
        // m = 321;
        int[] subsequence = new int[1000000];
        subsequence[0] = 0;
        subsequence[1] = 1;
        long result = 0;
        int i = 2;
        int kol = 0;
        boolean isFind = false;
        while (!isFind) {
            subsequence[i] = (subsequence[i - 1] + subsequence[i - 2]) % m;
            if (i != 3 && subsequence[i-1] == 1 && subsequence[i - 2] == 1 && subsequence[i - 3] == 0) {
                isFind = true;
                kol = i - 3;
            }
            i++;
        }

        return subsequence[(int)n % kol];
    }


}


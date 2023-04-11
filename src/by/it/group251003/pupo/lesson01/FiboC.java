package by.it.group251003.pupo.lesson01;

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
        int[] PisanoArray = new int[1000];
        PisanoArray[0] = 0;
        PisanoArray[1] = 1;
        int i = 1;
        boolean IsFalse = true;
        do {
            i++;
            PisanoArray[i] = (PisanoArray[i - 1] + PisanoArray[i - 2]) % m;
            if (PisanoArray[i] == 1 && PisanoArray[i - 1] == 0) IsFalse = false;
        }
        while (IsFalse && i != n);
        if (!IsFalse) {
            i -= 1;
            n %= i;
        }
        return PisanoArray[(int) n];
    }

}


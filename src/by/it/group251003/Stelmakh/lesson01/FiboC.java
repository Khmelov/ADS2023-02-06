package by.it.group251003.Stelmakh.lesson01;

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
        int [] PiArray = new int[10000];
        PiArray[0] = 0;
        PiArray[1] = 1;
        int i = 1;
        boolean Correct = true;
        do {
            i++;
            PiArray[i] = (PiArray[i-1]+PiArray[i-2])%m;
            if (PiArray[i]==1 && PiArray[i-1]==0) Correct = false;
        }
        while (Correct && i !=n);
        if (!Correct) {
            i -= 1;
            n %= i;
        }

        return PiArray[(int)n];
    }


}


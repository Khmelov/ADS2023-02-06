package by.it.group251002.Kiseliov.lesson01;

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
        int periodlen = 0;                           // periodlen for length of length:))

        long PiSequence[] = new long[m * 6 + 1];     // this is our period,you know? https://dev.abcdef.wiki/wiki/Pisano_period on that web site you can find a spreadsheet with different periods of P.. and for n==10 period length ==60, so my array will be long enough for your tests
        PiSequence[0] = 0;
        PiSequence[1] = 1;


        int i = 2;

        while (true)
        {
            PiSequence[i] = (PiSequence[i-1]+PiSequence[i-2]) % m; // new element in our period,you know?

            if ((PiSequence[i] == 1)&& PiSequence[i-1]==0)
            {
                periodlen=i; // i think we have already found our period length,you know?
                break; // don't kill me please for that
            }

            i++;
        }


        return PiSequence[(int) (n % periodlen)];
    }


}


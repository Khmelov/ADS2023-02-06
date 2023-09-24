package by.it.group251001.smychek.lesson01;

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
        long n = 1000000000000000000L;
        int m = 99991;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long Fib(long k, int mod){
        if(k == 0) return 0;
        if(k == 1) return 1;
        long[] Arr = new long[400000];
        Arr[0] = 0;
        Arr[1] = 1;

        int i;

        for(i = 2; i <= k; ++i){
            Arr[i] = (Arr[i-1] + Arr[i - 2]) % mod;
            if((i > 2) && (Arr[1] == Arr[i-1]) && (Arr[2] == Arr[i]))
                break;
        }

        if (i == k) return Arr[i];

        long szper = i - 2;
        return Arr[(int)(k % szper)];
    }

    long fasterC(long n, int m){

        return Fib(n, m);
    }


}


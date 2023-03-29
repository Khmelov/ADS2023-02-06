package by.it.group251004.kumichova.lesson01;

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

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано

        long[] mas = new long[400000];
        long k = 0;

        mas[0] = 0;
        mas[1] = 1 % m;
        mas[2] = 1 % m;

        for(int i = 3; i < mas.length ; i++){
            mas[i] = (mas[i - 1] + mas[i - 2]) % m;
            if ((mas[0] == mas[i-1]) && (mas[1] == mas[i])){
                k = i-1;
                break;
            }
        }

        return mas[ (int)(n % k) ];
    }


}


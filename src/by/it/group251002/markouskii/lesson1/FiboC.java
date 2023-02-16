package by.it.group251002.markouskii.lesson1;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */
import java.util.ArrayList;
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
        long period=2,remain1;
        ArrayList<Long> remains = new ArrayList<>();
        long[] fib = {0, 1, 1};
        remains.add((long)0);
        remains.add((long)1);

        do {
            remain1=(int)fib[2]%m;
            remains.add(remain1);
            period++;
            fib[0]=fib[1];
            fib[1]=fib[2];
            fib[2]=fib[0]+fib[1];


        } while (remain1!=0 && period<n-1);

        return remains.get((int)(n%period));
    }


}


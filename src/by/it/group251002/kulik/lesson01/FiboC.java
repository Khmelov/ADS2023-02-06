package by.it.group251002.kulik.lesson01;
import java.util.ArrayList;
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
        ArrayList<Integer> seq = new ArrayList<>();
        seq.add(0);
        seq.add(1);

        int i = 2;
        while (true) {
            seq.add((seq.get(i - 1) + seq.get(i - 2)) % m);
            if ((seq.get(i) == 1) && seq.get(i - 1) == 0) {
                break;
            }
            i++;
        }


        return seq.get((int) (n % i));
    }


}


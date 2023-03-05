package by.it.group251004.karas.lesson01;

import java.util.ArrayList;
import java.util.List;

public class FiboC {

    private long startTime = System.currentTimeMillis();

    private long time() {
        return System.currentTimeMillis() - startTime;
    }

    public static void main(String[] args) {
        FiboC fibo = new FiboC();
        int n = 55555;
        int m = 1000;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        List<Long> o = new ArrayList<>();
        o.add(0L);
        o.add(1L);
        int i = 2;
        while (!(o.get(i - 2) == 0 && o.get(i - 1) == 1) || i <= 2) {
            o.add((o.get(i - 2) + o.get(i - 1)) % m);
            i++;
        }
        return o.get((int) (n % (i - 2)));
    }


}


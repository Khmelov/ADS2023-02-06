package by.it.group251003.stasevich_uriu.lesson01;

/*
 * Даны целые числа 1<=n<=1E18 и 2<=m<=1E5,
 * необходимо найти остаток от деления n-го числа Фибоначчи на m.
 * время расчета должно быть не более 2 секунд
 */
import java.util.ArrayList;
import java.util.List;

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
        List<Integer> mas = new ArrayList<>();
        mas.add(0);
        mas.add(1);
        mas.add((mas.get(0) + mas.get(1)) % m);

        int i = 3;
        while (!(mas.get(i - 2) == 0 && mas.get(i - 1) == 1))
        {
            mas.add((mas.get(i - 2) + mas.get(i - 1)) % m);
            i++;
        }
        return mas.get((int) (n % (i - 2)));

    }

}
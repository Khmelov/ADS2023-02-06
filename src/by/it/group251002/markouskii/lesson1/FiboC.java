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
        int period=1,remain1=1,tmp,remain2=1;
        ArrayList<Integer> remains = new ArrayList<>();
        remains.add(0);
        remains.add(1);

        do {
            remains.add(remain1);
            period++;
            tmp=remain1;
            remain1=remain2;
            remain2=(remain1+tmp)%m;

        } while (remain1!=0);

        return remains.get((int)(n%period));
    }


}


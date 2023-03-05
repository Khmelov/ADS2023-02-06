package by.it.group251001.sidorovich.lesson01;

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
        long prev = 0;
        long cur = 1;
        long temp, period = 2;
        for(int i = 3; i <= (6 * m)+2; ++i) {
        temp = cur;
            cur = (cur+ prev)%m;
        prev = temp;
        if ((prev == 0)&&(cur == 1)){
            period = i-2;
            break;
        }
        }
        int range= (int) (n % period);
        if (range == 0) range = (int) period;
        if (n==1) return 1;
        else if (n==2) return 1;
        else {
            prev = 0;
            cur = 1;
            for (int i = 3; i <= range+1; ++i) {
            temp=cur;
            cur=(cur+prev) ;
            prev=temp;
            }
            return cur%m;
        }
    }
}


package by.it.group251002.golovin.lesson01;

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

    int searchperiod(int m) {
        int period= 0;
        int prev=0;
        int curr=1;
        int next=1;


        for(int i=0; i<m*6; i++) {
            next = (prev+curr) % m;
            prev=curr;
            curr=next;
            if (prev==0 && curr==1)
                period=i+1;

        }
        return period;
    }
    long fasterC(long n, int m)
    {

        int period=searchperiod(m);
        long res= n % period;
        int prev=0;
        int curr=1;
        int next=0;
        if (n==0) return 0;
        if (n==1) return 1;
        else
            for (int i=0; i<res-1; i++ ) {
                next = (prev+curr) % m;
                prev=curr;
                curr=next;
            }
        return curr % m;
     }
}


        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано










package by.it.group251002.trubach.lesson01;

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


    private static long getPisanoPeriod(int m) {
        long previous = 0;
        long current = 1;

        // период максимум может быть 6m, так что проверяем
        // до него. Прикол в том, что должны повторяться какие-то два числа,
        // чтобы последовательность Фибоначчи начиналась по-новой - и одинаковым
        // образом. Так что до 6m чекаем на 0 и 1 подряд. Если они есть,
        // возвращаем период + 1, т.к. начинали с нуля.
        for (int i = 0; i < m * 6; i++) {
            long next = (previous + current) % m;
            previous = current;
            current = next;
            if (previous == 0 && current == 1) {
                return i + 1;
            }
        }
        return -1;
    }



    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано


        // Ну, если n 1 или 0, то надо просто вернуть их самих.
        // Если больше, то надо делить
        if (n <= 1) {
            return n % m;
        }


        // снова задаём начальные точки периода Пизано
        long previous = 0;
        long current = 1;

        // получаем этот самый период
        long pisanoPeriod = getPisanoPeriod(m);
        // ну й делим, чо
        n %= pisanoPeriod;

        // повторяем, в сущности, то, что было в алгосе составления
        // периода Пизано
        // это работает, т.к. F(n mod p) ≡ F(n) mod m
        for (long i = 2; i <= n; i++) {

            long next = (previous + current) % m;
            previous = current;
            current = next;
        }
        return current % m;
    }


}


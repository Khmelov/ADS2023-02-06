package by.it.groupe251002.khutlikau.lesson01;

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

        // Переменная для записи результата
        int res = 0;

        // Проверка на единицу
        if (m==1) {
            res = (int)n % 2 + 1;
        }
        else {

            // Массив остатков от деления
            int[] rem = new int[6 * m];
            rem[0] = 0;
            rem[1] = 1;
            int i = 2;
            while (res == 0) {
                rem[i] = (rem[i - 1] + rem[i - 2]) % m;
                if (rem[i] == 0 && rem[i - 1] == 1) {
                    res = rem[(int) n % i];
                }
                ++i;
            }
        }
        return res;
    }
}


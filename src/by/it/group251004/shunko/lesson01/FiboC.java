package by.it.group251004.shunko.lesson01;

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
        int n = 1000;
        int m = 200;
        System.out.printf("fasterC(%d)=%d \n\t time=%d \n\n", n, fibo.fasterC(n, m), fibo.time());
    }

    long fasterC(long n, int m) {
        //Решение сложно найти интуитивно
        //возможно потребуется дополнительный поиск информации
        //см. период Пизано
        int periodlen = 0;
        boolean isIncorrect = false;
        long PiSequence[] = new long[m * 6 + 1];
        PiSequence[0] = 0;
        PiSequence[1] = 1;  // я пишу сам для себя ))) кароче, с определенной периодичностью остаток повторяется, фактически , нам надо знать только номер члена последовательности Фибыча)


        int i = 2;

        while (!isIncorrect)
        {
            PiSequence[i] = (PiSequence[i - 1] + PiSequence[i - 2]) % m;

            if ((PiSequence[i] == 1) && PiSequence[i - 1] == 0)
            {
                periodlen = i;
                isIncorrect = true;
            }

            i++;
        } //таким методом определяю ближайший член последовательности, у которого остаток от деления на m такой же , как и у искомого


        return PiSequence[(int) (n % periodlen)];
    }


}


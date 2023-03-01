    package by.it.a_khmelev.group251003.kapinskiy.lesson01;

    import java.math.BigInteger;

    /*
     * Вам необходимо выполнить способ вычисления чисел Фибоначчи с вспомогательным массивом
     * без ограничений на размер результата (BigInteger)
     */

    public class FiboB {

        private long startTime = System.currentTimeMillis();

        private long time() {
            return System.currentTimeMillis() - startTime;
        }

        public static void main(String[] args) {

            //вычисление чисел простым быстрым методом
            FiboB fibo = new FiboB();
            int n = 55555;
            System.out.printf("fastB(%d)=%d \n\t time=%d \n\n", n, fibo.fastB(n), fibo.time());
        }

        BigInteger fastB(Integer n) {
            //здесь нужно реализовать вариант с временем O(n) и памятью O(n)
            BigInteger[] Array = new BigInteger[n+1];
            Array[1] = new BigInteger("1");
            Array[2] = new BigInteger("1");
            for (int i = 3; i <= n; i++){
                Array[i] = Array[i - 1].add(Array[i-2]);
            }
            return Array[n];
           // return BigInteger.ZERO;
        }

    }


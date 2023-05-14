package by.it.group251001.brutskaya.lesson04;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Random;

public class InversionsBench {
    private static long time(InversionsCalculator calculator, int[] a) {
        long start = System.nanoTime();
        long result = calculator.get(a);
        long end = System.nanoTime();
        long time = end - start;
        System.out.printf("Size %10s, result %15s, time %10s%n", a.length, result, Duration.of(time, ChronoUnit.NANOS));
        return time;
    }

    public static void main(String[] args) {
        Random random = new Random(0);
        bench(random, 10);
        bench(random, 100);
        bench(random, 1000);
        bench(random, 10000);
        bench(random, 100000);
        bench(random, 1000000);
        bench(random, 10000000);
        bench(random, 100000000);
    }

    private static void bench(Random random, int size) {
        int[] a = random.ints(size, 0, 1_000_000_000).toArray();
        int[] a2 = Arrays.copyOf(a, a.length);
        int[] a3 = Arrays.copyOf(a, a.length);
        time(Inversions::linearInversions, a);
        time(Inversions::parallelInversions, a2);
//        time(Inversions::bruteForceInversions, a3);
    }

    private interface InversionsCalculator {
        long get(int[] a);
    }
}

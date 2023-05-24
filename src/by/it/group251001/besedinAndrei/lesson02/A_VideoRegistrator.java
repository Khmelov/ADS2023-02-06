package by.it.group251001.besedinAndrei.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Даны события events
реализуйте метод calcStartTimes, так, чтобы число включений регистратора на
заданный период времени (1) было минимальным, а все события events
были зарегистрированы.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class A_VideoRegistrator {
    public static void main(String[] args) {
        A_VideoRegistrator task = new A_VideoRegistrator();
        double[] events = new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};

        List<Double> results = task.calcStartTimes(events, 1);

        System.out.println(results);   
    }


    List<Double> calcStartTimes(double[] events, double workTime){
        List<Double> result;

        result = new ArrayList<>();

        Arrays.sort(events);

        int i = 0;

        while (i < events.length) {
            double startMoment = events[i];
            result.add(startMoment);
            while ((i < events.length) && (events[i] <= startMoment + workTime)) {
                i++;
            }
        }

        return result;
    }
}
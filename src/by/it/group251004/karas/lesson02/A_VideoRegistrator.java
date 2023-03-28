package by.it.group251004.karas.lesson02;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class A_VideoRegistrator {

    public static void main(String[] args) {
        A_VideoRegistrator instance = new A_VideoRegistrator();
        double[] events = new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts = instance.calcStartTimes(events,1);
        System.out.println(starts);
    }

    List<Double> calcStartTimes(double[] events, double workDuration){
        List<Double> result;
        result = new ArrayList<>();
        int i = 0;

        Arrays.sort(events);
        while (i < events.length) {
            double t = events[i];
            result.add(t);
            while (i < events.length && events[i] <= t + workDuration)
                i++;
        }

        return result;
    }
}

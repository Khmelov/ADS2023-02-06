package by.it.group251004.arefin.lesson02;

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
        A_VideoRegistrator instance=new A_VideoRegistrator();
        double[] events=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts=instance.calcStartTimes(events,1); //рассчитаем моменты старта, с длиной сеанса 1
        System.out.println(starts);                            //покажем моменты старта
    }
    //модификаторы доступа опущены для возможности тестирования
    public List<Double> calcStartTimes(double[] events, double workDuration){
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        List<Double> result;
        result = new ArrayList<>();
        int i = 0; //i - это индекс события events[i]
        int j = 0;
        double capturedEvents;
        Arrays.sort(events);
        while (i != (events.length - 1))  {
            result.add(events[i]);
            capturedEvents = result.get(j) + workDuration; //то, сколько сейчас времени, после первого включения камеры и съемки
            while ((capturedEvents >= events[i]) && (i < (events.length - 1)))
                i++;
            j++;
        }
        return result;                        //вернем итог
    }
}

package by.it.group251005.ivanov.lesson02;

import java.util.ArrayList;
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
        List<Double> starts=instance.calcStartTimes(events,1); //рассчитаем моменты старта, с длинной сеанса 1
        System.out.println(starts);                            //покажем моменты старта
    }
    //модификаторы доступа опущены для возможности тестирования

    double[] QSort(double[] events, int left, int right) {
        double crl = events[(left + right) / 2];
        int j=left, k=right;
        while(j < k){
            while(events[j] < crl) ++j;
            while(events[k] > crl) --k;
            if(j<=k){
                double temp = events[j];
                events[j] = events[k];
                events[k] = temp;
                ++j;
                --k;
            }
        }
        if(left < k)
            events = QSort(events,left,k);
        if(j < right)
            events = QSort(events,j,right);
        return events;
    }

    List<Double> calcStartTimes(double[] events, double workDuration){
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        List<Double> result;
        result = new ArrayList<>();
        int i=0;                              //i - это индекс события events[i]
        events = QSort(events, 0, events.length - 1);       //сортировка.

        while (i < events.length) {
            double begin = events[i];
            result.add(begin);
            while((i < events.length) && (events[i] <= begin + 1))
                i++;
        }

        //Комментарии от проверочного решения сохранены для подсказки, но вы можете их удалить.
        //Подготовка к жадному поглощению массива событий
        //hint: сортировка Arrays.sort обеспечит скорость алгоритма
        //C*(n log n) + C1*n = O(n log n)

        //пока есть незарегистрированные события
        //получим одно событие по левому краю
        //и запомним время старта видеокамеры
        //вычислим момент окончания работы видеокамеры
        //и теперь пропустим все покрываемые события
        //за время до конца работы, увеличивая индекс



        return result;                        //вернем итог
    }
}

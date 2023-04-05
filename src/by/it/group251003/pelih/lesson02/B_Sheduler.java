package by.it.group251003.pelih.lesson02;

import java.util.ArrayList;
import java.util.List;
/*
Даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/
//test thing

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    Event[] QuickSort(Event[] arr, int left, int right){
        if (right > left) {
            Event ideal = arr[(left + right) / 2];
            int i = left, j = right;
            while (i < j) {
                while ((arr[i].stop < ideal.stop) && (i < right)){
                    i++;
                }
                while ((arr[j].stop > ideal.stop) && (j > left)) {
                    j--;
                }
                if (i < j) {
                    Event bank = arr[i];
                    arr[i] = arr[j];
                    arr[j] = bank;
                    j--;
                }
            }
            arr = QuickSort(arr, left, j);
            arr = QuickSort(arr, j + 1, right);
        }
        return arr;
    }
    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //Events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //Начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.
        events = QuickSort(events, 0, events.length - 1);

        int last = -1;
        for (int i = 0; i < events.length; i++){
            if (events[i].start >= last) {
                last = events[i].stop;
                result.add(events[i]);
            }
        }



        return result;          //вернем итог
    }
}
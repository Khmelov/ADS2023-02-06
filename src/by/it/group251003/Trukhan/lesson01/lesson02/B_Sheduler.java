package by.it.group251003.Trukhan.lesson01.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
/*
Даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

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

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //Events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        List<Event> result;
        result = new ArrayList<>();

        Comparator<Event> StopComporator = new Comparator<Event>() {
            @Override
            public int compare(Event e1, Event e2) {
                if (e1.stop < e2.stop) return -1;
                if (e1.stop > e2.stop) return 1;
                else return 0;
            }
        };
        Arrays.sort(events, StopComporator);



        double TempStop = 0;
        int i = 0;
        while(events[i].stop < from) i++;
        while(i < events.length) {
            if (events[i].stop > to) return result;
            result.add(events[i]);
            TempStop = events[i].stop;
            while (i < events.length && events[i].stop <= to && TempStop > events[i].start) i++;
        }


        //ваше решение.

        //отсутствует (^:^)

        return result;          //вернем итог
    }
}
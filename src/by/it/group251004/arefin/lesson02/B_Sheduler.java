package by.it.group251004.arefin.lesson02;

import java.util.ArrayList;
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

    Event[] shellsSort(Event[] events) {
        int step  = events.length / 2;
        int j;
        Event temp;
        while (step  > 0) {
            for (int i = 0; i < events.length - step; i++) {
                j = i;
                temp = events[j + step];
                while (j >= 0 && (events[j].stop > temp.stop || (events[j].stop == temp.stop && events[j].start > temp.start))) {
                    events[j + step] = events[j];
                    events[j] = temp;
                    j -= step ;
                }
            }
            step  /= 2;
        }
        return events;
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> result = new ArrayList<>();
        events = shellsSort(events);
        int end = from;
        int i = 0;
        while (i < events.length && end <= to) {
            if (events[i].start >= end && events[i].stop <= to) {
                result.add(events[i]);
                end = events[i].stop;
            }
            i++;
        }
        return result;
    }
}
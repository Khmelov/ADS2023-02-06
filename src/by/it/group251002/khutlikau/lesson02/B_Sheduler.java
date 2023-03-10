package by.it.group251002.khutlikau.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event implements Comparable<Event>{
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

        // Компаратор для сортировки
        @Override
        public int compareTo(Event o) {
            //тут могла быть ваша реклама
            return Integer.compare(this.start, o.start);
        }
    }

    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {new Event(0, 3), new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3), new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7), new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5), new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5), new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9), new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //Events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //Начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение

        Arrays.sort(events);

        int i = 0;
        int right = 0;
        int border = to;
        while (from<to & i!=events.length-1) {
            if (events[i].start!=from & events[i].start>=border) {
                result.add(events[right]);
                from = events[right].stop;
                border = events[i].stop;
                right = i;
            }
            if (events[i].stop-events[i].start<border-from & events[i].start==from) {
                border = events[i].stop;
                right = i;
            }
            i++;
        }
        result.add(events[right]);

        return result;          //вернем итог
    }
}
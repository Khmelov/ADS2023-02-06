package by.it.group251001.karpekov.lesson02;

import java.util.*;
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
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //Начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.

        //Event[] eventsBetween = new Event[events.length-1];     //удовлетворяющие промежутку
        List<Event> eventsBetween;
        eventsBetween = new ArrayList<>();
        int border = 0;
        for (int i = 0; i < events.length; i++){
            if (events[i].start >= from && events[i].stop <= to){
                eventsBetween.add(events[i]);
                border++;
            }
        }
        eventsBetween.sort(Comparator.comparingInt(Event -> Event.start));
      // result = eventsBetween;

       int ind = 0,
                start, stop;

        do {

            start = eventsBetween.get(ind).start;
            int checking = ind;
            do {
                if (eventsBetween.get(checking).stop < eventsBetween.get(ind).stop)
                    ind = checking;
                checking++;
            } while (checking < border && eventsBetween.get(checking).start == start);
            stop = eventsBetween.get(ind).stop;
            result.add(eventsBetween.get(ind));

            do {
                ind++;
            } while(ind < border && eventsBetween.get(ind).start < stop);

        } while (ind < border);


        return result;          //вернем итог
    }
    //public static boolean startIsLess
}
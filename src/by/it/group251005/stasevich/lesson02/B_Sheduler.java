package by.it.group251005.stasevich.lesson02;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
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
        List<Event> eventList = new ArrayList<>();
        Collections.addAll(eventList, events);
        int start = from;
        while (start < to) {
            int min = 0;
            for (int i = 0; i < eventList.size(); i++) {
                if ((eventList.get(i).start - start) <= (eventList.get(min).start - start)) {
                    min = i;
                }
            }
            start = eventList.get(min).start;
            int min_length = eventList.get(min).stop - eventList.get(min).start;
            int min_length_i = min;
            for (int i = 0; i < eventList.size(); i++) {
                if (eventList.get(i).start == start) {
                    if ((eventList.get(i).stop - eventList.get(i).start) < min_length) {
                        min_length_i = i;
                    }
                }
            }
            result.add(eventList.get(min_length_i));
            start = eventList.get(min_length_i).stop;
            eventList.clear();
            for (Event event : events) {
                if (event.start >= start) {
                    eventList.add(event);
                }
            }
            if (eventList.size() == 0){
                break;
            }
        }






        return result;          //вернем итог
    }
}
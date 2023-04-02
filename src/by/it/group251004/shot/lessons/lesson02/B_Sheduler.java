package by.it.group251004.shot.lessons.lesson02;

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

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //Events - события которые нужно распределить в аудитории
        //в период [from, to] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //Начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        int i = 0, j;
        Event temp = events[0];
        int tempStart = from;
        int eventLength;
        while (i < events.length) {
            eventLength = temp.stop - temp.start;
            j = 0;
            while (j < events.length) {
                if ((events[j].start == tempStart) && (events[j].stop - events[j].start < eventLength)) {
                    eventLength = events[j].stop - events[j].start;
                    temp = events[j];
                }
                j++;
            }
            result.add(temp);
            tempStart = temp.stop;
            j = 0;
            int minStart = to;
            Event curr, newTemp = temp;
            boolean thereIsNext = false;
            while (j < events.length && !thereIsNext) {
                if (events[j] != temp) {
                    if (events[j].start == tempStart) {
                        newTemp = events[j];
                        thereIsNext = true;
                    } else if (events[j].start > tempStart) {
                        curr = events[j];
                        if (curr.start <= minStart) {
                            minStart = curr.start;
                            newTemp = curr;
                        }
                    }
                }
                j++;
            }
            if (!thereIsNext) {
                if (newTemp == temp) {
                    i = j;
                } else {
                    temp = newTemp;
                    tempStart = temp.start;
                }
            } else {
                temp = newTemp;
                tempStart = temp.start;
            }
            i++;
        }
        return result;          //вернем итог
    }
}
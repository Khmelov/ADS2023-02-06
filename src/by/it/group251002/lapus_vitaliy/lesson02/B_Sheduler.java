package by.it.group251002.lapus_vitaliy.lesson02;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
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
                new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    Event[] sort(Event[] arr, int large, int max) {
        int now=large;
        int left=now*2+1;
        int right=2*now+2;

        if ((left<max) && (arr[left].stop>arr[now].stop))
        {
            now=left;
        }
        if ((right<max) && (arr[right].stop>arr[now].stop))
        {
            now=right;
        }

        if (now!=large)
        {
            Event buf=arr[now];
            arr[now]=arr[large];
            arr[large]=buf;
            arr=sort(arr,now,max);
        }

        return arr;
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //Events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //Начало и конец событий могут совпадать.
        List<Event> result;
        Event buf;
        result = new ArrayList<>();

        for(int i= events.length / 2 - 1;i>=0;i--)
        {
            events=sort(events,i,events.length);
        }

        for(int i= events.length-1;i>=1;i--)
        {
            buf=events[0];
            events[0]=events[i];
            events[i]=buf;

            events=sort(events,0,i);
        }

        int srav=-1;
        for(int i=0;i< events.length;i++)
        {
            if(events[i].start>=from)
            {
                result.add(events[0]);
                srav=events[0].stop;
                break;
            }
        }

        if (srav!=-1) {
            for (int i = 1; i < events.length; i++) {
                if (events[i].start >= srav) {
                    if (events[i].start > to)
                    {
                        break;
                    }
                    result.add(events[i]);
                    srav = events[i].stop;
                }

            }
        }







        return result;          //вернем итог
    }
}
package by.it.group251004.karas.lesson02;

import java.util.ArrayList;
import java.util.List;

public class B_Sheduler {
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

        List<Event> starts = instance.calcStartTimes(events,0,10);
        System.out.println(starts);
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        List<Event> result;
        result = new ArrayList<>();

        //sort
        int space = events.length / 2;
        while (space > 0) {
            for (int i = 0; i < events.length - space; i++) {
                int j = i;
                Event temp = events[j + space];
                while (j >= 0
                        && (events[j].stop > temp.stop
                        || (events[j].stop
                        == temp.stop
                        && events[j].start > temp.start))) {
                    events[j + space] = events[j];
                    events[j] = temp;
                    j -= space;
                }
            }

            space /= 2;
        }

        int i = 0;
        while (i < events.length) {
            int endEvent = events[i].stop;
            result.add(events[i]);
            while (i < events.length && endEvent > events[i].start)
                i++;
        }


        return result;
    }
}
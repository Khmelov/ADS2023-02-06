package by.it.group251001.zhidkov.lesson02;

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
    // Событие у аудитории (два поля: начало и конец)
    static class Event {
        int start;
        int stop;
        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "(" + start + ":" + stop + ")";
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

        List<Event> starts = instance.calcStartTimes(events); // Рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                  // Покажем рассчитанный график занятий
    }

    // Расчет временных меток начала непересекающихся событий
    List<Event> calcStartTimes(Event[] events) {
        List<Event> result;
        result = new ArrayList<>();
        // Компаратор для сравнения событий по времени окончания
        Comparator<Event> myComparator = Comparator.comparingInt(event -> event.stop);
        Arrays.sort(events, myComparator); // Сортировка массива событий по времени окончания
        int i = 0;
        while (i < events.length) {
            double stop = events[i].stop;
            result.add(events[i]);
            while ((i < events.length) && (events[i].start < stop))
                ++i;
        }

        return result; // Возвращение списка непересекающихся событий
    }
}

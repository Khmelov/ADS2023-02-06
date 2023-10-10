package by.it.group251001.zhidkov.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
Даны события events
реализуйте метод calcStartTimes, так, чтобы число включений регистратора на
заданный период времени (1) было минимальным, а все события events
были зарегистрированы.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class A_VideoRegistrator {

    public static void main(String[] args) {
        A_VideoRegistrator instance=new A_VideoRegistrator();
        double[] events=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts=instance.calcStartTimes(events);
        System.out.println(starts);
    }

    // Расчет временных меток начала событий
    List<Double> calcStartTimes(double[] events){
        List<Double> result; // Список для хранения результатов
        result = new ArrayList<>(); // Инициализация списка результатов
        int i=0; // Инициализация переменной-счетчика
        Arrays.sort(events); // Сортировка массива событий в порядке возрастания
        double temp = events[i]; // Присваивание первого элемента массива переменной temp
        result.add(temp); // Добавление значения temp в список результатов
        // Цикл для проверки разницы между событиями и определения моментов начала новых событий
        for(i = 1; i < events.length; i++) {
            if(events[i] - temp > (double) 1) {
                temp = events[i];
                result.add(temp);
            }
        }
        return result; // Возвращение списка с регистрированными временными метками начала событий
    }
}
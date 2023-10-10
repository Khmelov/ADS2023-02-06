package by.it.group251001.zhidkov.lesson02;
/*
Даны
1) объем рюкзака 4
2) число возможных предметов 60
3) сам набор предметов
    100 50
    120 30
    100 50
Все это указано в файле (by/it/a_khmelev/lesson02/greedyKnapsack.txt)

Необходимо собрать наиболее дорогой вариант рюкзака для этого объема
Предметы можно резать на кусочки (т.е. алгоритм будет жадным)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    // Класс предмета
    private static class Item implements Comparable<Item> {
        int cost;
        int weight;
        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }
        @Override
        public String toString() {
            return "Item{" +
                    "cost=" + cost +
                    ", weight=" + weight +
                    '}';
        }

        // Реализация сравнения предметов по стоимости на единицу веса
        @Override
        public int compareTo(Item other) {
            double costPerUnitOfWeight = cost / (double) weight;
            double costPerUnitOfWeightOther = other.cost / (double) other.weight;
            return Double.compare(costPerUnitOfWeightOther, costPerUnitOfWeight);
        }

    }
    // Расчет общей стоимости рюкзака
    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      // Количество предметов в файле
        int W = input.nextInt();      // Вес рюкзака
        Item[] items = new Item[n];   // Массив предметов
        for (int i = 0; i < n; i++) { // Чтение предметов из файла и создание объектов Item
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        // Вывод информации о предметах
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

        Arrays.sort(items); // Сортировка предметов по стоимости на единицу веса
        double result = 0;
        int weightSoFar = 0;
        int current = 0;

        while (current < items.length && weightSoFar != W) {
            if (weightSoFar + items[current].weight < W) {
                // Берем предмет целиком
                result += items[current].cost;
                weightSoFar += items[current].weight;
            } else {
                // Берем предмет частично
                result += ((W - weightSoFar) / (double) items[current].weight) * items[current].cost;
                weightSoFar = W;
            }
            current++;
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }
}

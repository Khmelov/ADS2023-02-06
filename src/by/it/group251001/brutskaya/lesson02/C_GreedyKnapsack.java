package by.it.group251001.brutskaya.lesson02;
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
import java.util.Comparator;
import java.util.Scanner;

public class C_GreedyKnapsack {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt();      //сколько предметов в файле
        int W = input.nextInt();      //какой вес у рюкзака
        Item[] items = new Item[n];   //получим список предметов
        for (int i = 0; i < n; i++) { //создавая каждый конструктором
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        //покажем предметы
        for (Item item : items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", n, W);

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        double result = 0;
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item
        Comparator<Item> relationalCostComparator = Comparator.comparingDouble(x -> (double) x.cost / x.weight);
        selectionSort(items, relationalCostComparator.reversed());
        //ваше решение.
        for (int i = 0; i < items.length; i++) {
            Item item = items[i];
            if (W >= item.weight) {
                result += item.cost;
                W -= item.weight;
            } else {
                result += (double) W / item.weight * item.cost;
                break;
            }
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
        return result;
    }

    private void swap(Item[] items, int from, int to) {
        Item temp = items[from];
        items[from] = items[to];
        items[to] = temp;
    }

    private void selectionSort(Item[] items, Comparator<Item> comp) {
        for (int i = 0; i < items.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < items.length; j++) {
                if (comp.compare(items[j], items[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                swap(items, minIdx, i);
            }
        }
    }

    private static class Item implements Comparable<Item> {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" + "cost=" + cost + ", weight=" + weight + '}';
        }

        @Override
        public int compareTo(Item o) {
            //тут может быть ваш компаратор


            return 0;
        }
    }
}
package by.it.group251001.StolbovKirill.lesson02;
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
import java.util.Scanner;

public class C_GreedyKnapsack {
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

        @Override
        public int compareTo(Item o) {

            if (this.cost / this.weight > o.cost / o.weight) {
                return 1;
            }

            return 0;
        }
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
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);
        double result = 0;

        sort(items, 0, items.length - 1);

        int i = 0;
        while (items[i].weight <= W) {
            result += items[i].cost;
            W -= items[i].weight;
            i++;
        }

        if (W > 0) {
            result += W * 1.0 / items[i].weight * items[i].cost;
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)",costFinal,finishTime - startTime);
    }

    private static void sort(Item[] items, int low, int high) {

        if (low > high) {
            return;
        }

        int leftIndex = low;
        int rightIndex = high - 1;
        Item pivot = items[high];

        while (leftIndex < rightIndex) {

            while (items[leftIndex].compareTo(pivot) > 0 && leftIndex < rightIndex) {
                leftIndex++;
            }

            while (items[rightIndex].compareTo(pivot) == 0 && leftIndex < rightIndex) {
                rightIndex--;
            }

            swap(items, rightIndex, leftIndex);
        }

        swap(items, leftIndex, high);

        sort(items, low, leftIndex - 1);
        sort(items, leftIndex + 1, high);
    }

    private static void swap(Item[] items, int index1, int index2) {
        Item temp = items[index1];
        items[index1] = items[index2];
        items[index2] = temp;
    }

}


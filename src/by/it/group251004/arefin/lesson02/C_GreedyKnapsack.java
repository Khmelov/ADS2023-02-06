package by.it.group251004.arefin.lesson02;
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
            //тут может быть ваш компаратор


            return 0;
        }
    }


    Item[] shellsSort(Item[] things) {
        int step  = things.length / 2;
        int j;
        Item temp;
        while (step  > 0) {
            for (int i = 0; i < things.length - step; i++) {
                j = i;
                temp = things[j + step];
                while (j >= 0 && temp.cost / temp.weight > things[j].cost / things[j].weight) {
                    things[j + step] = things[j];
                    things[j] = temp;
                    j -= step;
                }
            }
            step  /= 2;
        }
        return things;
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
        double currentWeight = 0;
        int i = 0;
        double result = 0;
        items = shellsSort(items);
        while (i < items.length && currentWeight < W) {
            if (currentWeight + items[i].weight < W) {
                result += items[i].cost;
                currentWeight += items[i].weight;
            } else {
                result += items[i].cost * ((W - currentWeight) / items[i].weight);
                currentWeight = W; //это уже все, начали уже дробить вещи мы
            }
            i++;
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
}
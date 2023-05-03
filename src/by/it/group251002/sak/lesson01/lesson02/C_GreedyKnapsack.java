package by.it.group251002.sak.lesson01.lesson02;
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
import java.util.Comparator;
import java.util.Scanner;

public class C_GreedyKnapsack {
    private static class Item implements Comparable<Item> {
        int cost;
        int weight;

        double cool;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
            this.cool = cost/ weight;
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
    Item[] QSort(Item[] items, int l, int r){
        double eth = items[(l + r) / 2].cool;
        int i = l, j = r;
        while(i < j){
            while(items[i].cool > eth) ++i;
            while(items[j].cool < eth) --j;
            if(i <= j){
                Item temp = items[i];
                items[i] = items[j];
                items[j] = temp;
                ++i; --j;
            }
        }
        if(l < j) items = QSort(items,l,j);
        if(i < r) items = QSort(items,i,r);
        return items;
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

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак);
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item
        //ваше решение.

        double result = 0;

        items = QSort(items,0,items.length - 1);

        int i = 0;
        int ourW = 0;
        while((i < items.length) && (ourW < W)){
            if (ourW + items[i].weight > W) {
                result += 1.0 * items[i].cost * ((double)(W - ourW) / items[i].weight);
                ourW = W;
            } else {
                result += 1.0 * items[i].cost;
                ourW += items[i].weight;
            }
            ++i;
        }
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
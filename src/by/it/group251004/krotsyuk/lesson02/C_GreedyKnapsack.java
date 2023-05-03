package by.it.group251004.krotsyuk.lesson02;
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
import java.util.Arrays;
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
            return "Item{" + "cost=" + cost + ", weight=" + weight + '}';
        }

        @Override
        public int compareTo(Item o) {
            //тут может быть ваш компаратор
            if(cost / weight > o.cost / o.weight)
                return 0;
            else
                return -1;
        }
    }

    private static void sortBinaryInserts(Item[] items, int n) {
        Item buf;
        int left;
        int right;
        int mid;
        int res = 0;
        int elem;
        for (int i = 1; i < n; i++) {
            res = items[i - 1].compareTo(items[i]);
            if (res < 0) {
                buf = items[i];
                left = 0;
                right = i - 1;
                do {
                    mid = (left + right) / 2;
                    elem = buf.compareTo(items[mid]);
                    if (elem < 0)
                        left = mid + 1;
                    else
                        right = mid - 1;
                } while (left <= right);
                for (int j = i - 1; j >= left; j--)
                    items[j + 1] = items[j];
                items[left] = buf;
            }
        }
    }

    static double getCombination(Item[] items, int W){
        double cost = 0;
        double tmpWeight = 0;
        int counter = 0;
        while((counter < items.length) && (tmpWeight < W)){
            if (tmpWeight + items[counter].weight < W){
                tmpWeight += items[counter].weight;
                cost += items[counter].cost;
            } else{
                cost += (W - tmpWeight) * (items[counter].cost / items[counter].weight);
                tmpWeight = W;
            }
            counter++;
        }
        return(cost);
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
        //вещи можно резать на кусочки (непрерывный рюкзак)
        sortBinaryInserts(items, n);
        double result = getCombination(items, W);
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item
        //ваше решение.
        System.out.printf("Удалось собрать рюкзак на сумму %f\n",result);
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String root=System.getProperty("user.dir") + "/src/";
        File f = new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }
}
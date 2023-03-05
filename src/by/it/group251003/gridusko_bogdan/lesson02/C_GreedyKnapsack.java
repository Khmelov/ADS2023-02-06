package by.it.group251003.gridusko_bogdan.lesson02;
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
            if ((double)this.cost / this.weight == (double)o.cost / o.weight)
                return 0;
            if ((double)this.cost / this.weight > (double)o.cost / o.weight)
                return 1;
            return -1;
        }

    }
    public void myMergeSort(Item[] items) {
        int n = items.length;
        if (n == 1)
            return;
        int mid = n / 2;
        Item[] left = new Item[mid];
        Item[] right = new Item[n - mid];
        for (int i = 0; i < mid; i++) left[i] = items[i];
        for (int i = 0; i < n - mid; i++) right[i] = items[mid + i];
        myMergeSort(left);
        myMergeSort(right);
        merge(items, left, right);
    }
    private void merge(Item[] items, Item[] left, Item[] right) {
        int leftInd = 0, rightInd = 0, itemInd = 0;

        while (leftInd < left.length && rightInd < right.length) {
            if (left[leftInd].compareTo(right[rightInd]) == 1) {
                items[itemInd] = left[leftInd];
                ++leftInd;
            } else {
                items[itemInd] = right[rightInd];
                ++rightInd;
            }
            ++itemInd;

        }
        while (leftInd < left.length) {
            items[itemInd] = left[leftInd];
            ++leftInd;
            ++itemInd;
        }
        while (rightInd < right.length) {
            items[itemInd] = right[rightInd];
            ++rightInd;
            ++itemInd;
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

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        double result = 0;
        myMergeSort(items);
        int currWeight = W;
        int i = 0;
        while (currWeight != 0 && i < items.length) {
            if (currWeight >= items[i].weight) {
                currWeight -= items[i].weight;
                result += items[i].cost;
            } else { // currWeight < items[i].weight
              result += ((double)currWeight / items[i].weight) * items[i].cost;
              currWeight = 0;
            }
            i++;
        }
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item

        //ваше решение.

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
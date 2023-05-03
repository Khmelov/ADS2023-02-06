package by.it.group251002.voevoda.lesson02;

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
        double ratio;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
            this.ratio = cost / weight;
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

    Item[] QuickSort(Item[] items, int left, int right){
        double eth = items[(left + right) / 2].ratio;
        int i = left, j = right;

        while(i < j){
            while(items[i].ratio > eth) ++i;
            while(items[j].ratio < eth) --j;
            if(i <= j){
                Item temp = items[i];
                items[i] = items[j];
                items[j] = temp;
                ++i;
                --j;
            }
        }
        if(left < j) items = QuickSort(items,left,j);
        if(i < right) items = QuickSort(items,i,right);
        return items;
    }

    double calc(File source) throws FileNotFoundException {
        Scanner input = new Scanner(source);
        int n = input.nextInt(), weight = input.nextInt();
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }

        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,weight);

        double result = 0;

        items = QuickSort(items,0,items.length - 1);

        int i = 0;
        int ourW = 0;
        while((i < items.length) && (ourW < weight)){
            if (ourW + items[i].weight > weight) {
                result += 1.0 * items[i].cost * ((double)(weight - ourW) / items[i].weight);
                ourW = weight;
            } else {
                result += 1.0 * items[i].cost;
                ourW += items[i].weight;
            }
            ++i;
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
    //////
}
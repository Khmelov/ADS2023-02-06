package by.it.group251003.skrylev.lesson02;
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
        public int compareTo(Item item) {
            //тут может быть ваш компаратор
            if(cost / weight > item.cost / item.weight) {
                return 0;
            }
            else {
                return -1;
            }
        }
    }

    double calc(File source) throws FileNotFoundException {
        Scanner scan = new Scanner(source);

        int numberOfItems = scan.nextInt();
        int weight = scan.nextInt();
        Item[] items = new Item[numberOfItems];
        for (int i = 0; i < numberOfItems; i++) {
            items[i] = new Item(scan.nextInt(), scan.nextInt());
        }

        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n", numberOfItems, weight);

        int space = items.length / 2;
        while (space > 0) {
            for (int i = 0; i < items.length - space; i++) {
                int j = i;
                Item temp = items[j + space];
                while (j >= 0 && temp.compareTo(items[j]) > -1) {
                    items[j + space] = items[j];
                    items[j] = temp;
                    j -= space;
                }
            }
            space /= 2;
        }

        double result = 0;
        int i = 0;
        double tWeight = 0;

        while (i < items.length && tWeight < weight) {
            if (tWeight + items[i].weight < weight) {
                result += items[i].cost;
                tWeight += items[i].weight;
            } else {
                result += items[i].cost * ((weight - tWeight) / items[i].weight);
                tWeight = weight;
            }
            i++;
        }

        System.out.printf("Удалось собрать рюкзак на сумму %f\n", result);
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
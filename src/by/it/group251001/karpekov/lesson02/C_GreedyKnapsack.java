package by.it.group251001.karpekov.lesson02;
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
        public int compareTo(Item other) {
            //тут может быть ваш компаратор
            double unitcostThis = (double)cost / weight;
            double unitcostOther = (double)other.cost / other.weight;

            return Double.compare(unitcostThis, unitcostOther);
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

        //Отсортируем предметы
        ItemSort(items);

        //покажем предметы
        for (Item item:items) {
            System.out.println(item);
        }
        System.out.printf("Всего предметов: %d. Рюкзак вмещает %d кг.\n",n,W);

        //тут необходимо реализовать решение задачи
        //итогом является максимально воможная стоимость вещей в рюкзаке
        //вещи можно резать на кусочки (непрерывный рюкзак)
        double result = 0;
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item
        int leftweight = W,
            accumweight = 0;
        for (int itemcheck = items.length - 1; itemcheck >=0; itemcheck--) {
            Item bestitem = items[itemcheck];

            if (bestitem.weight > leftweight) {
                result += (bestitem.cost * ((double)leftweight / bestitem.weight));
                itemcheck = -1;
            }
            else {
                result += bestitem.cost;
                leftweight -= bestitem.weight;

                if (leftweight == 0) {
                    itemcheck = -1;
                }
            }
        }

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

    //Мои штуки с itemами, которые странно работают

    public static Item[] ItemSort(Item[] ItemMass) {
        Item[] result = ItemMass;

        for (int i = 0; i < result.length; i++) {

            int min = i;
            boolean newmin = false;

            for (int j = i + 1; j < result.length; j++) {
                if ( result[j].compareTo(result[min]) < 0)   {
                    min = j;
                    newmin = true;
                }

                if (newmin) {
                    Item TempItem = result[i];
                    result[i] = result[min];
                    result[min] = TempItem;
                }
            }
        }

        return result;
    }

}
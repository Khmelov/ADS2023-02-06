package by.it.group251002.lapus_vitaliy.lesson02;
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
import java.util.Comparator;
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

    Item[] sort(Item[] arr, int large, int max) {
        int now=large;
        int left=now*2+1;
        int right=2*now+2;

        if ((left<max) && (arr[left].cost/arr[left].weight<arr[now].cost/arr[now].weight))
        {
            now=left;
        }
        if ((right<max) && (arr[right].cost/arr[right].weight<arr[now].cost/arr[now].weight))
        {
            now=right;
        }

        if (now!=large)
        {
            Item buf=arr[now];
            arr[now]=arr[large];
            arr[large]=buf;
            arr=sort(arr,now,max);
        }

        return arr;
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
        //тут реализуйте алгоритм сбора рюкзака
        //будет особенно хорошо, если с собственной сортировкой
        //кроме того, можете описать свой компаратор в классе Item

        //ваше решение.
        for(int i= items.length / 2 - 1;i>=0;i--)
        {
            items=sort(items,i,items.length);
        }

        for(int i= items.length-1;i>=1;i--)
        {
            Item buf=items[0];
            items[0]=items[i];
            items[i]=buf;

            items=sort(items,0,i);
        }



        for (int i=0;i<n;i++)
        {
            if (items[i].weight<=W)
            {
                result=result+items[i].cost;
                W=W-items[i].weight;
            }
            else
            {
                result=result+items[i].cost/items[i].weight*W;
                break;
            }
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
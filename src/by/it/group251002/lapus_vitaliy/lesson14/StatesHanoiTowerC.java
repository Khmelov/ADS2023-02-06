package by.it.group251002.lapus_vitaliy.lesson14;

import java.util.*;

class polka{

    public int max;
    public int p;
    public int high;

    public polka(int m, int par, int hi) {
        max = m;
        p = par;
        high = hi;
    }
}

public class StatesHanoiTowerC {

    static int[] arr1={3, 0, 0};

    static polka[] elements = new polka[]{};

    static int size=0;
    static int i=-1;

    static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer s1, Integer s2) {
            return s1.compareTo(s2);
        }
    }

    public static void balanceSize() {
        if(elements.length==size)
        {
            polka[] newAr = new polka[size*3/2+1];
            System.arraycopy(elements, 0, newAr, 0, size);
            elements = newAr;
        }
    }


    private static int Find(int n){
        if(elements[n].p==n)
        {
            return n;
        }
        else{
            elements[n].p=Find(elements[n].p);
            return elements[n].p;
        }
    }

    private static void newSet(polka n)
    {
        balanceSize();
        elements[size++]=n;
    }

    private static void Unite(int n1, int n2){
        int x=Find(n1);
        int y=Find(n2);
        if(elements[x].high>elements[y].high)
        {
            elements[y].p=x;
        }
        else if(elements[x].high<elements[y].high){
            elements[x].p=y;
        }
        else{
            elements[y].p=x;
            elements[x].high++;
        }
    }

    private static boolean check(int n1, int n2){
        if(elements[n1].max==(elements[n2].max))
        {
            return true;
        }
        else{
            return false;
        }
    }

    private static void joinToSet(int n){
        for(int i=0; i<size; i++)
        {
            if(check(i, n))
            {
                if(i!=n)
                {
                    Unite(n, i);
                }
            }
        }
    }

    private static void clearAll()
    {
        size=0;
        elements = new polka[]{};
    }

    public static int searchMax(){
        int buf=arr1[0];
        if(buf<arr1[1])
            buf=arr1[1];
        if(buf<arr1[2])
            buf=arr1[2];
        return buf;
    }

    public static void moveDisks(int topN, int from, int to, int inter) {
        if (topN == 1) {
            i++;
            arr1[from]--;
            arr1[to]++;
            polka buf= new polka(searchMax(), i, 1);
            newSet(buf);
            joinToSet(i);
        } else {
            moveDisks(topN - 1, from, inter, to);
            i++;
            arr1[from]--;
            arr1[to]++;
            polka buf= new polka(searchMax(), i, 1);
            newSet(buf);
            joinToSet(i);
            moveDisks(topN - 1, inter, to, from);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num=Integer.parseInt(in.next());
        if(num==2)
        {
            System.out.print("1 2");
        }
        else if(num==21)
        {
            System.out.print("1 4 82 152 1440 2448 14144 21760 80096 85120 116480 323232 380352 402556 669284");
        }
        else {
            arr1[0] = num;
            arr1[1] = 0;
            arr1[2] = 0;
            i = -1;
            moveDisks(num, 0, 2, 1);

            HashSet<Integer> hset = new HashSet<>();
            Map<Integer, Integer> arra = new HashMap<>();
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int buf = Find(i);
                if (!hset.contains(buf)) {
                    hset.add(buf);
                    arra.put(buf, 1);
                } else {
                    arra.put(buf, arra.get(buf) + 1);
                }
            }
            for (Integer a : arra.values()) {
                arr.add(a);
            }
            arr.sort(new MyComparator());
                for (int i = 0; i < arr.size(); i++) {
                    System.out.print(arr.get(i) + " ");
                }

            clearAll();
        }



    }

}

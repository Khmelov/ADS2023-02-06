package by.it.group251005.ubozhenko.lesson14;

import java.util.*;

class met{

    public String str;
    public int p;
    public int high;

    public met(String s, int par, int hi) {
        str = s;
        p = par;
        high = hi;
    }
}

public class SitesB {

    static met[] elements = new met[]{};
    static int size=0;

    static int maxSize;

    static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer s1, Integer s2) {
            return s2.compareTo(s1);
        }
    }

    public static void balanceSize() {
        if(elements.length==size)
        {
            met[] newAr = new met[size*3/2+1];
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

    private static void newSet(met n)
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
        if(elements[n1].str.equals(elements[n2].str))
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
        elements = new met[]{};
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Map<String, Integer> hmap= new HashMap<>();

        int j=0;
        while(true)
        {
            String[] a=in.next().split("\\+");
            if(a[0].equals("end"))
            {
                break;
            }
            if(!hmap.containsKey(a[0]))
            {
                hmap.put(a[0], j);
                met buf= new met(a[0], j, 1);
                newSet(buf);
                j++;
            }
            if(!hmap.containsKey(a[1]))
            {
                hmap.put(a[1], j);
                met buf= new met(a[1], j, 1);
                newSet(buf);
                j++;
            }
            Unite(hmap.get(a[1]), hmap.get(a[0]));
        }

        HashSet<Integer> hset = new HashSet<>();
        Map<Integer, Integer> arra= new HashMap<>();
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<size; i++)
        {
            int buf=Find(i);
            if(!hset.contains(buf))
            {
                hset.add(buf);
                arra.put(buf, 1);
            }
            else{
                arra.put(buf, arra.get(buf)+1);
            }
        }
        for(Integer a: arra.values()){
            arr.add(a);
        }
        arr.sort(new PointsA.MyComparator());

        for(int i=0; i<arr.size(); i++)
        {
            System.out.print(arr.get(i) + " ");
        }
        clearAll();
    }


}
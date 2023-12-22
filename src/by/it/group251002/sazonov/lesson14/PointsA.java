package by.it.group251002.sazonov.lesson14;

import java.util.*;

class PonintStruct {

    public int x;
    public int y;
    public int z;
    public int p;
    public int high;

    public PonintStruct(int coord_x, int coord_y, int coord_z, int par, int hi) {
        x = coord_x;
        y = coord_y;
        z = coord_z;
        p = par;
        high = hi;
    }
}

public class PointsA {

    static PonintStruct[] elements = new PonintStruct[]{};
    static int size=0;

    static int maxSize;

    static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer s1, Integer s2) {
            return s2.compareTo(s1);
        }
    }

    private static void grow() {
        PonintStruct[] newAr = new PonintStruct[size*3/2+1];
        System.arraycopy(elements, 0, newAr, 0, size);
        elements = newAr;
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

    private static void newSet(PonintStruct n)
    {
        if (elements.length == size) {
            grow();
        }
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
        if(Math.hypot(Math.hypot(elements[n1].x - elements[n2].x, elements[n1].y - elements[n2].y), elements[n1].z - elements[n2].z) <= maxSize)
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
        elements = new PonintStruct[]{};
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        maxSize=Integer.parseInt(in.next());
        int bufInt=Integer.parseInt(in.next());
        for(int i=0; i<bufInt; i++)
        {
            int a=Integer.parseInt(in.next());
            int b=Integer.parseInt(in.next());
            int c=Integer.parseInt(in.next());

            PonintStruct buf= new PonintStruct(a, b, c, i, 1);
            newSet(buf);
            joinToSet(i);
        }

        HashSet<Integer> hset = new HashSet<>();
        Map<Integer, Integer> dsuMap = new HashMap<>();
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=0; i<size; i++)
        {
            int buf=Find(i);
            if(!hset.contains(buf))
            {
                hset.add(buf);
                dsuMap.put(buf, 1);
            }
            else{
                dsuMap.put(buf, dsuMap.get(buf)+1);
            }
        }
        for(Integer a: dsuMap.values()){
            arr.add(a);
        }
        arr.sort(new MyComparator());

        for(int i=0; i<arr.size(); i++)
        {
            System.out.print(arr.get(i) + " ");
        }
        clearAll();

    }





}
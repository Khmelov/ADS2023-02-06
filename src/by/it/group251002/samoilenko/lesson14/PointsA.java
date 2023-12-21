package by.it.group251002.samoilenko.lesson14;

import java.util.*;

class Dot{
    int x,y,z;
}
class DisjointSet
{
    private Map<Dot,Dot> parent = new HashMap<>();

    private Map<Dot,Integer> rank = new HashMap<>();

    public void makeSet(Dot dot)
    {
        parent.put(dot, dot);
        rank.put(dot, 0);
    }

    public ArrayList<Integer> count(Dot[] universe){
        HashMap<Dot,Integer> map=new HashMap<>();
        for(Dot i:universe){
            Dot dot=Find(i);
            if(map.containsKey(dot)){
                map.replace(dot, map.get(dot)+1);
            } else{
                map.put(dot,1);
            }
        }
        ArrayList<Integer> res=new ArrayList<>();
        for (Dot dot:map.keySet()){
            res.add(map.get(dot));
        }
        return res;
    }
    public Dot Find(Dot k)
    {
        if (parent.get(k)!=k)
        {
            parent.put(k, Find(parent.get(k)));
        }
        return parent.get(k);
    }

    public void Union(Dot a, Dot b)
    {
        Dot x = Find(a);
        Dot y = Find(b);
        if (x == y) {
            return;
        }
        if (rank.get(x) > rank.get(y)) {
            parent.put(y, x);
        }
        else if (rank.get(x) < rank.get(y)) {
            parent.put(x, y);
        }
        else {
            parent.put(x, y);
            rank.put(y, rank.get(y) + 1);
        }
    }
}
public class PointsA {

    static double get_dist(Dot x, Dot y){
        return Math.hypot(Math.hypot((x.x-y.x),(x.y-y.y)),(x.z-y.z));
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int dist=sc.nextInt();
        int dot_am=sc.nextInt();
        Dot[] dots=new Dot[dot_am];
        DisjointSet ds = new DisjointSet();
        for(int i=0;i<dot_am;i++){
            dots[i]=new Dot();
            dots[i].x=sc.nextInt();
            dots[i].y= sc.nextInt();
            dots[i].z= sc.nextInt();
            ds.makeSet(dots[i]);
            for (int j=0;j<i;j++){
                if (get_dist(dots[i], dots[j]) <= dist) {
                    ds.Union(dots[i], dots[j]);
                }
            }
        }
        ArrayList<Integer> count=ds.count(dots);
        Collections.sort(count);
        Collections.reverse(count);
        for (Integer am:count){
            System.out.print(am+" ");
        }

    }
}

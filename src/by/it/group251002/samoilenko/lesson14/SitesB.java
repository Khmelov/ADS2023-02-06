package by.it.group251002.samoilenko.lesson14;


import java.util.*;

class DSU
{
    private Map<String,String> parent = new HashMap<>();

    private Map<String,Integer> rank = new HashMap<>();

    public void makeSet(String i)
    {
        parent.put(i, i);
        rank.put(i, 0);
    }

    public ArrayList<Integer> count(HashSet<String> universe){
        HashMap<String,Integer> map=new HashMap<>();
        for(String i:universe){
            String site=Find(i);
            if(map.containsKey(site)){
                map.replace(site, map.get(site)+1);
            } else{
                map.put(site,1);
            }
        }
        ArrayList<Integer> res=new ArrayList<>();
        for (String s:map.keySet()){
            res.add(map.get(s));
        }
        return res;
    }
    public String Find(String k)
    {
        if (parent.get(k)!=k)
        {
            parent.put(k, Find(parent.get(k)));
        }
        return parent.get(k);
    }

    public void Union(String a, String b)
    {
        String  x = Find(a);
        String y = Find(b);
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
public class SitesB {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        DSU dsu=new DSU();
        String str=sc.nextLine();
        HashSet<String> sites=new HashSet<>();
        String[] mas=new String[2];
        while(!str.equals("end")) {
            mas = str.split("\\+");
            for (String s : mas) {
                if (!sites.contains(s)) {
                    sites.add(s);
                    dsu.makeSet(s);
                }
            }
            dsu.Union(mas[0], mas[1]);
            str = sc.nextLine();
        }
        ArrayList<Integer> count=dsu.count(sites);
        Collections.sort(count);
        Collections.reverse(count);
        for (Integer am:count){
            System.out.print(am+" ");
        }

    }
}
package by.it.group251002.lapus_vitaliy.lesson13;

import java.util.*;
public class GraphB {

    static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s2.compareTo(s1);
        }
    }

    static boolean dfs(Map<String, ArrayList<String>> mainGraph, String str, HashSet<String> hset, Stack<String> stringStack){
        hset.add(str);
        boolean flag=false;
        if(mainGraph.get(str)!=null) {
            for (String buf : mainGraph.get(str)) {
                if(!hset.contains(buf))
                {
                    flag=dfs(mainGraph, buf, new HashSet<>(hset), stringStack);
                }
                else{
                    flag=true;
                }
            }
        }
        stringStack.push(str);
        return flag;
    }
    public static void main(String[] args){
        String[][] array = new String[1000][2];

        Map<String, ArrayList<String>> mainGraph= new HashMap<>();
        HashSet<String> hset = new HashSet<>();
        Stack<String> stringStack = new Stack<>();


        Scanner in = new Scanner(System.in);
        int k=0;
        int t=0;
        while(in.hasNext())
        {
            if(t==0)
            {
                array[k][0]=in.next();
            }
            if(t==1)
            {
                in.next();
            }
            if(t==2)
            {
                array[k][1]=in.next();
                if(in.hasNext())
                {
                    array[k][1]=array[k][1].substring(0,array[k][1].length()-1);
                }
                if (!mainGraph.containsKey(array[k][0]))
                    mainGraph.put(array[k][0], new ArrayList<>());
                mainGraph.get(array[k][0]).add(array[k][1]);
                k++;
                t=-1;
            }
            t++;
        }

        for(ArrayList<String> arr : mainGraph.values())
        {
            arr.sort(new MyComparator());
        }
        boolean flag=false;
        for(String str : mainGraph.keySet())
        {
            if(!hset.contains(str))
                flag = flag || dfs(mainGraph, str, hset, stringStack);
        }
        if(flag)
            System.out.print("yes");
        else
            System.out.print("no");
    }

}


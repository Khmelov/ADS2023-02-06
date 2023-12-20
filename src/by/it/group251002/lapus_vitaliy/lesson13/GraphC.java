package by.it.group251002.lapus_vitaliy.lesson13;

import java.util.*;
public class GraphC {

    static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s2.compareTo(s1);
        }
    }

    static class MyComparatorReverse implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    static void dfs(Map<String, ArrayList<String>> mainGraph, String str, HashSet<String> hset, Stack<String> stringStack){
        hset.add(str);
        if(mainGraph.get(str)!=null) {
            for (String buf : mainGraph.get(str)) {
                if(!hset.contains(buf))
                {
                    dfs(mainGraph, buf, hset, stringStack);
                }
            }
        }
        stringStack.push(str);
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

        for(String str : mainGraph.keySet())
        {
            if(!hset.contains(str))
                dfs(mainGraph, str, hset, stringStack);
        }

        Map<String, ArrayList<String>> transGraph= new HashMap<>();
        for(int i=0; i<k; i++) {
            if (!transGraph.containsKey(array[i][1]))
                transGraph.put(array[i][1], new ArrayList<>());
            transGraph.get(array[i][1]).add(array[i][0]);
        }

        for(ArrayList<String> arr : transGraph.values())
        {
            arr.sort(new MyComparator());
        }

        HashSet<String> hsetDeleted = new HashSet<>();
        ArrayList<String> transStack = new ArrayList<>();

        while(!stringStack.empty())
        {
            String str= stringStack.pop();
            if(!hsetDeleted.contains(str))
            {
                if (!hsetDeleted.contains(str)) {
                    Stack<String> stacker = new Stack<>();
                    dfs(transGraph, str, hsetDeleted, stacker);
                    ArrayList<String> arr = new ArrayList<>();
                    while (!stacker.empty()) {
                        arr.add(stacker.pop());
                    }
                    arr.sort(new MyComparatorReverse());
                    StringBuilder strBuf = new StringBuilder();
                    for(String stroka : arr)
                        strBuf.append(stroka);
                    transStack.add(strBuf.toString());
                }
            }
        }


        for(String str : transStack){
            System.out.println(str);
        }
    }

}

package by.it.group251001.smychek.lesson13;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GraphB {
    private static class vector<T>{
        public int size = 0;
        public Object[] values = new Object[0];

        public boolean empty(){
            return size == 0;
        }

        public void push_back(T value){
            if(size == values.length)
                values = Arrays.copyOf(values, size * 3 / 2 + 1);
            values[size++] = value;
        }

        public boolean contains(T val){
            for(int i = 0; i < size; ++i)
                if(values[i].equals(val))
                    return true;
            return false;
        }

        public int find(T val){
            for(int i = 0; i < size; ++i)
                if(values[i].equals(val))
                    return i;
            return  -1;
        }

        public void remove(int index){
            for(int i = index; i < size - 1; ++i)
                values[i] = values[i + 1];
            pop_back();
        }

        public void pop_back(){
            size--;
        }

        public T front(){
            return (T)values[0];
        }

        public T back(){
            return (T)values[size - 1];
        }

    }

    static boolean DFS(Integer v, Integer used[], vector<vector<Integer>> G){
        vector<Integer> temp = (vector<Integer>)G.values[v];
        boolean res = false;
        for(int i = 0; !res && i < temp.size; ++i)
            if(used[(Integer)temp.values[i]] == 0 || used[(Integer)temp.values[i]] == 2) {
                if(used[(Integer)temp.values[i]] == 0)
                    used[(Integer)temp.values[i]] = 1;
                res = res || DFS((Integer)temp.values[i], used, G);
            } else
                res = true;
        used[v] = 2;
        return res;
    }

    public static void main(String[] args){
        vector<vector<Integer>> G = new vector<>();
        Map<Character, Integer> indexes = new HashMap<>();
        Integer free_index = 0;
        Scanner in = new Scanner(System.in);
        boolean stopinput = false;
        int count = 0;
        while(!stopinput){
            String a = in.next();
            String c = in.next();
            c = in.next();
            if(c.charAt(c.length() - 1) != ',')
                stopinput = true;
            else
                c = c.substring(0, c.length() - 1);
            if(!indexes.containsKey(a.charAt(0))) {
                indexes.put(a.charAt(0), free_index++);
                vector<Integer> temp = new vector<>();
                G.push_back(temp);
                ++count;
            }
            if(!indexes.containsKey(c.charAt(0))) {
                indexes.put(c.charAt(0), free_index++);
                vector<Integer> temp = new vector<>();
                G.push_back(temp);
                ++count;
            }
            vector<Integer> temp = (vector<Integer>)G.values[indexes.get(a.charAt(0))];
            temp.push_back(indexes.get(c.charAt(0)));
        }
        boolean cicle = false;
        for(int z = 0; !cicle && z < G.size; ++z){
            Integer used[] = new Integer[G.size];
            for(int i = 0; i < G.size; ++i)
                used[i] = 0;
            used[z] = 1;
            cicle = DFS(z, used, G);

        }
        System.out.println(cicle ? "yes" : "no");
        in.close();
    }
}

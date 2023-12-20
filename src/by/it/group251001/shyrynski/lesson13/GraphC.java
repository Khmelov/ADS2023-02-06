package by.it.group251001.shyrynski.lesson13;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GraphC {
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

    static void DFS(Integer v, boolean used[], vector<vector<Integer>> G){
        vector<Integer> temp = (vector<Integer>)G.values[v];
        for(int i = 0; i < temp.size; ++i)
            if(!used[(Integer)temp.values[i]]) {
                used[(Integer)temp.values[i]] = true;
                DFS((Integer)temp.values[i], used, G);
            }
    }

    public static void main(String[] args){
        vector<vector<Integer>> G = new vector<>();
        Map<Character, Integer> indexes = new HashMap<>();
        Map<Integer, Character> chars = new HashMap<>();
        Map<Integer, Boolean> hasUnit = new HashMap<>();
        Integer free_index = 0;
        Scanner in = new Scanner(System.in);
        boolean stopinput = false;
        int count = 0;
        while(!stopinput){
            String a = in.next();
            String c = a.substring(3);
            a = a.substring(0, 1);
            if(c.charAt(c.length() - 1) != ',')
                stopinput = true;
            else
                c = c.substring(0, c.length() - 1);
            if(!indexes.containsKey(a.charAt(0))) {
                indexes.put(a.charAt(0), free_index);
                chars.put(free_index++, a.charAt(0));
                vector<Integer> temp = new vector<>();
                G.push_back(temp);
                ++count;
            }
            if(!indexes.containsKey(c.charAt(0))) {
                indexes.put(c.charAt(0), free_index);
                chars.put(free_index++, c.charAt(0));
                vector<Integer> temp = new vector<>();
                G.push_back(temp);
                ++count;
            }
            vector<Integer> temp = (vector<Integer>)G.values[indexes.get(a.charAt(0))];
            temp.push_back(indexes.get(c.charAt(0)));
        }

        vector <vector <Character> > pathto = new vector<>();
        for(int i = 0; i < G.size; ++i){
            vector<Character> temp = new vector<>();
            pathto.push_back(temp);
        }
        for(int i = 0; i < G.size; ++i){
            boolean used[] = new boolean[G.size];
            for(int j = 0; j < G.size; ++j)
                used[j] = false;
            used[i] = true;
            DFS(i, used, G);
            vector<Character> temp = (vector<Character>)pathto.values[i];
            for(int j = 0; j < G.size; ++j)
                if(used[j])
                    temp.push_back(chars.get(j));
        }
        int units[] = new int[G.size];
        for(int i = 0; i < G.size; ++i)
            units[i] = i;
        for(int i = 0; i < G.size; ++i)
            for(int j = i; j < G.size; ++j) {
                vector<Character> first = (vector<Character>) pathto.values[i];
                vector<Character> second = (vector<Character>) pathto.values[j];
                if (first.contains(chars.get(j)) && second.contains(chars.get(i)))
                    units[j] = units[i];
            }
        vector<String> ans = new vector<>();
        for(int i = 0; i < G.size; ++i)
            if(!hasUnit.containsKey(units[i])){
                ans.push_back(new String());
                String temp = ans.back();
                for(int j = i; j < G.size; ++j)
                    if(units[j] == units[i])
                        temp = temp + chars.get(j);
                char[] temp2 = temp.toCharArray();
                Arrays.sort(temp2);
                temp = new String(temp2);
                ans.values[ans.size - 1] = temp;
                hasUnit.put(units[i], true);
            }
        for(int i = 0; i < ans.size; ++i)
            System.out.println((String)ans.values[i]);
        in.close();
    }
}

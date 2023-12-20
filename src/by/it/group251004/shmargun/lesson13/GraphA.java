package by.it.group251004.shmargun.lesson13;

import java.util.*;

public class GraphA {

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

    public static void main(String[] args){
        vector<vector <Character> > G = new vector<>();
        Map<Character, Integer> indexes = new HashMap<>();
        Map<Integer, Character> chars = new HashMap<>();
        Map<Integer, Boolean> used = new HashMap<>();
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
                chars.put(free_index - 1, a.charAt(0));
                vector<Character> temp = new vector<>();
                G.push_back(temp);
                ++count;
            }
            if(!indexes.containsKey(c.charAt(0))) {
                indexes.put(c.charAt(0), free_index++);
                chars.put(free_index - 1, c.charAt(0));
                vector<Character> temp = new vector<>();
                G.push_back(temp);
                ++count;
            }
            vector<Character> temp = (vector<Character>)G.values[indexes.get(c.charAt(0))];
            temp.push_back(a.charAt(0));
        }
        while(count > 0){
            int min = count + 1;
            int min_index = 0;
            Character min_name = 0;
            --count;
            for(int i = 0; i < G.size; ++i){
                if(!used.containsKey(i)) {
                    vector<Character> temp = (vector<Character>) G.values[i];
                    if (min > temp.size || (min == temp.size && chars.get(i) < min_name)) {
                        min = temp.size;
                        min_index = i;
                        min_name = chars.get(i);
                    }
                }
            }
            System.out.print(min_name);
            System.out.print(' ');
            used.put(min_index, true);
            for(int i = 0; i < G.size; ++i) {
                vector<Character> temp = (vector<Character>)G.values[i];
                int ind = temp.find(min_name);
                if(ind != -1)
                    temp.remove(ind);
            }
        }
        in.close();
    }
}

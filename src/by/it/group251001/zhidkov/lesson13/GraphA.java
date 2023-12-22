package by.it.group251001.zhidkov.lesson13;
import java.util.*;

public class GraphA {

    private static class Vector<T> {
        private List<T> values = new ArrayList<>();

        public boolean isEmpty() {
            return values.isEmpty();
        }

        public void pushBack(T value) {
            values.add(value);
        }

        public boolean contains(T val) {
            return values.contains(val);
        }

        public int find(T val) {
            return values.indexOf(val);
        }

        public void remove(int index) {
            values.remove(index);
        }

        public void popBack() {
            if (!values.isEmpty()) {
                values.remove(values.size() - 1);
            }
        }

        public T front() {
            return values.isEmpty() ? null : values.get(0);
        }

        public T back() {
            return values.isEmpty() ? null : values.get(values.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<Vector<Character>> G = new ArrayList<>();
        Map<Character, Integer> indexes = new HashMap<>();
        Map<Integer, Character> chars = new HashMap<>();
        Map<Integer, Boolean> used = new HashMap<>();
        Integer freeIndex = 0;

        Scanner in = new Scanner(System.in);
        boolean stopInput = false;

        while (!stopInput) {
            String a = in.next();
            String c = in.next();
            c = in.next();
            if (c.charAt(c.length() - 1) != ',') {
                stopInput = true;
            } else {
                c = c.substring(0, c.length() - 1);
            }

            if (!indexes.containsKey(a.charAt(0))) {
                indexes.put(a.charAt(0), freeIndex);
                chars.put(freeIndex++, a.charAt(0));
                G.add(new Vector<>());
            }

            if (!indexes.containsKey(c.charAt(0))) {
                indexes.put(c.charAt(0), freeIndex);
                chars.put(freeIndex++, c.charAt(0));
                G.add(new Vector<>());
            }

            Vector<Character> temp = G.get(indexes.get(c.charAt(0)));
            temp.pushBack(a.charAt(0));
        }

        int count = G.size();

        while (count > 0) {
            int min = count + 1;
            int minIndex = 0;
            Character minName = 0;

            --count;

            for (int i = 0; i < G.size(); ++i) {
                if (!used.containsKey(i)) {
                    Vector<Character> temp = G.get(i);
                    if (min > temp.values.size() || (min == temp.values.size() && chars.get(i) < minName)) {
                        min = temp.values.size();
                        minIndex = i;
                        minName = chars.get(i);
                    }
                }
            }

            System.out.print(minName);
            System.out.print(' ');
            used.put(minIndex, true);

            for (int i = 0; i < G.size(); ++i) {
                Vector<Character> temp = G.get(i);
                int ind = temp.find(minName);
                if (ind != -1) {
                    temp.remove(ind);
                }
            }
        }

        in.close();
    }
}

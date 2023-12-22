package by.it.group251001.zhidkov.lesson13;
import java.util.*;

public class GraphB {

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

    static boolean DFS(Integer v, Integer[] used, List<Vector<Integer>> G) {
        Vector<Integer> temp = G.get(v);
        boolean res = false;
        for (Integer i : temp.values) {
            if (used[i] == 0 || used[i] == 2) {
                if (used[i] == 0) {
                    used[i] = 1;
                }
                res = res || DFS(i, used, G);
            } else {
                res = true;
            }
        }
        used[v] = 2;
        return res;
    }

    public static void main(String[] args) {
        List<Vector<Integer>> G = new ArrayList<>();
        Map<Character, Integer> indexes = new HashMap<>();
        Integer freeIndex = 0;
        Scanner in = new Scanner(System.in);
        boolean stopInput = false;
        int count = 0;

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
                indexes.put(a.charAt(0), freeIndex++);
                G.add(new Vector<>());
                ++count;
            }

            if (!indexes.containsKey(c.charAt(0))) {
                indexes.put(c.charAt(0), freeIndex++);
                G.add(new Vector<>());
                ++count;
            }

            Vector<Integer> temp = G.get(indexes.get(a.charAt(0)));
            temp.pushBack(indexes.get(c.charAt(0)));
        }

        boolean cycle = false;

        for (int z = 0; !cycle && z < G.size(); ++z) {
            Integer[] used = new Integer[G.size()];
            Arrays.fill(used, 0);
            used[z] = 1;
            cycle = DFS(z, used, G);
        }

        System.out.println(cycle ? "yes" : "no");
        in.close();
    }
}


package by.it.group251003.gridusko_bogdan.lesson13;

import java.util.*;

public class GraphA {

    static class LexicalComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s2.compareTo(s1);
        }
    }

    public static void main(String[] args) {
        Map<String, ArrayList<String>> neighbours = new HashMap<>();
        Stack<String> st = new Stack<>();
        Set<String> vis = new HashSet<>();

        Scanner scanner = new Scanner(System.in);
        String[] vals = scanner.nextLine().split(",");
        scanner.close();

        String start;
        String end;

        for (String s : vals) {
            String[] current = s.trim().split(" ");
            start = current[0];
            end = (current[current.length - 1]);
            if (neighbours.get(start) == null)
                neighbours.put(start, new ArrayList<>());

            neighbours.get(start).add(end);
        }

        for (ArrayList<String> list: neighbours.values()) {
            list.sort(new LexicalComparator());
        }

        for (String w : neighbours.keySet()) {
            if (!vis.contains(w)) {
                dfs(neighbours, w, vis, st);
            }
        }

        while (!st.empty()) {
            System.out.print(st.pop() + " ");
        }
    }

    private static void dfs(Map<String, ArrayList<String>> neighbours,
                       String v, Set<String> vis, Stack<String> st) {
        vis.add(v);

        if (neighbours.get(v) != null) {
            for (String child : neighbours.get(v)) {
                if (!vis.contains(child)) {
                    dfs(neighbours, child, vis, st);
                }
            }
        }

        st.push(v);
    }
}

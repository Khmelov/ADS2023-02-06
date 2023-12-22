package by.it.group251001.lashkin.lesson13;

import java.util.*;

public class GraphC {

    public static class LexicalComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s2.compareTo(s1);
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> neighbours = new HashMap<>();
        Stack<String> st = new Stack<>();
        Set<String> vis = new HashSet<>();

        Scanner scanner = new Scanner(System.in);
        String[] val = scanner.nextLine().split(",");
        scanner.close();

        for (String s : val) {
            String[] current = s.trim().split("");
            String start = current[0];
            String end = current[current.length - 1];

            neighbours.computeIfAbsent(start, k -> new ArrayList<>()).add(end);
        }

        neighbours.forEach((k, v) -> v.sort(new LexicalComparator()));

        for (String w : neighbours.keySet()) {
            if (!vis.contains(w)) {
                dfs(neighbours, w, vis, st);
            }
        }

        Map<String, List<String>> transNeighbours = new HashMap<>();
        for (String v : neighbours.keySet()) {
            for (String child : neighbours.get(v)) {
                transNeighbours.computeIfAbsent(child, k -> new ArrayList<>()).add(v);
            }
        }

        vis.clear();
        while (!st.empty()) {
            String v = st.pop();
            if (!vis.contains(v)) {
                StringBuilder sb = new StringBuilder();
                dfs(transNeighbours, v, vis, sb);
                char[] charArr = sb.toString().toCharArray();
                Arrays.sort(charArr);
                System.out.println(new String(charArr));
            }
        }
    }

    private static void dfs(Map<String, List<String>> neighbours,
                            String v, Set<String> vis, Stack<String> st) {
        vis.add(v);
        if (neighbours.containsKey(v)) {
            for (String child : neighbours.get(v)) {
                if (!vis.contains(child)) {
                    dfs(neighbours, child, vis, st);
                }
            }
        }
        st.push(v);
    }

    private static void dfs(Map<String, List<String>> neighbours,
                            String v, Set<String> vis, StringBuilder sb) {
        vis.add(v);
        sb.append(v);
        if (neighbours.containsKey(v)) {
            for (String child : neighbours.get(v)) {
                if (!vis.contains(child)) {
                    dfs(neighbours, child, vis, sb);
                }
            }
        }
    }
}


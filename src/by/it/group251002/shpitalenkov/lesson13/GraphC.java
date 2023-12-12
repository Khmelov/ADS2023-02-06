package by.it.group251002.shpitalenkov.lesson13;

import java.util.*;
import java.util.Map.Entry;

public class GraphC {
    static Integer currTime = 0;

    private static void dfsTime(String node, Map<String, ArrayList<String>> graph, Set<String> visited, Map<String, Integer> time) {
        visited.add(node);
        if (graph.get(node) != null)
            for (String next_node : graph.get(node)) {
                if (!visited.contains(next_node)) {
                    currTime++;
                    dfsTime(next_node, graph, visited, time);
                }
            }
        time.put(node, currTime++);
    }

    private static void dfs(String node, Map<String, ArrayList<String>> graph, Set<String> visited, List<String> path) {
        visited.add(node);
        path.add(node);
        if (graph.get(node) != null)
            for (String next_node : graph.get(node)) {
                if (!visited.contains(next_node)) {
                    dfs(next_node, graph, visited, path);
                }
            }
    }

    static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    static class MapEntryComparator implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
            int valueComparison = entry1.getValue().compareTo(entry2.getValue());
            if (valueComparison == 0) {
                return entry2.getKey().compareTo(entry1.getKey());
            }
            return valueComparison;
        }
    }

    public static void main(String[] args) {
        Map<String, ArrayList<String>> graph = new HashMap<>();
        Map<String, ArrayList<String>> graphReversed = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Map<String, Integer> time = new HashMap<>();

        Scanner in = new Scanner(System.in);

        boolean isEnd = false;

        String start = null;

        while (!isEnd) {
            String a = in.next();
            if (start == null)
                start = a;
            String s = in.next();
            String b = in.next();
            if (b.charAt(b.length() - 1) == ',') {
                b = b.substring(0, s.length() - 1);
            } else {
                isEnd = true;
            }
            if (!graph.containsKey(a))
                graph.put(a, new ArrayList<>());
            graph.get(a).add(b);

            if (!graphReversed.containsKey(b))
                graphReversed.put(b, new ArrayList<>());
            graphReversed.get(b).add(a);
        }

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfsTime(node, graph, visited, time);
            }
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(time.entrySet());
        entryList.sort(new MapEntryComparator());
        String[] vertices = new String[entryList.size()];
        for (int i = entryList.size() - 1; i > -1; i--) {
            vertices[entryList.size() - 1 - i] = entryList.get(i).getKey();
        }

        visited = new HashSet<>();
        for (String node : vertices) {
            if (!visited.contains(node)) {
                List<String> path = new ArrayList<>();
                dfs(node, graphReversed, visited, path);

                path.sort(new StringComparator());

                for (String n : path) {
                    System.out.print(n);
                }
                System.out.println();
            }
        }

        in.close();
    }
}
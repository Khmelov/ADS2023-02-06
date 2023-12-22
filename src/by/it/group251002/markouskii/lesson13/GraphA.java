package by.it.group251002.markouskii.lesson13;

import java.util.*;


public class GraphA {

    private static void dfs(String node, Map<String, ArrayList<String>> graph, Set<String> visited, Stack<String> stack) {
        visited.add(node);
        ArrayList<String> Nodes = graph.get(node);
        if (graph.get(node) != null) {
            for (String str : Nodes) {
                if (!visited.contains(str))
                    dfs(str, graph, visited, stack);
            }
        }
        stack.push(node);
    }

    static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s2.compareTo(s1);
        }
    }

    public static void main(String[] args) {
        Map<String, ArrayList<String>> graph = new HashMap<>();
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

        Scanner in = new Scanner(System.in);

        boolean isEnd = false;

        while (!isEnd) {
            String a = in.next();
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
        }
        for (ArrayList<String> Nodes :
                graph.values()) {
            Nodes.sort(new StringComparator());
        }

        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                dfs(node, graph, visited, stack);
            }
        }
        while (!stack.empty()) {
            System.out.print(stack.pop());
            System.out.print(' ');
        }
    }
}

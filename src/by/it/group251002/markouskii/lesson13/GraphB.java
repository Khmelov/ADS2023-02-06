package by.it.group251002.markouskii.lesson13;

import java.util.*;


public class GraphB {

    private static boolean dfs(String node, Map<String, ArrayList<String>> graph, Set<String> visited) {
        visited.add(node);
        ArrayList<String> Nodes = graph.get(node);
        boolean result=false;
        if (graph.get(node) != null) {
            for (String str : Nodes) {
                if (!visited.contains(str) && !result)
                    result = dfs(str, graph, new HashSet<>(visited));
                else
                    result=true;
            }
        }
        return result;
    }

    static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s2.compareTo(s1);
        }
    }

    public static void main(String[] args) {
        Map<String, ArrayList<String>> graph = new HashMap<>();
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
        boolean result=false;
        for (String node : graph.keySet()) {
            if (!visited.contains(node) && !result) {
                result=dfs(node, graph, new HashSet<>());
            }
        }
        if (!result)  System.out.println("no");
        else System.out.println("yes");
    }
}

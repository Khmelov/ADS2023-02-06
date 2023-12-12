package by.it.group251002.markouskii.lesson13;

import java.util.*;


public class GraphC {

    private static void dfs(String node, Map<String, ArrayList<String>> graph, Set<String> visited,Stack<String> stack) {
        visited.add(node);
        ArrayList<String> Nodes = graph.get(node);
        if (graph.get(node) != null) {
            for (String str : Nodes) {
                if (!visited.contains(str))
                    dfs(str, graph, visited,stack);
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
        Map<String, ArrayList<String>> Graph = new HashMap<>();
        Map<String, ArrayList<String>> ReversedGraph = new HashMap<>();
        Stack<String> stack = new Stack<>();
        Set<String> visited = new HashSet<>();

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
            if (!Graph.containsKey(a))
                Graph.put(a, new ArrayList<>());
            Graph.get(a).add(b);

            if (!ReversedGraph.containsKey(b))
                ReversedGraph.put(b, new ArrayList<>());
            ReversedGraph.get(b).add(a);
        }
        for (ArrayList<String> Nodes :
                Graph.values()) {
            Nodes.sort(new StringComparator());
        }
        for (ArrayList<String> Nodes :
                ReversedGraph.values()) {
            Nodes.sort(new StringComparator());
        }
        for (String node : ReversedGraph.keySet()) {
            if (!visited.contains(node)) {
                dfs(node, ReversedGraph, visited,stack);
            }
        }
        Stack<String> Output=new Stack<>();
        visited = new HashSet<>();
        while (!stack.empty()){
            String node=stack.pop();
            if (node!=null) {
                if (!visited.contains(node)) {
                    Stack<String> stacker = new Stack<>();
                    dfs(node, Graph, visited, stacker);
                    StringBuilder a = new StringBuilder();
                    while (!stacker.empty()) {
                        a.append(stacker.pop());
                    }
                    Output.push(a.toString());
                }
            }
        }
        while (!Output.empty()){
            System.out.println(Output.pop());
        }
    }
}

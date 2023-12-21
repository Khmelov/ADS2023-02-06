package by.it.group251002.samoilenko.lesson13;

import java.util.*;
public class GraphA {
    static void Explore(String v, HashMap<String, LinkedHashSet<String>> graph, HashMap<String, Boolean> visited, Stack<String> stack) {
        visited.put(v, true);
        if (graph.get(v) != null) {
            for (String u : graph.get(v)) {
                if (!visited.get(u))
                    Explore(u, graph, visited, stack);
            }
        }
        stack.push(v);
    }
    static void DFS(HashMap<String, LinkedHashSet<String>> graph, HashSet<String> vers) {
        HashMap<String, Boolean> visited = new HashMap<>();
        Stack<String> stack = new Stack<>();
        for (String s : vers) {
            visited.put(s, false);
        }
        for (String s : vers) {
            if (!visited.get(s))
                Explore(s, graph, visited, stack);
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str[] = sc.nextLine().split(", ");
        String[] nums = new String[2];
        HashMap<String, LinkedHashSet<String>> graph = new HashMap<String, LinkedHashSet<String>>();
        HashSet<String> vers = new HashSet<String>();
        for (String s : str) {
            nums = s.replace(" ", "").split("->");
            if (!graph.containsKey(nums[0])) {
                graph.put(nums[0], new LinkedHashSet<String>());
            }
            graph.get(nums[0]).add(nums[1]);
            vers.add(nums[1]);
            vers.add(nums[0]);
        }
        for (String s : graph.keySet()) {
            List list = new ArrayList(graph.get(s));
            Collections.sort(list, Collections.reverseOrder());
            graph.put(s, new LinkedHashSet<>(list));
        }
        DFS(graph, vers);
    }
}
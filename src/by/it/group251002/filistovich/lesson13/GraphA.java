package by.it.group251002.filistovich.lesson13;

import java.util.*;

class GraphA {
    private Map<Character, List<Character>> gr;

    public GraphA() {
        gr = new TreeMap<>(Collections.reverseOrder());
    }

    public void addEdge(char first, char second) {
        if (!gr.containsKey(first)) {
            gr.put(first, new ArrayList<>());
        }
        gr.get(first).add(second);
    }
    public void topologicalSort(){
        for (List<Character> temp : gr.values()) {
            Collections.sort(temp, Collections.reverseOrder());
        }
        Stack<Character> stack = new Stack<>();
        Set<Character> visited = new HashSet<>();

        for (char tmpNode : gr.keySet()) {
            if (!visited.contains(tmpNode)) {
                DFS(tmpNode, visited, stack);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private void DFS(char node, Set<Character> visited, Stack<Character> stack) {
        visited.add(node);

        for (char tempnode : gr.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(tempnode)) {
                DFS(tempnode, visited, stack);
            }
        }
        stack.push(node);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        GraphA graph = new GraphA();

        String[] edges = input.split(", ");
        for (String edge : edges) {
            String[] vert = edge.split(" -> ");
            char A = vert[0].charAt(0);
            char B = vert[1].charAt(0);
            graph.addEdge(A, B);
        }

        graph.topologicalSort();
    }
}
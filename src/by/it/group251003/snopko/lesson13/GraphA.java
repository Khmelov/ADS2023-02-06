package by.it.group251003.snopko.lesson13;

import java.util.*;

class GraphA {
    private Map<Character, List<Character>> graph;

    public GraphA() {
        graph = new TreeMap<>(Collections.reverseOrder());
    }

    public void addEdge(char A, char B) {
        if (!graph.containsKey(A)) {
            graph.put(A, new ArrayList<>());
        }
        graph.get(A).add(B);
    }
    public void topologicalSort(){
        for (List<Character> temp : graph.values()) {
            Collections.sort(temp, Collections.reverseOrder());
        }
        Stack<Character> stack = new Stack<>();
        Set<Character> visited = new HashSet<>();

        for (char tempnode : graph.keySet()) {
            if (!visited.contains(tempnode)) {
                DFS(tempnode, visited, stack);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private void DFS(char node, Set<Character> visited, Stack<Character> stack) {
        visited.add(node);

        for (char tempnode : graph.getOrDefault(node, new ArrayList<>())) {
            if (!visited.contains(tempnode)) {
                DFS(tempnode, visited, stack);
            }
        }
        stack.push(node);
    }
/*
    public void topologicalSort() {
        Map<Character, Integer> inDegree = new HashMap<>();
        for (char node : graph.keySet()) {
            inDegree.put(node, 0);
        }


        for (List<Character> nearNodes : graph.values()) {
            for (char node : nearNodes) {
                inDegree.put(node, inDegree.getOrDefault(node, 0) + 1);
            }
        }

        Queue<Character> queue = new PriorityQueue<>();

        for (char node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.offer(node);
            }
        }

        List<Character> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            char current = queue.poll();
            result.add(current);

            if (graph.containsKey(current)) {
                for (char node : graph.get(current)) {
                    Integer degree = inDegree.get(node);
                    if (degree != null) {
                        inDegree.put(node, degree - 1);
                        if (degree - 1 == 0) {
                            queue.offer(node);
                        }
                    }
                }
            }
        }

        for (char node : result) {
            System.out.print(node + " ");
        }
    }
*/


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        GraphA graph = new GraphA();

        String[] edges = input.split(", ");
        for (String edge : edges) {
            String[] vertices = edge.split(" -> ");
            char A = vertices[0].charAt(0);
            char B = vertices[1].charAt(0);
            graph.addEdge(A, B);
        }

        graph.topologicalSort();
    }
}

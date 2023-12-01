package by.it.group251001.zhidkov.lesson13;
import java.util.Stack;

public class Graph {
    private int V;
    private boolean hasCycle;
    private int[][] adjMatrix;
    public Graph(int v) {
        V = v;
        adjMatrix = new int[v][v];
        hasCycle = false;
    }
    public void addEdge(int v, int w) {
        adjMatrix[v][w] = 1;
    }
    public void topologicalSort(boolean isLetter) {
        Stack<String> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for (int i = V - 1; i >= 0 ; i--) {
            if (!visited[i]) {
                DFS_A(i, visited, stack, isLetter);
            }
        }
        // Вывод результата
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
    public boolean isHasCycle() {
        int[] visited = new int[V];
        for (int i = 0; i < V ; i++) {
            if (visited[i] == 0) {
                DFS_B(i, visited);
            }
        }
        return hasCycle;
    }
    private void DFS_A(int v, boolean[] visited, Stack<String> stack, boolean isLetter) {
        visited[v] = true;

        for (int i = V - 1; i >= 0 ; i--) {
            if (adjMatrix[v][i] == 1 && !visited[i]) {
                DFS_A(i, visited, stack, isLetter);
            }
        }
        if (!isLetter) {
            stack.push(String.valueOf(v));
        }
        else {
            stack.push(String.valueOf((char) ('A' + v)));
        }
    }
    private void DFS_B(int v, int[] visited) {
        visited[v] = 1;

        for (int i = 0; i < V ; i++) {
            if (adjMatrix[v][i] == 1) {
                if (visited[i] == 0)
                    DFS_B(i, visited);
                else
                    if (visited[i] == 1)
                        hasCycle = true;
            }
        }
        visited[v] = 2;
    }
}
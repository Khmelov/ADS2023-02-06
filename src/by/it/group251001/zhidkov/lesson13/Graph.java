package by.it.group251001.zhidkov.lesson13;
import java.util.Stack;

public class Graph {
    private int V;
    private int[][] adjMatrix;
    public Graph(int v) {
        V = v;
        adjMatrix = new int[v][v];
    }
    public void addEdge(int v, int w) {
        adjMatrix[v][w] = 1;
    }
    public void topologicalSort(boolean isLetter) {
        Stack<String> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for (int i = V - 1; i >= 0 ; i--) {
            if (!visited[i]) {
                DFS(i, visited, stack, isLetter);
            }
        }
        // Вывод результата
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
    private void DFS(int v, boolean[] visited, Stack<String> stack, boolean isLetter) {
        visited[v] = true;

        for (int i = V - 1; i >= 0 ; i--) {
            if (adjMatrix[v][i] == 1 && !visited[i]) {
                DFS(i, visited, stack, isLetter);
            }
        }
        if (!isLetter) {
            stack.push(String.valueOf(v));
        }
        else {
            stack.push(String.valueOf((char) ('A' + v)));
        }
    }
}
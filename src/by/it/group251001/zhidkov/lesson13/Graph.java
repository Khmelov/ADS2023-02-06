package by.it.group251001.zhidkov.lesson13;
import java.util.Stack;
public class Graph {
    public int V;
    private boolean hasCycle;
    public int[][] adjMatrix;
    public Graph(int v) {
        V = v;
        adjMatrix = new int[v][v];
        hasCycle = false;
    }
    public void addEdge(int v, int w) {
        adjMatrix[v][w] = 1;
    }
    public void topSort(boolean isLetter, Stack<String> stack) {
        boolean[] visited = new boolean[V];
        for (int i = V - 1; i >= 0 ; i--)
            if (!visited[i])
                DFS_A(i, visited, stack, isLetter);
    }
    public void topologicalSort(boolean isLetter) {
        Stack<String> stack = new Stack<>();
        topSort(isLetter, stack);
        while (!stack.isEmpty())
            System.out.print(stack.pop() + " ");
    }
    public int findMax(int[] visited) {
        int max = 0;
        for (int i = 0; i < V; i++)
            if (visited[i] > max)
                max = visited[i];
        return max;
    }
    public void findComponents(boolean isLetter, int[] order) {
        int[] visited = new int[V];
        int Count = 0;
        for (int i = 0; i < V; i++)
            if (visited[order[i]] == 0)
                DFS_C(order[i], ++Count, visited);
        int max = findMax(visited);
        String line = "";
        for (int i = 0; (i < V) && (max != 0); i++) {
            for (int j = 0; j < V; j++)
                if (visited[j] == max)
                    if (isLetter)
                        line += (char) (j + 'A');
                    else
                        line += j;
            max--;
            if (max != 0)
                line += "\n";
        }
        System.out.print(line);
    }
    public int[] findOrder(boolean isLetter) {
        Stack<String> stack = new Stack<>();
        topSort(isLetter, stack);
        int[] order = new int[V];
        int i = 0;
        while (!stack.isEmpty())
            if (isLetter)
                order[i++] = (int)stack.pop().charAt(0) - 'A';
            else
                order[i++] = Integer.valueOf(stack.pop());
        return order;
    }
    public boolean isHasCycle() {
        int[] visited = new int[V];
        for (int i = 0; i < V ; i++)
            if (visited[i] == 0)
                DFS_B(i, visited);
        return hasCycle;
    }
    private void DFS_A(int v, boolean[] visited, Stack<String> stack, boolean isLetter) {
        visited[v] = true;
        for (int i = V - 1; i >= 0 ; i--)
            if (adjMatrix[v][i] == 1 && !visited[i])
                DFS_A(i, visited, stack, isLetter);
        if (!isLetter)
            stack.push(String.valueOf(v));
        else
            stack.push(String.valueOf((char) ('A' + v)));
    }
    private void DFS_B(int v, int[] visited) {
        visited[v] = 1;
        for (int i = 0; i < V ; i++)
            if (adjMatrix[v][i] == 1)
                if (visited[i] == 0)
                    DFS_B(i, visited);
                else
                    if (visited[i] == 1)
                        hasCycle = true;
        visited[v] = 2;
    }
    private void DFS_C(int v, int Component, int[] visited) {
        visited[v] = Component;
        for (int i = 0; i < V; i++)
            if (adjMatrix[v][i] == 1)
                if (visited[i] == 0)
                    DFS_C(i, Component, visited);
    }
}
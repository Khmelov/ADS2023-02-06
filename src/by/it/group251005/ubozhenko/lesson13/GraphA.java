package by.it.group251005.ubozhenko.lesson13;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class GraphA {

    static boolean[] visited;
    static ArrayList<String> topological;

    static int getMax(int[] startVertex, int[] endVertex) {
        int size = Integer.MIN_VALUE;
        for (int i = 0; i < startVertex.length; i++) {
            if (startVertex[i] > size)
                size = startVertex[i];
            if (endVertex[i] > size)
                size = endVertex[i];
        }
        return size;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] arr = scan.nextLine().split(",");

        boolean isNumeric = arr[0].trim().matches(".*\\d.*");

        int[] startVertex = new int[arr.length];
        int[] endVertex = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            String[] current = arr[i].trim().split(" ");
            startVertex[i] = isNumeric ? Integer.parseInt(current[0]) : current[0].charAt(0) - 'A';
            endVertex[i] = isNumeric ? Integer.parseInt(current[current.length - 1]) : current[current.length - 1].charAt(0) - 'A';
        }

        GraphUtil graph = new GraphUtil(getMax(startVertex, endVertex) + 1);

        for (int i = 0; i < arr.length; i++) {
            graph.addOrientedEdge(startVertex[i], endVertex[i]);
            String temp = isNumeric ? String.valueOf(startVertex[i]) : String.valueOf((char)(startVertex[i] + 'A'));
            graph.setVertexNames(startVertex[i], temp);
            temp = isNumeric ? String.valueOf(endVertex[i]) : String.valueOf((char)(endVertex[i] + 'A'));
            graph.setVertexNames(endVertex[i], temp);
        }

        TopologicalSort(graph, 0);
        int i = 0;
        for (String item : topological) {
            System.out.print(i == topological.size() - 1 ? topological.get(i) : (topological.get(i) + " "));
            i++;
        }
        scan.close();
    }

    public static void TopologicalSort(GraphUtil graph, int start) {
        visited = new boolean[graph.vertexCount];
        topological = new ArrayList<>();
        for (int i = start; i < graph.vertexCount; i++)
            if (!visited[i])
                DFS(graph, i);
        Collections.reverse(topological);
    }

    public static void DFS(GraphUtil graph, int vertex) {
        visited[vertex] = true;
        for (int i : graph.getNeighbors(vertex))
            if (!visited[i])
                DFS(graph, i);
        topological.add(graph.getVertexName(vertex));
    }
}

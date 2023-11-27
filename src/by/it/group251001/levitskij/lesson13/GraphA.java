package by.it.group251001.levitskij.lesson13;



import java.util.Scanner;

public class GraphA{

    public static void main(String[] args) {
        Graph graph = new Graph();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter(", ");
        while (scanner.hasNext()) {
            String line = scanner.next();
            String[] edge = line.split(" -> ");
            if (edge.length==2)
                graph.addEdge(edge[0], edge[1]);
        }
        System.out.println(graph.toposort());
    }
}

package by.it.group251001.zhidkov.lesson13;
import java.util.*;
public class GraphC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GraphA.fill();
        while (in.hasNextLine()) {
            Graph graph = GraphA.graphCreate(GraphA.strAnalisys(in));
            Graph graphRev = new Graph(graph.V);
            for (int i = 0; i < graphRev.V; i++)
                for (int j = 0; j < graphRev.V; j++)
                    graphRev.adjMatrix[i][j] = graph.adjMatrix[j][i];
            graph.findComponents(GraphA.isLetter, graphRev.findOrder(GraphA.isLetter));
        }
    }
}

package by.it.group251001.zhidkov.lesson13;
import java.util.Scanner;
public class GraphB {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        GraphA.fill();
        while (in.hasNextLine()) {
            Graph graph = GraphA.graphCreate(GraphA.strAnalisys(in));
            if (graph.isHasCycle())
                System.out.print("yes");
            else
                System.out.print("no");
        }
    }
}

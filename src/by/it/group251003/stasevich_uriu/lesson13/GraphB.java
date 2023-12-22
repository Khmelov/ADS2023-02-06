package by.it.group251003.stasevich_uriu.lesson13;

import java.util.*;

public class GraphB {
    private final Map<String, List<String>> adList = new HashMap<>();
    private boolean hasCycle = false;
    public boolean hasCycle() { return hasCycle; }
    public GraphB(String input) {
        var edges = input.split(", ");
        for (var edge : edges) {

            var vertices = edge.split(" -> ");
            var from = vertices[0];
            var to = vertices[1];

            adList.computeIfAbsent(from, _1_ -> new ArrayList<>()).add(to);
        }
        checkCycle();
    }
    private void checkCycle() {
        var marked  = new HashSet<String>();
        var onStack = new HashSet<String>();
        for (var ver : adList.keySet())
            if (!marked.contains(ver))
                checkCycle(ver, marked, onStack);
    }
    private void checkCycle(String ver, HashSet<String> marked, HashSet<String> onStack) {
        marked.add(ver);
        onStack.add(ver);
        var neighbors = adList.get(ver);
        if (neighbors != null)
            for (var neighbor : neighbors) {
                if (hasCycle)
                    return;

                if (!marked.contains(neighbor))
                    checkCycle(neighbor, marked, onStack);
                else if (onStack.contains(neighbor))
                    hasCycle = true;
            }
        onStack.remove(ver);
    }
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var graph = new GraphB(scanner.nextLine());
        System.out.print(graph.hasCycle() ? "yes" : "no");
    }
}
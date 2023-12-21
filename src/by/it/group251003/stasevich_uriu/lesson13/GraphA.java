package by.it.group251003.stasevich_uriu.lesson13;

import java.util.*;

public class GraphA {
    private final SortedMap<String, SortedSet<String>> adList = new TreeMap<>(Collections.reverseOrder());
    private List<String> topolOrder = null;
    private boolean hasCycle = false;
    public List<String> getTopolOrder() { return topolOrder; }
    public GraphA(String input) {
        var edges = input.split(", ");
        for (var edge : edges) {
            var vertices = edge.split(" -> ");
            var from = vertices[0];
            var to = vertices[1];
            adList.computeIfAbsent(from, _1_ -> new TreeSet<>(Collections.reverseOrder())).add(to);
        }
        checkCycle();
        if (!hasCycle) {
            topolOrder = new ArrayList<>();
            calcTopSort();
        }
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
    private void calcTopSort() {
        var marked = new HashSet<String>();
        for (var vertex : adList.keySet())
            if (!marked.contains(vertex))
                calcTopSort(vertex, marked);
        Collections.reverse(topolOrder);
    }
    private void calcTopSort(String ver, HashSet<String> marked) {
        marked.add(ver);
        var neighbors = adList.get(ver);
        if (neighbors != null)
            for (var neighbor : neighbors)
                if (!marked.contains(neighbor))
                    calcTopSort(neighbor, marked);
        topolOrder.add(ver);
    }
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var graph = new GraphA(scanner.nextLine());

        var topologicalOrder = graph.getTopolOrder();
        for (var vertex : topologicalOrder)
            System.out.print(vertex + " ");
    }
}

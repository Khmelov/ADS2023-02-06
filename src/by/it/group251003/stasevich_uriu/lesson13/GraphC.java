package by.it.group251003.stasevich_uriu.lesson13;


import java.util.*;

public class GraphC {
    private final Map<String, Set<String>> adList = new HashMap<>();
    private final List<List<String>> strongComponents = new ArrayList<>();
    public List<List<String>> getStrongComponents() { return strongComponents; }
    public GraphC(String input) {
        var edgs = input.split(", ");
        for (var ed : edgs) {
            var vertices = ed.split("->");
            var from = vertices[0];
            var to = vertices[1];
            adList.computeIfAbsent(from, _1_ -> new HashSet<>()).add(to);
        }
        calcStrongComponents();
    }
    private static class ReverseGraph {
        private final Map<String, Set<String>> adList = new HashMap<>();
        private final List<String> topolOrder = new ArrayList<>();
        public List<String> getTopolOrder() { return topolOrder; }
        public ReverseGraph(Map<String, Set<String>> adjacencyList) {
            for (var entry : adjacencyList.entrySet()) {
                var ver = entry.getKey();
                for (var neighbor : entry.getValue())
                    this.adList.computeIfAbsent(neighbor, _1_ -> new HashSet<>()).add(ver);
            }
            calcTopSort();
        }
        private void calcTopSort() {
            var marked = new HashSet<String>();
            for (var ver : adList.keySet())
                if (!marked.contains(ver))
                    calcTopSort(ver, marked);
            Collections.reverse(topolOrder);
        }
        private void calcTopSort(String vertex, HashSet<String> marked) {
            marked.add(vertex);
            var neighbors = adList.get(vertex);
            if (neighbors != null)
                for (var neighbor : neighbors)
                    if (!marked.contains(neighbor))
                        calcTopSort(neighbor, marked);
            topolOrder.add(vertex);
        }
    }
    private void calcStrongComponents() {
        var reverse = new ReverseGraph(adList);
        var topologicalOrder = reverse.getTopolOrder();

        var marked = new HashSet<String>();
        for (var vertex : topologicalOrder) {
            if (!marked.contains(vertex)) {
                List<String> component = new ArrayList<>();
                dfs(vertex, marked, component);
                strongComponents.add(component);
            }
        }
        Collections.reverse(strongComponents);
        for (var component : strongComponents)
            Collections.sort(component);
    }
    private void dfs(String ver, Set<String> marked, List<String> component) {
        marked.add(ver);
        component.add(ver);
        var neighbors = adList.get(ver);
        if (neighbors != null)
            for (var neighbor : neighbors)
                if (!marked.contains(neighbor))
                    dfs(neighbor, marked, component);
    }
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var graph = new GraphC(scanner.nextLine());

        var strongComponents = graph.getStrongComponents();
        for (var strongComponent : strongComponents) {
            for (var ver : strongComponent)
                System.out.print(ver);
            System.out.print('\n');
        }
    }
}

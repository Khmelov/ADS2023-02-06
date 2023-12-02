package by.it.group251003.snopko.lesson13;


import java.util.*;

class GraphC {
    private Map<Character, List<Character>> graph;
    private Map<Character, List<Character>> Tgraph;

    public GraphC() {
        graph = new TreeMap<>();
        Tgraph = new TreeMap<>();
    }

    public void addEdge(char A, char B) {
        Tgraph.putIfAbsent(A, new ArrayList<>());
        graph.putIfAbsent(B, new ArrayList<>());
        graph.get(B).add(A);
        Tgraph.get(A).add(B);
    }

    private void dfs1 (char node, Set<Character> visited, Deque<Character> order){
        visited.add(node);
        List <Character> NearNodes = graph.getOrDefault(node, new ArrayList<>());
        for (char temp: NearNodes){
            if (!visited.contains(temp)) {
                dfs1(temp, visited, order);
            }
        }
        order.addFirst(node);
    }

    private void dfs2 (char node, Set<Character> visited, List<Character> comp){
         visited.add(node);

         List <Character> NearNodes = Tgraph.getOrDefault(node, new ArrayList<>());
         for (char temp: NearNodes){
             if (!visited.contains(temp)){
                 dfs2(temp,visited,comp);
             }
         }
         comp.add(node);
    }

    public void components(){
        Set<Character> visited = new HashSet<>();
        Deque<Character> order = new ArrayDeque<>();
        for (char node: graph.keySet()) {
            if (!visited.contains(node)){
                dfs1(node,visited,order);
            }
        }
        visited.clear();
        List<List<Character>> ans = new ArrayList<>();
        while(!(order.size()==0)){
            char node = order.pollFirst();
            List <Character> comp = new ArrayList<>();
            if (!visited.contains(node)){
                dfs2(node,visited,comp);
                Collections.sort(comp);
                ans.add(comp);
            }
        }
        Collections.reverse(ans);
        for (List<Character> col: ans) {
            for (Character node : col) {
                System.out.print(node);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        GraphC graph = new GraphC();

        String[] edges = input.split(", ");
        for (String edge : edges) {
            String[] vertices = edge.split(" -> ");
            char A = vertices[0].trim().charAt(0);
            char B = vertices[1].trim().charAt(0);
            graph.addEdge(A, B);
        }
        graph.components();

    }
}
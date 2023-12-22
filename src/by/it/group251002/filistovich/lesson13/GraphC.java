package by.it.group251002.filistovich.lesson13;


import java.util.*;

class GraphC {
    private Map<Character, List<Character>> gr;
    private Map<Character, List<Character>> TrGr;

    public GraphC() {
        gr = new TreeMap<>();
        TrGr = new TreeMap<>();
    }

    public void addEdge(char first, char second) {
        TrGr.putIfAbsent(first, new ArrayList<>());
        gr.putIfAbsent(second, new ArrayList<>());
        gr.get(second).add(first);
        TrGr.get(first).add(second);
    }

    private void firstDFS(char node, Set<Character> visited, Deque<Character> order){
        visited.add(node);
        List <Character> NearNodes = gr.getOrDefault(node, new ArrayList<>());
        for (char tempNode: NearNodes){
            if (!visited.contains(tempNode)) {
                firstDFS(tempNode, visited, order);
            }
        }
        order.addFirst(node);
    }

    private void secondDFS(char node, Set<Character> visited, List<Character> cmp){
        visited.add(node);

        List <Character> NearNodes = TrGr.getOrDefault(node, new ArrayList<>());
        for (char tempNode: NearNodes){
            if (!visited.contains(tempNode)){
                secondDFS(tempNode,visited,cmp);
            }
        }
        cmp.add(node);
    }

    public void components(){
        Set<Character> visited = new HashSet<>();
        Deque<Character> order = new ArrayDeque<>();
        for (char node: gr.keySet()) {
            if (!visited.contains(node)){
                firstDFS(node,visited,order);
            }
        }
        visited.clear();
        List<List<Character>> ans = new ArrayList<>();
        while(!(order.size()==0)){
            char node = order.pollFirst();
            List <Character> comp = new ArrayList<>();
            if (!visited.contains(node)){
                secondDFS(node,visited,comp);
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

        GraphC gr = new GraphC();

        String[] edges = input.split(", ");
        for (String edge : edges) {
            String[] vertices = edge.split(" -> ");
            char A = vertices[0].trim().charAt(0);
            char B = vertices[1].trim().charAt(0);
            gr.addEdge(A, B);
        }
        gr.components();

    }
}
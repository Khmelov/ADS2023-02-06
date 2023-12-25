package by.it.group251002.filistovich.lesson13;

import java.util.*;

class GraphB {
    private Map<Character, List<Character>> gr;

    public GraphB() {
        gr = new TreeMap<>();
    }

    public void addEdge(char first, char second) {
        if (!gr.containsKey(first)) {
            gr.put(first, new ArrayList<>());
        }
        gr.get(first).add(second);
    }

    // deep Searching
    private boolean DFS (char node,  Set<Character> glVisited, Set<Character> local){
        if (local.contains(node))
            return true;
        if (glVisited.contains(node))
            return false;

        glVisited.add(node);
        local.add(node);

        List <Character> NearNodesList = gr.getOrDefault(node, new ArrayList<>());
        for (char tempNode: NearNodesList){
            if (DFS(tempNode,glVisited,local)){
                return true;
            }
        }
        local.remove(node);
        return false;
    }

    public boolean CycleCheck(){
        Set <Character> glVisited = new HashSet<>();
        boolean state = false;
        for (char node: gr.keySet()){
            if(!glVisited.contains(node)){
                Set <Character> Local = new HashSet<>();
                if (DFS(node, glVisited, Local)) return true;


            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        GraphB graph = new GraphB();

        String[] edges = input.split(", ");
        for (String edge : edges) {
            String[] vertices = edge.split(" -> ");
            char A = vertices[0].charAt(0);
            char B = vertices[1].charAt(0);
            graph.addEdge(A, B);
        }

        if (graph.CycleCheck()) {
            System.out.print("yes");
        } else {
            System.out.print("no");
        }

    }
}
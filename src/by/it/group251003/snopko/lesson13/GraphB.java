package by.it.group251003.snopko.lesson13;

import java.util.*;

class GraphB {
    private Map<Character, List<Character>> graph;

    public GraphB() {
        graph = new TreeMap<>();
    }

    public void addEdge(char A, char B) {
        if (!graph.containsKey(A)) {
            graph.put(A, new ArrayList<>());
        }
        graph.get(A).add(B);
    }

    private boolean DFS (char node,  Set<Character> GlobalVisited, Set<Character> Local){
        if (Local.contains(node))
          return true;
        if (GlobalVisited.contains(node))
            return false;

        GlobalVisited.add(node);
        Local.add(node);

        List <Character> NearNodes = graph.getOrDefault(node, new ArrayList<>());
        for (char temp: NearNodes){
            if (DFS(temp,GlobalVisited,Local)){
                return true;
            }
        }
        Local.remove(node);
        return false;
    }

    public boolean CycleCheck(){
        Set <Character> GlobalVisited = new HashSet<>();
        boolean state = false;
        for (char node: graph.keySet()){
            if(!GlobalVisited.contains(node)){
                Set <Character> Local = new HashSet<>();
                if (DFS(node, GlobalVisited, Local)) return true;


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

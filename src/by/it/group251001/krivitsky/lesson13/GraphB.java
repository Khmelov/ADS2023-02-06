package by.it.group251001.krivitsky.lesson13;

import java.util.*;

public class GraphB {

    public List<int[]> vertex;

    public GraphB(){
        vertex = new ArrayList<>();
    }

    private boolean findCycles(){
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < vertex.size(); i++){
            if (!visited.contains(i)){
                if (findRec(vertex.get(i)[0], visited) == true){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findRec(Integer curr, Set visited){
        visited.add(curr);

        for (int i = 0; i < vertex.size(); i++) {
            int[] edge = vertex.get(i);
            if (edge[0] == curr && !visited.contains(edge[1])) {
                return findRec(edge[1], visited);
            }
            else if ((edge[0] == curr) && visited.contains(edge[1])){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] edgeStrings = input.split(", ");

        GraphB graph = new GraphB();

        for (String temp: edgeStrings){
            String[] v = temp.split(" -> ");
            int startVertex = Integer.parseInt(v[0]);
            int endVertex = Integer.parseInt(v[1]);
            graph.vertex.add(new int[]{startVertex, endVertex});
        }

        boolean result = graph.findCycles();

        if (result){
            System.out.println("yes");
        }
        else{
            System.out.println("no");
        }
    }



}

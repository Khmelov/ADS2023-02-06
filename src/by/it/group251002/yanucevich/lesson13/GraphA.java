package by.it.group251002.yanucevich.lesson13;

import java.util.*;

public class GraphA {

    static void getTopology(String key, Stack<String> topologyStack, Set<String> visitedSet, Map<String,ArrayList<String>> graph){

        visitedSet.add(key);
        ArrayList<String> destinations = graph.get(key);
        if (destinations!=null){
            destinations.forEach(s -> {
                if (!visitedSet.contains(s)) {
                    getTopology(s,topologyStack,visitedSet,graph);
                }
            });
        }

        topologyStack.push(key);

    }
    public static void main(String[] args){
        Map<String, ArrayList<String>> adjacencyList=new HashMap<>();

        // putting the input value into the array
        Scanner scan = new Scanner(System.in);
        String[] values = scan.nextLine().split(",");
        scan.close();

        // building a graph
        String from,to;
        String[] currString;
        for(int i=0;i<values.length;i++){
            currString = values[i].split("->");
            from = currString[0].trim();
            to = currString[1].trim();

            if(adjacencyList.get(from)==null){
                adjacencyList.put(from,new ArrayList<>());
            }
            adjacencyList.get(from).add(to);
            if(adjacencyList.get(to)==null){
                adjacencyList.put(to,new ArrayList<>());
            }
        }

        // for topological sort the lists containing values
        // must be sorted in reverse so that the alphabetic
        // order is kept
        for (ArrayList<String> node:adjacencyList.values()){
            node.sort(Comparator.reverseOrder());
        }
        Set<String> visited = new HashSet<>();
        Stack<String> topologyOrder = new Stack<>();
        for (String s:adjacencyList.keySet()){
            if(!visited.contains(s)){
                getTopology(s,topologyOrder,visited,adjacencyList);
            }
        }

        String delimiter = "";
        while(!topologyOrder.isEmpty()){
            System.out.print(delimiter+topologyOrder.pop());
            delimiter=" ";
        }

    }

}

package by.it.group251002.yanucevich.lesson13;

import java.util.*;

public class GraphB {



    private static boolean isThereALoop(String key, Map<String,ArrayList<String>> graph, Set<String> inProcess){
        boolean result = false;
        if (inProcess.contains(key)){
            return true;
        }
        inProcess.add(key);
        ArrayList<String> destinations = graph.get(key);
        for (String d : destinations) {
            result = result || isThereALoop(d,graph,new HashSet<>(inProcess));
        }
        inProcess.remove(key);
        return result;
    }
    public static void main(String[] args){
        Map<String, ArrayList<String>> adjacencyList = new HashMap<>();
        Scanner scan = new Scanner(System.in);
        String[] inputArr = scan.nextLine().split(",");
        scan.close();

        String from, to;
        String[] currString;
        for(int i=0;i<inputArr.length;i++){
            currString=inputArr[i].split("->");
            from = currString[0].strip();
            to = currString[1].strip();

            if (adjacencyList.get(from)==null){
                adjacencyList.put(from, new ArrayList<>());
            }
            adjacencyList.get(from).add(to);
            if(adjacencyList.get(to)==null){
                adjacencyList.put(to,new ArrayList<>());
            }
        }

        boolean result = false;
        Set<String> elements = adjacencyList.keySet();
        for (String e : elements) {
            result = result || isThereALoop(e,adjacencyList,new HashSet<>());
        }

        System.out.println(result ? "yes" : "no");


    }
}

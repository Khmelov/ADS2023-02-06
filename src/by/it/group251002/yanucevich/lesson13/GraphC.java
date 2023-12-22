package by.it.group251002.yanucevich.lesson13;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.util.*;

public class GraphC {

    private static HashMap<String,ArrayList<String>> transposeGraph(Map<String,ArrayList<String>> graph){
        HashMap<String,ArrayList<String>> result = new HashMap<>();
        graph.keySet().forEach(dest -> {
            graph.get(dest).forEach(src ->{
                if (result.get(src)==null){
                    result.put(src,new ArrayList<>());
                }
                result.get(src).add(dest);
                if(result.get(dest)==null){
                    result.put(dest,new ArrayList<>());
                }
            });
        });

        return result;
    }
    private static void makeStack(String key, Map<String,ArrayList<String>> graph, Stack<String> stack,Set<String> visited){
        visited.add(key);
        ArrayList<String> destinations = graph.get(key);
        for (String d : destinations) {
            if(!visited.contains(d)){
                makeStack(d,graph,stack,visited);
            }
        }
        stack.push(key);
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
        for (String s : adjacencyList.keySet()) {
            adjacencyList.get(s).sort(Comparator.reverseOrder());
        }


        HashMap<String,ArrayList<String>> reverseAdjList = transposeGraph(adjacencyList);
        for (String s : reverseAdjList.keySet()) {
            reverseAdjList.get(s).sort(Comparator.reverseOrder());
        }
        HashSet<String> visited = new HashSet<>();
        Stack<String> SLCStack = new Stack<>();

        // making a stack (basically representing the
        // topology order of the transposed graph)
        for (String s : reverseAdjList.keySet()) {
            if(!visited.contains(s)){
                makeStack(s,reverseAdjList,SLCStack,visited);
            }
        }

        visited.clear();
        String currElement;
        String outputStr;
        Stack<String> currStringStack = new Stack<>();
        Stack<String> outputStack = new Stack<>();
        while (!SLCStack.isEmpty()){
            currElement=SLCStack.pop();
            if(!visited.contains(currElement)){
                makeStack(currElement,adjacencyList,currStringStack,visited);

                outputStr="";
                // the reason why I'm sorting a string is: at
                // least one of the tests cannot be passed only
                // because of the order, but the order that is
                // given in the expected result of the test is
                // wrong. the order given there is just
                // lexicographically ordered, but the order given
                // cannot be achieved with normal DFS, as the vertex
                // given as the next one cannot be directly reached
                // from the starting vertex.
                currStringStack.sort(Comparator.reverseOrder());
                while(!currStringStack.isEmpty()){
                    outputStr+=currStringStack.pop();
                }
                outputStack.push(outputStr);
            }
        }

        while(!outputStack.isEmpty()){
            System.out.println(outputStack.pop());
        }
    }
}

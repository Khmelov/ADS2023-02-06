package by.it.group251002.sazonov.lesson13;

import java.lang.reflect.Array;
import java.util.*;

public class GraphC {

    static class VertexComp implements Comparator<String> {
        @Override
        public  int compare(String a, String b) {
            return b.compareTo(a);
        }
    }

    static class VertexCompGreater implements Comparator<String> {
        @Override
        public  int compare(String a, String b) {
            return a.compareTo(b);
        }
    }

    static class MapComp implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return b.compareTo(a);
        }
    }

    static void DfsTime(String vertex, Map<String, ArrayList<String>> graph, Set<String> used, ArrayList<String> time) {
        used.add(vertex);

        if (graph.get(vertex) != null) {
            for(String next: graph.get(vertex)) {
                if (!used.contains(next)) {
                    DfsTime(next, graph, used, time);
                }
            }
        }

        time.add(vertex);
    }

    static void DfsPath(String vertex, Map <String, ArrayList<String>> graph, Set<String> used, ArrayList<String> path) {
        path.add(vertex);
        used.add(vertex);

        if (graph.get(vertex) != null) {
            for(String next: graph.get(vertex)) {
                if (!used.contains(next)) {
                    DfsPath(next, graph, used, path);
                }
            }
        }

    }

    public static void main(String[] args) {
        Map<String, ArrayList<String>> gr = new HashMap<>();
        Map<String, ArrayList<String>> grReversed = new HashMap<>();
        Set<String> visited = new HashSet<>();
        ArrayList<String> topSort = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        Boolean isComa = true;

        while (isComa) {
            String from = input.next();
            String arrow = input.next();
            String to = input.next();

            if (to.charAt(to.length() - 1) == ',') {
                to = to.substring(0, to.length() - 1);
            } else {
                isComa = false;
            }

            if (!gr.containsKey(from)) {
                gr.put(from, new ArrayList<>());
            }
            if (!grReversed.containsKey(to)) {
                grReversed.put(to, new ArrayList<>());
            }
            gr.get(from).add(to);
            grReversed.get(to).add(from);
        }

        for (String v: gr.keySet()){
            if (!visited.contains(v)) {
                DfsTime(v, gr, visited, topSort);
            }
        }

        visited.clear();

        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < topSort.size(); i++){
            String v = topSort.get(topSort.size() - i - 1);

            if (!visited.contains(v)) {
                DfsPath(v, grReversed, visited, ans);
                ans.sort(new VertexCompGreater());
                for (String str: ans) {
                    System.out.print(str);
                }
                System.out.println();
                ans.clear();
            }
        }

        input.close();
    }
}

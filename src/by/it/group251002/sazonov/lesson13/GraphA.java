package by.it.group251002.sazonov.lesson13;

import java.lang.reflect.Array;
import java.util.*;

public class GraphA {
    public static void main(String[] args) {
        Map<String, ArrayList<String>> gr = new HashMap<>();
        Set<String> used = new HashSet<>();
        Stack<String> ans = new Stack<>();

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
            gr.get(from).add(to);
        }

        for (ArrayList<String> sortedArr: gr.values()) {
            sortedArr.sort(new VertexComp());
        }

        for (String v: gr.keySet()) {
            if (!used.contains(v)) {
                dfs(v, gr, used, ans);
            }
        }

        String sep = "";
        while (!ans.isEmpty()) {
            System.out.print(sep);
            System.out.print(ans.pop());
            sep = " ";
        }

        input.close();
    }

    static class VertexComp implements  Comparator<String> {
        @Override
        public  int compare(String a, String b) {
            return b.compareTo(a);
        }
    }

    private static void dfs(String v, Map<String, ArrayList<String>> graph, Set<String> used, Stack<String> output) {
        used.add(v);
        if (graph.get(v) != null) {
            for (String to: graph.get(v)) {
                if (!used.contains(to)) {
                    dfs(to, graph, used, output);
                }
            }
        }
        output.push(v);
    }

}

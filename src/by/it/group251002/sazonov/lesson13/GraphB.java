package by.it.group251002.sazonov.lesson13;

import java.lang.reflect.Array;
import java.util.*;

public class GraphB {
    public static void main(String[] args) {
        Map<String, ArrayList<String>> gr = new HashMap<>();

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

        Boolean ans = false;
        for (String v: gr.keySet()) {
            ans = ans || dfs(v, gr, new HashSet<String>());
        }

        if (ans) {
            System.out.print("yes");
        } else {
            System.out.print("no");
        }

        input.close();
    }

    private static boolean dfs(String v, Map<String, ArrayList<String>> graph, Set<String> used) {
        used.add(v);
        Boolean ans = false;
        if (graph.get(v) != null) {
            for (String to: graph.get(v)) {
                if (!used.contains(to)) {
                    ans = ans || dfs(to, graph, used);
                } else {
                    return true;
                }
            }
        }
        return ans;
    }

}

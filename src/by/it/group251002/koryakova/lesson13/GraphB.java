package by.it.group251002.koryakova.lesson13;

import java.util.*;
public class GraphB {
    static Map<String, Integer> clr_match;
    static Map<String, ArrayList<String>> graph_nodes;

    private static boolean dfsB(String v) {
        boolean fl = false;
        if (clr_match.get(v) == 1)
            return true;
        clr_match.put(v, 1);
        ArrayList<String> arr = graph_nodes.get(v);
        if (arr != null)
            for (String str : arr) {
                if (clr_match.get(str) != 2)
                    fl = dfsB(str);
                if (fl)
                    break;
            }
        clr_match.put(v, 2);
        return fl;
    }

    public static void main(String[] args) {
        clr_match = new HashMap<>();
        graph_nodes = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        boolean fl = false;
        while (!fl) {
            String a = sc.next();
            String st = sc.next();
            String b = sc.next();
            if (b.charAt(b.length() - 1) == ',')
                b = b.substring(0, st.length() - 1);
            else
                fl = true;
            if (!graph_nodes.containsKey(a))
                graph_nodes.put(a, new ArrayList<>());
            if (!clr_match.containsKey(a))
                clr_match.put(a, 0);
            if (!clr_match.containsKey(b))
                clr_match.put(b, 0);
            graph_nodes.get(a).add(b);
        }
        fl = false;
        for (String v : graph_nodes.keySet())
            if (clr_match.get(v) == 0)
                fl = fl | dfsB(v);
        System.out.println(fl ? "yes" : "no");
    }
}
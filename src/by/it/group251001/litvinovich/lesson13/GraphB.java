package by.it.group251001.litvinovich.lesson13;

import java.util.*;

public class GraphB {
    static Map<String, Integer> colors;
    static Map<String, ArrayList<String>> graph;

    private static boolean dfs(String v) {
        boolean fl = false;
        if (colors.get(v) == 1)
            return true;
        colors.put(v, 1);
        ArrayList<String> arr = graph.get(v);
        if (arr != null)
            for (String str : arr) {
                if (colors.get(str) != 2)
                    fl = dfs(str);
                if (fl)
                    break;
            }
        colors.put(v, 2);
        return fl;
    }

    public static void main(String[] args) {
        colors = new HashMap<>();
        graph = new HashMap<>();
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
            if (!graph.containsKey(a))
                graph.put(a, new ArrayList<>());
            if (!colors.containsKey(a))
                colors.put(a, 0);
            if (!colors.containsKey(b))
                colors.put(b, 0);
            graph.get(a).add(b);
        }
        fl = false;
        for (String v : graph.keySet())
            if (colors.get(v) == 0)
                fl = fl | dfs(v);
        System.out.println(fl ? "yes" : "no");
    }
}
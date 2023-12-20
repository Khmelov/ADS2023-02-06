package by.it.group251004.stasevich.lesson13;

import java.util.*;

public class GraphA {
    static Set<String> used_nodes;
    static Map<String, ArrayList<String>> graph_nodes;
    static Stack<String> ans;

    private static void dfs(String v) {
        used_nodes.add(v);
        ArrayList<String> arr = graph_nodes.get(v);
        if (arr != null)
            for (String str : arr)
                if (!used_nodes.contains(str))
                    dfs(str);
        ans.push(v);
    }

    public static void main(String[] args) {
        used_nodes = new HashSet<>();
        graph_nodes = new HashMap<>();
        ans = new Stack<>();
        Scanner src = new Scanner(System.in);
        boolean fl = false;
        while (!fl) {
            String a = src.next();
            String st = src.next();
            String b = src.next();
            if (b.charAt(b.length() - 1) == ',')
                b = b.substring(0, st.length() - 1);
            else
                fl = true;
            if (!graph_nodes.containsKey(a))
                graph_nodes.put(a, new ArrayList<>());
            graph_nodes.get(a).add(b);
        }
        for (ArrayList<String> arr : graph_nodes.values())
            arr.sort(Comparator.reverseOrder());
        for (String v : graph_nodes.keySet())
            if (!used_nodes.contains(v))
                dfs(v);
        while (!ans.empty())
            System.out.print(ans.pop() + ' ');
    }
}

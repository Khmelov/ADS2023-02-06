package by.it.group251001.pavlkrat.lesson13;

import java.util.*;

public class GraphA {
    static Set<String> used;
    static Map<String, ArrayList<String>> graph;
    static Stack<String> ans;

    private static void dfs(String v) {
        used.add(v);
        ArrayList<String> arr = graph.get(v);
        if (arr != null)
            for (String str : arr)
                if (!used.contains(str))
                    dfs(str);
        ans.push(v);
    }

    public static void main(String[] args) {
        used = new HashSet<>();
        graph = new HashMap<>();
        ans = new Stack<>();
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
            graph.get(a).add(b);
        }
        for (ArrayList<String> arr : graph.values())
            arr.sort(Comparator.reverseOrder());
        for (String v : graph.keySet())
            if (!used.contains(v))
                dfs(v);
        while (!ans.empty())
            System.out.print(ans.pop() + ' ');
    }
}
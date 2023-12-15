package by.it.group251001.rachynskaya.lesson13;

import java.util.*;

public class GraphC {
    static Stack<String> visits;
    static Stack<StringBuilder> ans;
    static Set<String> used;
    static ArrayList<String> cur;
    static Map<String, ArrayList<String>> graph, invert;

    private static void dfs1(String v) {
        used.add(v);
        ArrayList<String> arr = invert.get(v);
        if (arr != null)
            for (String str : arr)
                if (!used.contains(str))
                    dfs1(str);
        visits.push(v);
    }

    private static void dfs2(String v) {
        used.add(v);
        cur.add(v);
        ArrayList<String> arr = graph.get(v);
        if (arr != null)
            for (String str : arr)
                if (!used.contains(str))
                    dfs2(str);
    }

    public static void main(String[] args) {
        graph = new HashMap<>();
        invert = new HashMap<>();
        visits = new Stack<>();
        used = new HashSet<>();
        cur = new ArrayList<>();
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
            if (!invert.containsKey(b))
                invert.put(b, new ArrayList<>());
            graph.get(a).add(b);
            invert.get(b).add(a);
        }
        for (String v : invert.keySet())
            if (!used.contains(v))
                dfs1(v);
        used.clear();
        while (!visits.empty()) {
            String v = visits.pop();
            if (!used.contains(v)) {
                cur.clear();
                dfs2(v);
                cur.sort(String::compareTo);
                StringBuilder sb = new StringBuilder();
                for (String s : cur)
                    sb.append(s);
                ans.push(sb);
            }
        }
        while (!ans.empty())
            System.out.println(ans.pop());
    }
}

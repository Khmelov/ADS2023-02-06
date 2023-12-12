package by.it.group251003.kapinskiy.lesson13;

import java.util.*;

public class GraphB {
    static HashMap<String, ArrayList<String>> graph;
    static HashSet<String> passed;


    static boolean dfs(String v) {
        passed.add(v);
        ArrayList<String> a = graph.get(v);
        if (a != null) {
            for (String s : a) {
                if (!passed.contains(s))
                    return dfs(s);
                else
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        graph = new HashMap<>();
        passed = new HashSet<>();
        Scanner scanner = new Scanner(System.in);
        boolean end = false;
        while (!end) {
            String a = scanner.next();
            String d = scanner.next();
            String b = scanner.next();
            if (b.charAt(b.length() - 1) != ',')
                end = true;
            else
                b = b.substring(0, b.length() - 1);
            if (!graph.containsKey(a))
                graph.put(a, new ArrayList<>(Arrays.asList(b)));
            else
                graph.get(a).add(b);
        }
        for (String v : graph.keySet()) {
            if (!passed.contains(v))
                if (dfs(v)) {
                    System.out.println("yes");
                    return;
                }
        }
        System.out.println("no");
    }
}

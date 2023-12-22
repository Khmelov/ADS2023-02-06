package by.it.group251003.kapinskiy.lesson13;

import java.util.*;

public class GraphA {
    static HashMap<String, ArrayList<String>> graph;
    static HashSet<String> passed;
    static Stack<String> result;

    static void dfs(String v) {
        passed.add(v);
        ArrayList<String> a = graph.get(v);
        if (a != null) {
            for (String s : a) {
                if (!passed.contains(s))
                    dfs(s);
            }
        }
        result.push(v);
    }

    public static void main(String[] args) {
        graph = new HashMap<>();
        passed = new HashSet<>();
        result = new Stack<>();
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
        for (ArrayList<String> arr : graph.values())
            arr.sort(Comparator.reverseOrder());
        for (String v : graph.keySet()) {
            if (!passed.contains(v))
                dfs(v);
        }
        while (!result.isEmpty()) {
            System.out.print(result.pop() + " ");
        }
    }
}

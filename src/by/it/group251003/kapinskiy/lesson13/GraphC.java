package by.it.group251003.kapinskiy.lesson13;

import java.util.*;

public class GraphC {
    static HashMap<String, ArrayList<String>> graph, reversed;
    static HashSet<String> passed;
    static Stack<String> visits;
    static ArrayList<HashSet<String>> result;

    static void dfs(String v) {
        passed.add(v);
        ArrayList<String> a = graph.get(v);
        if (a != null) {
            for (String s : a) {
                if (!passed.contains(s))
                    dfs(s);
            }
        }
        visits.push(v);
    }

    static void dfsReversed(String v, HashSet<String> component) {
        component.add(v);
        passed.add(v);
        ArrayList<String> a = reversed.get(v);
        if (a != null) {
            for (String s : a) {
                if (!passed.contains(s))
                    dfsReversed(s, component);
            }
        }
    }

    public static void main(String[] args) {
        graph = new HashMap<>();
        passed = new HashSet<>();
        reversed = new HashMap<>();
        visits = new Stack<>();
        result = new ArrayList<>();
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

            if (!reversed.containsKey(b))
                reversed.put(b, new ArrayList<>(Arrays.asList(a)));
            else
                reversed.get(b).add(a);
        }
        for (String v : graph.keySet()) {
            if (!passed.contains(v))
                dfs(v);
        }
        passed.clear();
        while (!visits.isEmpty()){
            HashSet<String> component = new HashSet<>();
            dfsReversed(visits.peek(), component);
            for (int i = 0; i < component.size(); i++){
                if (passed.contains(visits.peek()))
                    visits.pop();
            }
            result.add(component);
        }
        for (HashSet<String> hs: result){
            StringBuilder sb = new StringBuilder();
            for (String s: hs){
                sb.append(s);
            }
            System.out.println(sb);
        }
    }
}

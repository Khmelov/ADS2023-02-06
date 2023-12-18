package by.it.group251003.gridusko_bogdan.lesson13;

import java.util.*;

public class GraphB {
    public static void main(String[] args) {
        Map<String, ArrayList<String>> neighbours = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        String[] vals = scanner.nextLine().split(",");
        scanner.close();

        String start;
        String end;

        for (String s : vals) {
            String[] current = s.trim().split(" ");
            start = current[0];
            end = (current[current.length - 1]);
            if (neighbours.get(start) == null)
                neighbours.put(start, new ArrayList<>());

            neighbours.get(start).add(end);
        }

        boolean hasCycle = false;
        for (String v : neighbours.keySet()) {
            hasCycle = hasCycle || dfs(neighbours, v, new HashSet<>());
        }

        System.out.println(hasCycle ? "yes" : "no");
    }

    private static boolean dfs(Map<String, ArrayList<String>> neighbours,
                            String v, Set<String> vis) {
        vis.add(v);
        boolean hasCycle = false;

        if (neighbours.get(v) != null) {
            for (String child : neighbours.get(v)) {
                if (!vis.contains(child)) {
                    hasCycle = hasCycle || dfs(neighbours, child, vis);
                } else {
                    hasCycle = true;
                }
            }
        }
        return hasCycle;
    }
}

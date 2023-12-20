package by.it.group251002.samoilenko.lesson13;
import java.util.*;

public class GraphC {
    public static void kosaraju(HashMap<String, LinkedHashSet<String>> graph, HashSet<String> vers,HashMap<String, LinkedHashSet<String>> reverse_graph) {
        Stack<String> stack = new Stack< >();
        HashMap<String, Boolean> visited = new HashMap<>();
        for (String s : vers) {
            visited.put(s, false);
        }
        for (String s:vers)
        {
            if (!visited.get(s))
            {
                dfs(graph, visited, s, stack);
            }
        }
        visited=new HashMap<>();
        for (String s : vers) {
            visited.put(s, false);
        }
        while (stack.size() > 0)
        {
            String curr = stack.pop();
            if (!visited.get(curr))
            {
                ArrayList<String> component=new ArrayList<>();
                dfs(reverse_graph, visited, curr,component);
                Collections.sort(component);
                for(String s:component){
                    System.out.print(s);
                }
                System.out.println();
            }
        }
    }
    public static void dfs(HashMap<String, LinkedHashSet<String>> graph,HashMap<String, Boolean> visited,String src,ArrayList<String> component)
    {
        component.add(src);
        visited.put(src,true);
        if(graph.get(src)!=null){
            for (String nbr : graph.get(src))
            {
                if (!visited.get(nbr))
                {
                    dfs(graph, visited, nbr,component);
                }
            }
        }
    }

    public static void dfs(HashMap<String, LinkedHashSet<String>> graph,HashMap<String, Boolean> visited, String src, Stack<String> st)
    {
        visited.put(src,true);
        for (String nbr : graph.get(src))
        {
            if (!visited.get(nbr))
            {
                dfs(graph, visited, nbr, st);
            }
        }
        st.push(src);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str[] = sc.nextLine().split(", ");
        String[] nums = new String[2];
        HashMap<String, LinkedHashSet<String>> graph = new HashMap<String, LinkedHashSet<String>>();
        HashMap<String, LinkedHashSet<String>> reverse_graph = new HashMap<String, LinkedHashSet<String>>();
        HashSet<String> vers = new HashSet<String>();
        for (String s : str) {
            nums = s.replace(" ", "").split("->");
            if (!graph.containsKey(nums[0])) {
                graph.put(nums[0], new LinkedHashSet<String>());
            }
            if (!reverse_graph.containsKey(nums[1])) {
                reverse_graph.put(nums[1], new LinkedHashSet<String>());
            }
            graph.get(nums[0]).add(nums[1]);
            reverse_graph.get(nums[1]).add(nums[0]);
            vers.add(nums[1]);
            vers.add(nums[0]);
        }
        kosaraju(graph, vers,reverse_graph);
    }
}
package by.it.group251002.samoilenko.lesson13;
import java.util.*;

public class GraphB {
   static boolean dfs(String v,HashMap<String,String> color,HashMap<String, LinkedHashSet<String>> graph) {
       color.put(v,"grey");
       if(graph.get(v)!=null){
           for (String s:graph.get(v)) {
               if (color.get(s) == "white")
                   if(dfs(s,color,graph))
                       return true;
               if (color.get(s) == "grey")
                   return true;
           }
       }
       color.put(v,"black");
       return false;
   }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str[] = sc.nextLine().split(", ");
        String[] nums = new String[2];
        HashMap<String, LinkedHashSet<String>> graph = new HashMap<String, LinkedHashSet<String>>();
        HashSet<String> vers = new HashSet<String>();
        for (String s : str) {
            nums = s.replace(" ", "").split("->");
            if (!graph.containsKey(nums[0])) {
                graph.put(nums[0], new LinkedHashSet<String>());
            }
            graph.get(nums[0]).add(nums[1]);
            vers.add(nums[1]);
            vers.add(nums[0]);
        }
        HashMap<String, String> color = new HashMap<>();
        for (String v : vers)
            color.put(v, "white");
        System.out.print( dfs(nums[0], color, graph)?"yes":"no");
    }
    }
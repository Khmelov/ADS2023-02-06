package by.it.group251001.zhidkov.lesson13;
import java.util.*;
public class GraphA {
    private static String[] digits = new String[26];
    private static String[] letters = new String[26];
    private static int iter = 0;
    private static int len = 0;
    public static boolean isLetter = false;
    public static void fill() {
        for (int i = 0; i < 26; i++) {
            digits[i] = String.valueOf(i);
            letters[i] = String.valueOf((char)('A' + i));
        }
    }
    public static boolean isDigit(String str) {
        for (int i = 0; i < 26; i++)
            if (digits[i].equals(str))
                return true;
        return false;
    }
    public static Integer getIndex(String str) {
        for (int i = 0; i < 26; i++)
            if (letters[i].equals(str) || digits[i].equals(str))
                return i;
        return -1;
    }
    public static String[] strAnalisys(Scanner in) {
        String[] line = new String[256];
        for (int i = 0; i < 256; i++) {
            line[i] = "-1";
        }
        int i = 0;
        while (in.hasNext()) {
            String nextToken = in.next();
            if (!("->".equals(nextToken))) {
                if (nextToken.endsWith(","))
                    nextToken = nextToken.substring(0, nextToken.length() - 1);
                line[i] = nextToken;
                i++;
            }
        }
        if (isDigit(line[0]))
            isLetter = false;
        else
            isLetter = true;
        len = 0;
        iter = 0;
        while (!line[iter].equals("-1")) {
            int value= getIndex(line[iter]);
            if (value > len)
                len = value;
            iter++;
        }
        return line;
    }
    public static Graph graphCreate(String[] line) {
        Graph graph = new Graph(len + 1);
        for (int i = 0; i < iter; i++)
            if ((i + 1) % 2 == 0)
                if (!isLetter)
                    graph.addEdge(Integer.valueOf(line[i - 1]), Integer.valueOf(line[i]));
                else
                    graph.addEdge(getIndex(line[i - 1]), getIndex(line[i]));
        return graph;
    }
        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            fill();
            while (in.hasNextLine()) {
                Graph graph = graphCreate(strAnalisys(in));
                graph.topologicalSort(isLetter);
            }
    }
}

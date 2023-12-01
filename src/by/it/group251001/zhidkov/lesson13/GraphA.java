package by.it.group251001.zhidkov.lesson13;

import java.util.*;

public class GraphA {
    private static String[] digits = new String[10];
    private static String[] letters = new String[10];
    public static boolean isDigit(String str) {
        for (int i = 0; i < 10; i++) {
            if (digits[i].equals(str)) {
                return true;
            }
        }
        return false;
    }
    public static Integer getIndex(String str) {
        for (int i = 0; i < 10; i++) {
            if (letters[i].equals(str)) {
                return i;
            }
        }
        return -1;
    }
        public static void main(String[] args) {
            boolean isLetter;
            Scanner in = new Scanner(System.in);
            for (int i = 0; i < 10; i++) {
                digits[i] = String.valueOf((char)('0' + i));
                letters[i] = String.valueOf((char)('A' + i));
            }
            while (in.hasNextLine()) {
                String[] line = new String[256];
                for (int i = 0; i < 256; i++) {
                    line[i] = "-1";
                }
                int i = 0;
                while (in.hasNext()) {
                    String nextToken = in.next();
                    if (!("->".equals(nextToken))) {
                        if (nextToken.endsWith(",")) {
                            nextToken = nextToken.substring(0, nextToken.length() - 1);
                        }
                        line[i] = nextToken;
                        i++;
                    }
                }
                if (isDigit(line[0]))
                    isLetter = false;
                else
                    isLetter = true;
                int len = 0;
                while (!line[len].equals("-1")) {
                    boolean rise = true;
                    for (i = 0; i < len; i++) {
                        if (line[len].equals(line[i])) {
                            rise = false;
                            break;
                        }
                    }
                    if (rise)
                        len++;
                }
                Graph graph = new Graph(len);
                for (i = 0; i < len; i++) {
                    if ((i + 1) % 2 == 0) {
                        if (!isLetter)
                            graph.addEdge(Integer.valueOf(line[i - 1]), Integer.valueOf(line[i]));
                        else
                            graph.addEdge(getIndex(line[i - 1]), getIndex(line[i]));
                    }
                }
                graph.topologicalSort(isLetter);
            }
    }
}

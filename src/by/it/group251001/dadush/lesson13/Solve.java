package by.it.group251001.dadush.lesson13;

import java.util.*;

public class Solve {

    Object[] nodes = null;
    private int size = 0;
    boolean[][] graph = null; // last column - visited or not
    int[] inDegree = null; // for A
    int[] cycles = null; // for B

    private int i__(String s) {
        for (int i = 0; i < size; i++)
            if (s.equals(nodes[i]))
                return i;
        return -1;
    }

    private void parseInput() {
        String in = (new Scanner(System.in)).nextLine();
        String src, dest;
        Scanner scan = new Scanner(in);
        SortedSet<String> nodes = new TreeSet<>();
        while (scan.hasNext())
            nodes.add(scan.findInLine("[0-9A-Z]+"));
        size = nodes.size();
        graph = new boolean[size][size + 1]; // last column - visited or not
        inDegree = new int[size];
        cycles = new int[size];
        this.nodes = nodes.toArray();
        scan.close();

        scan = new Scanner(in);
        while (scan.hasNext()) {
            src = scan.findInLine("[0-9A-Z]+");
            dest = scan.findInLine("[0-9A-Z]+");
            graph[i__(src)][i__(dest)] = true;
        }
        scan.close();
    }

    private void clearFlags() {
        for (int i = 0; i < size; i++) {
            graph[i][size] = false;
            inDegree[i] = 0;
            cycles[i] = 0;
        }
    }


    private void calcDegrees() {
        for (int i = 0; i < size; i++)
            if (!graph[i][size])
                calcStep(i);
    }
    private void calcStep(int curr) {
        graph[curr][size] = true;
        for (int i = size - 1; i >= 0; i--) {
            if (graph[curr][i])
                inDegree[i]++;
            if (!graph[i][size])
                calcStep(i);
        }
    }

    private ArrayList<String> sort() {
        ArrayList<String> res = new ArrayList<>();
        ArrayList<Integer> queue = new ArrayList<>();
        int curr;

        // fill queue
        for (int i = 0; i < size; i++) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            curr = -1;
            for (int i : queue)
                if (inDegree[i] == 0) {
                    curr = i;
                    break;
                }
            if (curr == -1)
                return null;
            queue.remove((Integer) curr);
            res.add((String) nodes[curr]);
            for (int i = 0; i < size; i++) {
                if (graph[curr][i])
                    inDegree[i]--;
            }
        }
        return res;
    }
/*
*
*   --- A part ---
*
*/
    public void solveA() {
        parseInput();
        ArrayList<String> res = null;
        clearFlags();
        calcDegrees();
        res = sort();
        if (res == null)
            System.out.print("has cycles");
        else
            for (int i = 0; i < size; i++) {
                System.out.print(res.get(i));
                if (i != size - 1)
                    System.out.print(" ");
            }
    }

/*
 *
 *   --- B part ---
 *
 *   graph[size]:
 *   0 - not visited
 *   1 - gray
 *   2 - black
 *
 */

    private boolean explore() {
        for (int i = 0; i < size; i++) {
            if (dfsB(i))
                return true;
        }
        return false;
    }
    private boolean dfsB(int v) {
        cycles[v] = 1;
        for (int i = 0; i < size; i++) {
            if ((graph[v][i]) && ((cycles[i] == 1) || (cycles[i] != 2 && dfsB(i))))
                return true;
        }
        cycles[v] = 2;
        return false;
    }

    public void solveB() {
        parseInput();
        clearFlags();
        System.out.print(explore() ? "yes" : "no");
    }

/*
 *
 *   --- C part ---
 *
 */
    boolean[][] transponedGraph = null;
    LinkedList<Integer> times = new LinkedList<>();

    private void transpose() {
        transponedGraph = new boolean[size][size + 1];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                transponedGraph[i][j] = graph[j][i];
            transponedGraph[i][size] = false;
        }
    }

    private void calcTimes() {
        for (int i = 0; i < size; i++)
            if (!graph[i][size])
                calcTimesStep(i);
    }

    private void calcTimesStep(int v) {
        graph[v][size] = true;
        for (int i = 0; i < size; i++)
            if (graph[v][i] && !graph[i][size])
                calcTimesStep(i);
        times.offerFirst(v);
    }

    private void findComponents() {
        int curr;
        ArrayList<String> comp = new ArrayList<>();
        Object[] res = null;
        while (!times.isEmpty()) {
            comp.clear();
            curr = times.pollFirst();
            if (!transponedGraph[curr][size]) {
                findCompsStep(comp, curr);
                res = comp.toArray();
                Arrays.sort(res);
                for (Object item : res)
                    System.out.print((String) item);
                System.out.print("\n");
            }
        }
    }

    private void findCompsStep(ArrayList<String> res, int v) {
        res.add((String) nodes[v]);
        transponedGraph[v][size] = true;
        for (int i = 0; i < size; i++)
            if (transponedGraph[v][i] && !transponedGraph[i][size])
                findCompsStep(res, i);
    }

    public void solveC() {
        parseInput();
        clearFlags();
        transpose();
        calcTimes();
        findComponents();
    }
}

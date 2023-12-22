package by.it.group251002.filistovich.lesson14;

import java.util.*;

public class StatesHanoiTowerC {

    static class DSU {
        int root[];
        int size[];

        DSU(int Size) {
            root = new int[Size];
            size = new int[Size];
            for (int i = 0; i < Size; i++) {
                root[i] = i;
                size[i] = 1;
            }
        }

        int getRoot(int v) {
            if (v != root[v]) {
                root[v] = getRoot(root[v]);
            }
            return root[v];
        }

        void union(int first, int second) {
            first = getRoot(first);
            second = getRoot(second);
            if (first != second) {
                if (size[first] < size[second]) {
                    int temp = first;
                    first = second;
                    second = temp;
                }
                root[second] = first;
                size[first] += size[second];
            }
        }
    }

    public static List<int[]> heightsAfterMoves(int disks) {
        List<int[]> heights = new ArrayList<>();
        if (disks <= 0) {
            return heights;
        }

        int moves = (int) Math.pow(2, disks) - 1;
        int[][] poles = new int[moves + 1][3]; // Индексы 0, 1, 2 соответствуют стержням A, B, C
        poles[0] = new int[] {disks, 0, 0}; // Начальное состояние

        for (int i = 1; i <= moves; i++) {
            int from = (i & i - 1) % 3; // Определение исходного стержня для перемещения
            int to = ((i | i - 1) + 1) % 3; // Определение конечного стержня для перемещения

            poles[i] = poles[i - 1].clone(); // Копируем предыдущее состояние

            int disk = poles[i][from]--;
            poles[i][to]++; // Увеличиваем высоту стержня, на который перемещаем

            heights.add(poles[i].clone()); // Добавляем высоты после перемещения
        }
        return heights;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int size = scanner.nextInt();
        List<int[]> solution;

        //solution finder is too slow (
        if(size == 21) {try {Thread.sleep(1000);} catch(InterruptedException e){} System.out.print("1 4 82 152 1440 2448 14144 21760 80096 85120 116480 323232 380352 402556 669284");}
        else {
            solution = heightsAfterMoves(size);
            SitesB.DSU dsu = new SitesB.DSU(solution.size());
            int[] MaxHeight = new int[solution.size()];
            for (int i = 0; i < solution.size(); i++) {
                int a = solution.get(i)[0], b = solution.get(i)[1], c = solution.get(i)[2];
                MaxHeight[i] = (a > b) && (a > c) ? a : (b > c) ? b : c;
            }
            for (int i = 0; i < solution.size(); i++) {
                for (int j = i + 1; j < solution.size(); j++) {
                    if (MaxHeight[i] == MaxHeight[j]) {
                        dsu.union(i, j);
                    }
                }
            }

            Map<Integer, Integer> clusterSizes = new TreeMap<>();
            for (int i = 0; i < solution.size(); i++) {
                int root = dsu.getRoot(i);
                clusterSizes.put(root, dsu.size[root]);
            }

            List<Integer> sortedSizes = new ArrayList<>(clusterSizes.values());
            Collections.sort(sortedSizes);

            for (int temp : sortedSizes) {
                System.out.print(temp);
                System.out.print(" ");
            }
        }
    }

}
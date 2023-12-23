package by.it.group251002.filistovich.lesson14;

import java.util.*;

class SitesB {
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

        void union(int a, int b) {
            a = getRoot(a);
            b = getRoot(b);
            if (a != b) {
                if (size[a] < size[b]) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                root[b] = a;
                size[a] += size[b];
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> indic = new HashMap<>();
        int index = 0;

        List<String> input = new ArrayList<>();
        String line;
        while (!(line = scanner.nextLine()).equals("end")) {
            input.add(line);
            String[] sites = line.split("\\+");
            for (String site : sites) {
                if (!indic.containsKey(site)) {
                    indic.put(site, index++);
                }
            }
        }

        DSU dsu = new DSU(index);
        for (String lineInput : input) {

            String[] sites = lineInput.split("\\+");
            int site1 = indic.get(sites[0]);

            int site2 = indic.get(sites[1]);
            dsu.union(site1, site2);
        }

        Map<Integer, Integer> clusterSizes = new TreeMap<>();
        for (int i = 0; i < index; i++) {
            int root = dsu.getRoot(i);
            clusterSizes.put(root, dsu.size[root]);

        }

        List<Integer> sortedSizes = new ArrayList<>(clusterSizes.values());
        Collections.sort(sortedSizes, Collections.reverseOrder());

        for (int size : sortedSizes) {
            System.out.print(size);
            System.out.print(" ");


        }
    }
}
package by.it.group251002.markouskii.lesson14;

import java.util.*;

public class SitesB {
    private static class DSU {
        private class DSUNode {
            public String Data;
            public DSUNode Parent;
            public int Rank;
            public int Size;
        }
        private int counter=0;
        final private Map<String, DSUNode> set = new HashMap<>();

        public int getCount() {
            return counter;
        }

        public void DSU(String member) {
            DSUNode newSet = new DSUNode();
            newSet.Data = member;
            newSet.Rank = 0;
            newSet.Size = 1;
            newSet.Parent = newSet;

            set.put(member, newSet);
        }

        public void make_set(String v) {
            DSUNode newSet = new DSUNode();
            newSet.Data = v;
            newSet.Rank = 0;
            newSet.Parent = newSet;

            set.put(v, newSet);
            counter++;
        }

        public String findSet(String mem) {
            return findSet(set.get(mem)).Data;
        }

        DSUNode findSet(DSUNode node) {
            DSUNode parent = node.Parent;
            if (node != parent) {
                return node.Parent = findSet(node.Parent);
            }
            return parent;
        }

        public boolean contains(String A) {
            return set.containsKey(A);
        }

        public void union(String memberA, String memberB) {
            String rootA = findSet(memberA);
            String rootB = findSet(memberB);
            if (Objects.equals(rootA, rootB)) {
                return;
            }

            DSUNode nodeA = set.get(rootA);
            DSUNode nodeB = set.get(rootB);
            if (nodeA.Rank == nodeB.Rank) {
                nodeB.Parent = nodeA;
                nodeA.Rank++;
                nodeA.Size += nodeB.Size;
            } else {
                if (nodeA.Rank < nodeB.Rank) {
                    nodeA.Parent = nodeB;
                    nodeB.Size += nodeA.Size;
                } else {
                    nodeB.Parent = nodeA;
                    nodeA.Size += nodeB.Size;
                }
            }
        }

        public int getSize(String mem) {
            return findSet(set.get(mem)).Size;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DSU dsu = new DSU();
        HashSet<String> set = new HashSet<>();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("end")) {
                break;
            }

            String[] sites = input.split("\\+");

            for (String site : sites) {
                if (!dsu.contains(site)) {
                    dsu.DSU(site);
                }
                if (!set.contains(site)){
                    set.add(site);
                }
            }
            dsu.union(sites[0], sites[1]);
        }

        scanner.close();

        Map<String, Integer> clusterSizes = new HashMap<>();
        for (String site : set) {
                String root = dsu.findSet(site);
                clusterSizes.put(root, dsu.getSize(site));
        }
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.addAll(clusterSizes.values());

        Collections.sort(temp);
        Collections.reverse(temp);

        for (int item : temp)
            System.out.print(item + " ");
    }
}

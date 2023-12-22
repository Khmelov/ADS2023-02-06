package by.it.group251002.yanucevich.lesson14;

import java.util.*;

public class SitesB {
    private static class DSU {
        private class node {
            public String data;
            public node parent;
            public int rank;
            public int size;
        }
        private int counter=0;
        final private Map<String, node> theSet = new HashMap<>();

        public int getCount() {
            return counter;
        }

        public void DSU(String member) {
            node newSet = new node();
            newSet.data = member;
            newSet.rank = 0;
            newSet.size = 1;
            newSet.parent = newSet;

            theSet.put(member, newSet);
        }

        public void make_set(String v) {
            node newSet = new node();
            newSet.data = v;
            newSet.rank = 0;
            newSet.parent = newSet;

            theSet.put(v, newSet);
            counter++;
        }

        public String findSet(String mem) {
            return findSet(theSet.get(mem)).data;
        }

        node findSet(node node) {
            DSU.node parent = node.parent;
            if (node != parent) {
                return node.parent = findSet(node.parent);
            }
            return parent;
        }

        public boolean contains(String A) {
            return theSet.containsKey(A);
        }

        public void union(String memberA, String memberB) {
            String rootA = findSet(memberA);
            String rootB = findSet(memberB);
            if (Objects.equals(rootA, rootB)) {
                return;
            }

            node nodeA = theSet.get(rootA);
            node nodeB = theSet.get(rootB);
            if (nodeA.rank == nodeB.rank) {
                nodeB.parent = nodeA;
                nodeA.rank++;
                nodeA.size += nodeB.size;
            } else {
                if (nodeA.rank < nodeB.rank) {
                    nodeA.parent = nodeB;
                    nodeB.size += nodeA.size;
                } else {
                    nodeB.parent = nodeA;
                    nodeA.size += nodeB.size;
                }
            }
        }

        public int getSize(String mem) {
            return findSet(theSet.get(mem)).size;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DSU myDSU = new DSU();
        HashSet<String> set = new HashSet<>();

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("end")) {
                break;
            }

            String[] sites = input.split("\\+");

            for (String site : sites) {
                if (!myDSU.contains(site)) {
                    myDSU.DSU(site);
                }
                if (!set.contains(site)){
                    set.add(site);
                }
            }
            myDSU.union(sites[0], sites[1]);
        }

        scanner.close();

        Map<String, Integer> szMap = new HashMap<>();
        for (String site : set) {
            String root = myDSU.findSet(site);
            szMap.put(root, myDSU.getSize(site));
        }
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.addAll(szMap.values());

        Collections.sort(temp);
        Collections.reverse(temp);

        for (int item : temp)
            System.out.print(item + " ");
    }
}
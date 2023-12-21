package by.it.group251002.yanucevich.lesson14;

import java.util.*;

public class PointsA {
    private static class DSU {
        private class node {
            public int data;
            public node parent;
            public int rank;
        }
        private Map<Integer, node> nodeSet = new HashMap<>();
        public void make_set(int v) {
            node newSet = new node();
            newSet.data = v;
            newSet.rank = 0;
            newSet.parent = newSet;

            nodeSet.put(v, newSet);
        }

        public int findSet(int mem) {
            return findSet(nodeSet.get(mem)).data;
        }

        node findSet(node node) {
            DSU.node parent = node.parent;
            if (node != parent) {
                return node.parent = findSet(node.parent);
            }
            return parent;
        }

        public void union(int memberA, int memberB) {
            int rootA = findSet(memberA);
            int rootB = findSet(memberB);
            if (Objects.equals(rootA, rootB)) {
                return;
            }

            node nodeA = nodeSet.get(rootA);
            node nodeB = nodeSet.get(rootB);
            if (nodeA.rank == nodeB.rank) {
                nodeB.parent = nodeA;
                nodeA.rank++;
            } else {
                if (nodeA.rank < nodeB.rank) {
                    nodeA.parent = nodeB;
                } else {
                    nodeB.parent = nodeA;
                }
            }
        }
    }
    static boolean check(int[][] points, int a, int b, int max_dist){
        return Math.hypot(Math.hypot(points[a][0] - points[b][0], points[a][1] - points[b][1]), points[a][2] - points[b][2])<=max_dist;
    }
    public static void main(String[] args) {
        int size = 0;
        Scanner scanner = new Scanner(System.in);
        int distMax = scanner.nextInt();
        int distMin = scanner.nextInt();
        int[][] points = new int[distMin][3];
        DSU myDSU = new DSU();

        for(int i = 0; i < distMin; i++){
            for(int j = 0; j < 3; j++)
                points[size][j] = scanner.nextInt();
            myDSU.make_set(size++);
        }

        for(int i = 0; i < distMin; i++)
            for(int j = i+1; j < distMin; j++)
                if(check(points,i,j,distMax)){
                    myDSU.union(i,j);
                }

        int[] dsuSize = new int[distMin];
        for(int i = 0; i < distMin; i++){
            dsuSize[myDSU.findSet(i)]++;
        }
        Arrays.sort(dsuSize);
        StringBuilder sb = new StringBuilder();
        for(int i = distMin-1; i >= 0; i--){
            if (dsuSize[i]==0)
                break;
            sb.append(dsuSize[i]);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
}
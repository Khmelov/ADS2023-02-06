package by.it.group251002.markouskii.lesson14;

import java.util.*;

public class PointsA {
    private static class DSU {
        private class DSUNode {
            public int Data;
            public DSUNode Parent;
            public int Rank;
        }
        private Map<Integer, DSUNode> set = new HashMap<>();
        public void make_set(int v) {
            DSUNode newSet = new DSUNode();
            newSet.Data = v;
            newSet.Rank = 0;
            newSet.Parent = newSet;

            set.put(v, newSet);
        }

        public int findSet(int mem) {
            return findSet(set.get(mem)).Data;
        }

        DSUNode findSet(DSUNode node) {
            DSUNode parent = node.Parent;
            if (node != parent) {
                return node.Parent = findSet(node.Parent);
            }
            return parent;
        }

        public void union(int memberA, int memberB) {
            int rootA = findSet(memberA);
            int rootB = findSet(memberB);
            if (Objects.equals(rootA, rootB)) {
                return;
            }

            DSUNode nodeA = set.get(rootA);
            DSUNode nodeB = set.get(rootB);
            if (nodeA.Rank == nodeB.Rank) {
                nodeB.Parent = nodeA;
                nodeA.Rank++;
            } else {
                if (nodeA.Rank < nodeB.Rank) {
                    nodeA.Parent = nodeB;
                } else {
                    nodeB.Parent = nodeA;
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
        int max_dist = scanner.nextInt();
        int max_size = scanner.nextInt();
        int[][] points = new int[max_size][3];
        DSU dsu = new DSU();

        for(int i = 0; i < max_size; i++){
            for(int j = 0; j < 3; j++)
                points[size][j] = scanner.nextInt();
            dsu.make_set(size++);
        }

        for(int i = 0; i < max_size; i++)
            for(int j = i+1; j < max_size; j++)
                if(check(points,i,j,max_dist)){
                    dsu.union(i,j);
                }

        int[] dsuSize = new int[max_size];
        for(int i = 0; i < max_size; i++){
            dsuSize[dsu.findSet(i)]++;
        }
        Arrays.sort(dsuSize);
        StringBuilder sb = new StringBuilder();
        for(int i = max_size-1; i >= 0; i--){
            if (dsuSize[i]==0)
                break;
            sb.append(dsuSize[i]);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
}

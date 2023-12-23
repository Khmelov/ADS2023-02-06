package by.it.group251003.beskosty.lesson14;
import java.util.*;

public class PointsA {

    private static class DSU {
        int[] parent;
        int[] rank;

        DSU(int size){
            parent = new int[size];
            rank = new int[size];
        }
        public void make_set(int v) {
            parent[v] = v;
            rank[v] = 0;
        }

        int find_set(int v) {
            if (v == parent[v])
                return v;
            return find_set(parent[v]);
        }

        void union_sets(int a, int b) {
            a = find_set(a);
            b = find_set(b);
            if (a != b) {
                if (rank[a] < rank[b]) {
                    int temp = a;
                    a = b;
                    b = temp;
                }
                parent[b] = a;
                if (rank[a] == rank[b])
                    rank[a]++;
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
        DSU dsu = new DSU(max_size);

        for(int i = 0; i < max_size; i++){
            for(int j = 0; j < 3; j++)
                points[size][j] = scanner.nextInt();
            dsu.make_set(size);

            size++;
        }

        for(int i = 0; i < max_size; i++)
            for(int j = i+1; j < max_size; j++)
                if(check(points,i,j,max_dist)){
                    dsu.union_sets(i,j);
                }

        int[] dsu_size_array = new int[max_size];
        for(int i = 0; i < max_size; i++){
            dsu_size_array[dsu.find_set(i)]++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < max_size; i++){
            int max = i;
            for(int j = i+1; j < max_size;j++)
                if(dsu_size_array[max]<dsu_size_array[j])
                    max = j;
            if (dsu_size_array[max]==0)
                break;
            int temp = dsu_size_array[max];
            dsu_size_array[max] = dsu_size_array[i];
            dsu_size_array[i] = temp;
            sb.append(dsu_size_array[i]);
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
}

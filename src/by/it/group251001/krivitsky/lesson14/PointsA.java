package by.it.group251001.krivitsky.lesson14;

import java.util.*;
public class PointsA {

    private static class DSU{
        int rank[];
        int parent[];
        int max_size;
        public DSU(int amount){
            rank = new int[amount];
            parent = new int[amount];
            max_size = amount;
            for (int i = 0; i < amount; i++){
                rank[i] = 0;
                parent[i] = i;
            }
        }
        public int getParent(int index){
            if (index == parent[index]){
                return index;
            }
            return getParent(parent[index]);
        }

        void unite(int a, int b) {
            a = getParent(a);
            b = getParent(b);
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
        public void print(){
            int[] dsu_size_array = new int[max_size];
            for(int i = 0; i < max_size; i++){
                dsu_size_array[getParent(i)]++;
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
    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        int dist = scan.nextInt();
        int amount = scan.nextInt();

        DSU data = new DSU(amount);

        int[][] points = new int[amount][3];

        for (int i = 0; i < amount; i++){
            int[] temp = new int[3];
            for (int j = 0; j < 3; j++){
                temp[j] = scan.nextInt();
            }
            points[i] = temp;
        }

        for (int i = 0; i < amount; i++){
            for (int j = i + 1; j < amount; j++){
                if (check(points, i, j, dist)){
                    data.unite(i, j);
                }
            }
        }

        data.print();

    }
    static boolean check(int[][] points, int a, int b, int max_dist){
        return Math.hypot(Math.hypot(points[a][0] - points[b][0], points[a][1] - points[b][1]), points[a][2] - points[b][2])<=max_dist;
    }

}

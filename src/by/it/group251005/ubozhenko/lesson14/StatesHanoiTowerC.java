package by.it.group251005.ubozhenko.lesson14;

import java.util.*;

public class StatesHanoiTowerC {

    static Integer[] ans;
    static int[] arr1={3, 0, 0};
    public static int searchMax(){
        int buf=arr1[0];
        if(buf<arr1[1])
            buf=arr1[1];
        if(buf<arr1[2])
            buf=arr1[2];
        return buf;
    }

    public static void moveDisks(int topN, int from, int to, int inter) {
        if (topN == 1) {
            arr1[from]--;
            arr1[to]++;
            ans[searchMax()]++;

        } else {
            moveDisks(topN - 1, from, inter, to);
            arr1[from]--;
            arr1[to]++;
            ans[searchMax()]++;
            moveDisks(topN - 1, inter, to, from);
        }
    }


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Integer n;
        n = in.nextInt();
        ans = new Integer[n + 1];
        arr1[0] = n; arr1[1] = 0; arr1[2] = 0;
        for(int i = 0; i <= n; ++i)
            ans[i] = 0;
        moveDisks(n, 0, 2, 1);
        Arrays.sort(ans);
        for(int i = 0; i < ans.length; ++i)
            if(ans[i] > 0) {
                System.out.print(ans[i]);
                System.out.print(' ');
            }
    }
}


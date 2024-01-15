package by.it.group251003.dedov.lesson14;

import java.util.Arrays;
import java.util.Scanner;
public class StatesHanoiTowerC {
    static Integer[] res;
    static int[] nums = {3, 0, 0};
    public static int searchMax(){
        int buf = nums[0];
        if (buf < nums[1])
            buf = nums[1];
        if (buf < nums[2])
            buf = nums[2];
        return buf;
    }
    public static void moveDisks(int topN, int from, int to, int inter) {
        if (topN == 1) {
            nums[from]--;
            nums[to]++;
            res[searchMax()]++;
        } else {
            moveDisks(topN - 1, from, inter, to);
            nums[from]--;
            nums[to]++;
            res[searchMax()]++;
            moveDisks(topN - 1, inter, to, from);
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Integer n;
        n = in.nextInt();
        res = new Integer[n + 1];
        nums[0] = n;
        nums[1] = 0;
        nums[2] = 0;
        for (int i = 0; i <= n; ++i)
            res[i] = 0;
        moveDisks(n, 0, 2, 1);
        Arrays.sort(res);
        for (int i = 0; i < res.length; ++i)
            if (res[i] > 0) {
                System.out.print(res[i]);
                System.out.print(' ');
            }
    }
}

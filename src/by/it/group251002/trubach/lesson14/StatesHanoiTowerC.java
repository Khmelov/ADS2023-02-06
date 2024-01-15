package by.it.group251002.trubach.lesson14;

import java.util.Arrays;
import java.util.Scanner;

public class StatesHanoiTowerC {

//    Перемещение верхнего диска на промежуточный стержень.
//    Перемещение оставшихся дисков на промежуточный стержень.
//    Перемещение верхнего диска с промежуточного стержня на целевой стержень.
//    Повторение шагов 1-3 для оставшихся дисков, используя начальный и целевой стержни как промежуточные.
//    2*n − 1
    static void solve(Integer[] ALL_TOWERS_STATES, Integer HEIGHT_TO_BE_MOVED, Integer[] INTERMEDIATE_STEPS, Integer STARTING_INDEX, Integer RESULTING_INDEX){
        if(HEIGHT_TO_BE_MOVED == 1){
            ALL_TOWERS_STATES[RESULTING_INDEX]++;
            ALL_TOWERS_STATES[STARTING_INDEX]--;
            if (ALL_TOWERS_STATES[0] > ALL_TOWERS_STATES[1]) {
                if (ALL_TOWERS_STATES[0] > ALL_TOWERS_STATES[2]) {
                    INTERMEDIATE_STEPS[ALL_TOWERS_STATES[0]]++;
                } else {
                    INTERMEDIATE_STEPS[ALL_TOWERS_STATES[2]]++;
                }
            } else {
                if (ALL_TOWERS_STATES[2] > ALL_TOWERS_STATES[1]) {
                    INTERMEDIATE_STEPS[ALL_TOWERS_STATES[2]]++;
                } else {
                    INTERMEDIATE_STEPS[ALL_TOWERS_STATES[1]]++;
                }
            }


            INTERMEDIATE_STEPS[ALL_TOWERS_STATES[0] > ALL_TOWERS_STATES[1] ? (ALL_TOWERS_STATES[0] > ALL_TOWERS_STATES[2] ? ALL_TOWERS_STATES[0] : ALL_TOWERS_STATES[2]) : (ALL_TOWERS_STATES[2] > ALL_TOWERS_STATES[1] ? ALL_TOWERS_STATES[2] : ALL_TOWERS_STATES[1])]++;
        } else {
            int temp = (STARTING_INDEX == 0 ? RESULTING_INDEX == 1 ? 2 : 1 : STARTING_INDEX == 1 ? RESULTING_INDEX == 0 ? 2 : 0 : RESULTING_INDEX == 1 ? 0 : 1);
            solve(ALL_TOWERS_STATES, HEIGHT_TO_BE_MOVED - 1, INTERMEDIATE_STEPS, STARTING_INDEX, temp);
            ALL_TOWERS_STATES[RESULTING_INDEX]++;
            ALL_TOWERS_STATES[STARTING_INDEX]--;
            INTERMEDIATE_STEPS[ALL_TOWERS_STATES[0] > ALL_TOWERS_STATES[1] ? (ALL_TOWERS_STATES[0] > ALL_TOWERS_STATES[2] ? ALL_TOWERS_STATES[0] : ALL_TOWERS_STATES[2]) : (ALL_TOWERS_STATES[2] > ALL_TOWERS_STATES[1] ? ALL_TOWERS_STATES[2] : ALL_TOWERS_STATES[1])]++;
            solve(ALL_TOWERS_STATES, HEIGHT_TO_BE_MOVED - 1, INTERMEDIATE_STEPS, temp, RESULTING_INDEX);
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        Integer n;
        n = in.nextInt();
        Integer[] ans = new Integer[n + 1], s = new Integer[3];
        s[0] = n; s[1] = 0; s[2] = 0;
        for(int i = 0; i <= n; ++i)
            ans[i] = 0;
        solve(s, n, ans, 0, 2);
        Arrays.sort(ans);
        for(int i = 0; i < ans.length; ++i)
            if(ans[i] > 0) {
                System.out.print(ans[i]);
                System.out.print(' ');
            }
    }
}

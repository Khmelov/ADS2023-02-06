package by.it.group251001.kulchinskiy.lesson06;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class C_LongNotUpSubSeq {

    int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
        // Prepare to read the input data
        Scanner scanner = new Scanner(stream);

        // Read the total length of the sequence
        int n = scanner.nextInt();

        // Read the sequence into a list
        List<Integer> sequence = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            sequence.add(scanner.nextInt());
        }

        // Find the longest non-increasing subsequence
        List<Integer> subsequence = findLongestNonIncreasingSubsequence(sequence);

        // Print the length of the subsequence
        int length = subsequence.size();
        System.out.println(length);

        // Print the indices of the subsequence
        for (int i = 0; i < length; i++) {
            System.out.print(subsequence.get(i) + " ");
        }
        System.out.println();

        return length;
    }

    List<Integer> findLongestNonIncreasingSubsequence(List<Integer> sequence) {
        int n = sequence.size();
        List<Integer> dp = new ArrayList<>(); // Dynamic programming array
        List<Integer> prev = new ArrayList<>(); // Previous indices array

        // Initialize the dynamic programming and previous indices arrays
        for (int i = 0; i < n; i++) {
            dp.add(1);
            prev.add(-1);
        }

        // Find the longest non-increasing subsequence
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence.get(j) >= sequence.get(i) && dp.get(j) + 1 > dp.get(i)) {
                    dp.set(i, dp.get(j) + 1);
                    prev.set(i, j);
                }
            }
        }

        // Find the index of the last element in the longest non-increasing subsequence
        int lastIndex = 0;
        for (int i = 1; i < n; i++) {
            if (dp.get(i) > dp.get(lastIndex)) {
                lastIndex = i;
            }
        }

        // Reconstruct the longest non-increasing subsequence
        List<Integer> subsequence = new ArrayList<>();
        while (lastIndex != -1) {
            subsequence.add(lastIndex + 1); // Adjust the index by adding 1
            lastIndex = prev.get(lastIndex);
        }

        // Reverse the subsequence to get the correct order
        Collections.reverse(subsequence);

        return subsequence;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson06/dataC.txt");
        C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
        int result = instance.getNotUpSeqSize(stream);
        System.out.print(result);
    }
}

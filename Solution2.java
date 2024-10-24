package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Solution2 {

    public static int[] solution(int[] A) {
        int N = A.length;

        // Create an array of pairs (maxHeight, originalIndex)
        int[][] skyscrapers = new int[N][2];
        for (int i = 0; i < N; i++) {
            skyscrapers[i][0] = A[i];  // Max height
            skyscrapers[i][1] = i;     // Original index
        }

        // Sort skyscrapers by their maximum heights in descending order
        Arrays.sort(skyscrapers, (a, b) -> Integer.compare(b[0], a[0]));

        // Array to store assigned heights
        int[] result = new int[N];
        Set<Integer> usedHeights = new HashSet<>();  // To track already used heights

        // Assign heights in a greedy manner
        for (int i = 0; i < N; i++) {
            int maxHeight = skyscrapers[i][0];  // Get the maximum height allowed
            int assignedHeight = maxHeight; // Start with the maximum height

            // Find the largest unused height less than or equal to maxHeight
            while (usedHeights.contains(assignedHeight)) {
                assignedHeight--; // Decrease height until we find an unused one
            }

            // Assign the height to the current skyscraper
            result[skyscrapers[i][1]] = assignedHeight; // Assign the height to the original index
            usedHeights.add(assignedHeight); // Mark this height as used
        }

        return result;
    }
}

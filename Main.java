package org.example;


import java.util.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println(solution("aaAbcCABBc"));
//        System.out.println(solution("xyzXYZabcABC"));
//        System.out.println(solution("ABCabcAefG"));
//        int[] A = {1, 1, 3};
//        int[] B = {2, 2, 1};
//        int S = 3; //true


//        int[] A = {3,2,3,1};
//        int[] B = {1,3,1,2};
//        int S = 3; // false

//        int[] A = {1,2,1,6,8,7,8};
//        int[] B = {2,3,4,7,7,8,7};
//        int S = 10; //False


//        int[] A = {2,5,6,5};
//        int[] B = {5,4,2,2};
//        int S = 10; //true

       // System.out.println(solution2(A, B, S));



        // Test case 1: All skyscrapers can be built to their maximum heights
        int[] A1 = {1, 2, 3};
        int[] result1 = solution(A1);
        System.out.println("Test case 1: " + Arrays.toString(result1));
        // Expected output: [1, 2, 3] (since there are distinct and maximum heights for each)

        // Test case 2: No two skyscrapers can have the same height, but multiple valid answers
        int[] A2 = {9, 4, 3, 7, 7};
        int[] result2 = solution(A2);
        System.out.println("Test case 2: " + Arrays.toString(result2));
        // Expected output: A valid solution like [5, 2, 1, 4, 3] or other combinations where no two heights are the same

        // Test case 3: Multiple identical maximum heights
        int[] A3 = {2, 5, 4, 5, 5};
        int[] result3 = solution(A3);
        System.out.println("Test case 3: " + Arrays.toString(result3));
        // Expected output: [1, 5, 4, 2, 3] or other valid configurations where all heights are distinct

        // Test case 4: Single skyscraper
        int[] A4 = {10};
        int[] result4 = solution(A4);
        System.out.println("Test case 4: " + Arrays.toString(result4));
        // Expected output: [1] (only one skyscraper, so the height is 1)

        // Test case 5: All heights are the same
        int[] A5 = {5, 5, 5, 5, 5};
        int[] result5 = solution(A5);
        System.out.println("Test case 5: " + Arrays.toString(result5));
        // Expected output: [1, 2, 3, 4, 5] (since the heights must be distinct, but can't exceed 5)

        // Test case 6: Large values with no issues
        int[] A6 = {1000000000, 1000000000, 1000000000};
        int[] result6 = solution(A6);
        System.out.println("Test case 6: " + Arrays.toString(result6));
        // Expected output: [1, 2, 3] (since the heights must be distinct and within the allowed range)

        // Test case 7: Random large test
        int[] A7 = {10, 3, 8, 6, 4, 2};
        int[] result7 = solution(A7);
        System.out.println("Test case 7: " + Arrays.toString(result7));
        // Expected output: [10, 2, 8, 6, 4, 1] or other valid combinations


    }

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
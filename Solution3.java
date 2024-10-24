package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution3 {

    public static boolean solution3(int[] A, int[] B, int S) {
        int N = A.length;  // Number of patients

        // Create an adjacency list where each slot points to a list of patients who prefer that slot
        List<Integer>[] slotGraph = new List[N];  // N patients are numbered from 0 to N-1

        // Initialize adjacency lists for each patient
        for (int i = 0; i < N; i++) {
            slotGraph[i] = new ArrayList<>();
        }

        // Build the graph: each patient is connected to their two preferred slots
        for (int i = 0; i < N; i++) {
            slotGraph[i].add(A[i]);  // Patient i prefers slot A[i]
            slotGraph[i].add(B[i]);  // Patient i also prefers slot B[i]
        }

        // To keep track of which slot each patient is matched to
        int[] patientSlot = new int[N];
        Arrays.fill(patientSlot, -1); // -1 indicates patient is not matched yet

        // To keep track of which patient is matched to each slot
        int[] slotMatched = new int[S + 1];
        Arrays.fill(slotMatched, -1); // -1 indicates slot is free

        // Try to find a match for each patient
        for (int i = 0; i < N; i++) {
            // We use a visited array to avoid revisiting the same slots in a single DFS
            boolean[] visited = new boolean[S + 1];
            if (!dfs(i, slotGraph, patientSlot, slotMatched, visited)) {
                return false; // If we can't find a match for this patient, return false
            }
        }

        return true; // All patients have been successfully assigned
    }

    // DFS to find augmenting paths and match patients to slots
    private static boolean dfs(int patient, List<Integer>[] slotGraph, int[] patientSlot, int[] slotMatched, boolean[] visited) {
        for (int slot : slotGraph[patient]) {
            if (visited[slot]) {
                continue; // Avoid revisiting the same slot
            }
            visited[slot] = true;

            // If the slot is free or we can rematch the patient previously assigned to this slot
            if (slotMatched[slot] == -1 || dfs(slotMatched[slot], slotGraph, patientSlot, slotMatched, visited)) {
                // Match this slot to the current patient
                patientSlot[patient] = slot;
                slotMatched[slot] = patient;
                return true;
            }
        }

        return false; // No match found for the patient
    }
}

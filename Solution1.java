package org.example;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {

    public static int solution(String letters) {
        boolean[] seenLower = new boolean[26];   // To track if lowercase letter has been seen
        boolean[] validLetter = new boolean[26]; // To check if a letter still remains valid
        boolean[] seenUpper = new boolean[26];   // To track if uppercase letter has been seen
        int count = 0;  // Final count of valid letters

        // Initialize validLetter as true for all letters
        for (int i = 0; i < 26; i++) {
            validLetter[i] = true;
        }

        // Traverse the string and process each character
        for (int i = 0; i < letters.length(); i++) {
            char ch = letters.charAt(i);
            int index = Character.toLowerCase(ch) - 'a';

            if (Character.isLowerCase(ch)) {
                // If we see a lowercase letter after its uppercase, it becomes invalid
                if (seenUpper[index]) {
                    validLetter[index] = false;
                } else {
                    seenLower[index] = true;
                }
            } else {
                // It's an uppercase letter
                if (!seenLower[index]) {
                    validLetter[index] = false; // Uppercase appears before any lowercase
                }
                seenUpper[index] = true;
            }
        }

        // Now count the letters that are still valid
        for (int i = 0; i < 26; i++) {
            if (validLetter[i] && seenLower[i] && seenUpper[i]) {
                count++;
            }
        }
        return count;
    }

}

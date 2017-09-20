package com.company;

public class Solution_1_170916 {

    public static String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        String arrToBinaryString;

        for (int i = 0; i < n; i++) {
            arrToBinaryString = Integer.toBinaryString(arr1[i] | arr2[i]);
            answer[i] = "";
            if (arrToBinaryString.length() < n) {
                for (int j = 0; j < n - arrToBinaryString.length(); j++) {
                    answer[i] += " ";
                }
            }
            for (int j = 0; j < arrToBinaryString.length(); j++) {
                if (arrToBinaryString.charAt(j) == '1') answer[i] += "#";
                else answer[i] += " ";
            }
        }

        return answer;
    }
}

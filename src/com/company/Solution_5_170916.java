package com.company;

import java.util.ArrayList;

public class Solution_5_170916 {

    public static int solution(String str1, String str2) {
        int answer = 0;

        ArrayList<String> strToSplit1;
        ArrayList<String> strToSplit2;
        ArrayList<String> intersection = new ArrayList<>();
        ArrayList<String> union = new ArrayList<>();

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        strToSplit1 = makeSplit(str1);
        strToSplit2 = makeSplit(str2);

        if (strToSplit1.isEmpty() && strToSplit2.isEmpty()) return 65536;

        for (int i = 0; i < strToSplit1.size(); i++) {
            for (int j = 0; j < strToSplit2.size(); j++) {
                if (strToSplit1.get(i).equals(strToSplit2.get(j))) {
                    intersection.add(strToSplit1.get(i));
                    strToSplit1.remove(i);
                    strToSplit2.remove(j);
                    i--;
                    break;
                }
            }
        }
        union.addAll(intersection);
        union.addAll(strToSplit1);
        union.addAll(strToSplit2);

        answer = (int)(( (double)intersection.size() / (double) union.size()) * 65536);
        return answer;
    }

    public static ArrayList<String> makeSplit(String str) {
        ArrayList<String> strToSplit = new ArrayList<>();
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) < 'a' || str.charAt(i) > 'z') continue;

            if (str.charAt(i+1) < 'a' || str.charAt(i+1) > 'z') {
                i++;
                continue;
            }
            strToSplit.add(String.format("%c%c", str.charAt(i), str.charAt(i+1)));
        }
        return strToSplit;
    }
}

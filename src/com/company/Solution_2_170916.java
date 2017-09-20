package com.company;

public class Solution_2_170916 {

    public static int solution(String dartResult) {
        int answer = 0;

        int score[] = new int[3];
        int indexScore = 0;
        for (int i = 0; i < dartResult.length(); i++) {
            if ('0' <= dartResult.charAt(i) && dartResult.charAt(i) <= '9') {
                if (dartResult.charAt(i) == '1' && dartResult.charAt(i+1) == '0') {
                    score[indexScore++] = 10;
                    i++;
                } else score[indexScore++] = dartResult.charAt(i) - '0';
            } else {
                switch (dartResult.charAt(i)) {
                    case 'S':
                        break;
                    case 'D':
                        score[indexScore - 1] *= score[indexScore - 1];
                        break;
                    case 'T':
                        score[indexScore - 1] = score[indexScore - 1] * score[indexScore - 1] * score[indexScore - 1];
                        break;
                    case '*':
                        score[indexScore - 1] *= 2;
                        if (indexScore > 1) score[indexScore - 2] *= 2;
                        break;
                    case '#':
                        score[indexScore - 1] *= -1;
                        break;
                }
            }
        }
        answer = score[0] + score[1] + score[2];

        return answer;
    }

}

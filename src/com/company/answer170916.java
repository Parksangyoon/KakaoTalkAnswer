package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class answer170916 {
    public static int solution6(int m, int n, String[] board) {
        int answer = 0;

        ArrayList<List<String>> boardToArray = new ArrayList<>();
        for (int i = 0; i < m; i++) boardToArray.add(Arrays.asList(board[i].split("")));

        while (true){
            if (!findBomb(m, n, boardToArray)) break;
        }

        for (List<String> string : boardToArray) {
            for (String str : string) if (str.equals("#")) answer += 1;
        }

        return answer;
    }

    public static boolean findBomb (int m, int n, ArrayList<List<String>> board) {
        ArrayList<List<String>> checkBoard = new ArrayList<>(board);
        boolean[] checkBomb = new boolean[(m-1)*(n-1)];
        boolean find = false;

        int x, y;
        for (int i = 0; i < (m-1)*(n-1); i++) {
            x = i%(n-1);
            y = i/(n-1);
            if (board.get(y).get(x).equals("#")
                    || !board.get(y).get(x).equals(board.get(y + 1).get(x))
                    || !board.get(y).get(x).equals(board.get(y).get(x + 1))
                    || !board.get(y).get(x).equals(board.get(y + 1).get(x + 1))
                    || board.get(y + 1).get(x).equals("#")
                    || !board.get(y + 1).get(x).equals(board.get(y).get(x + 1))
                    || !board.get(y + 1).get(x).equals(board.get(y + 1).get(x + 1))
                    || board.get(y).get(x + 1).equals("#")
                    || !board.get(y).get(x + 1).equals(board.get(y + 1).get(x + 1)))
                continue;
            else {
                find = true;
                checkBomb[i] = true;
            }
        }

        for (int i = 0; i < (m-1)*(n-1); i++) {
            if (checkBomb[i]) {
                x = i%(n-1);
                y = i/(n-1);
                checkBoard.get(y).set(x, "#");
                checkBoard.get(y + 1).set(x, "#");
                checkBoard.get(y).set(x + 1, "#");
                checkBoard.get(y + 1).set(x + 1, "#");
            }
        }

        for (int i = (m-1)*(n-1) - 1; i >= n-1; i--) {
            if (checkBomb[i]) {
                x = i%(n-1);
                y = i/(n-1);

                downToBlock(checkBoard, y+1, x);
                downToBlock(checkBoard, y+1, x+1);
                downToBlock(checkBoard, y, x);
                downToBlock(checkBoard, y, x+1);
            }
        }
        return find;
    }

    public static void downToBlock(ArrayList<List<String>> checkBoard, int y, int x) {
        int upY = y - 2;
        while (upY >= 0 && checkBoard.get(upY).get(x).equals("#")) upY -= 1;
        if (upY >= 0) {
            checkBoard.get(y).set(x, checkBoard.get(upY).get(x));
            checkBoard.get(upY).set(x, "#");
        }
    }


    public static int solution5(String str1, String str2) {
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

    public static int solution2(String dartResult) {
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

    public static String[] solution1(int n, int[] arr1, int[] arr2) {
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

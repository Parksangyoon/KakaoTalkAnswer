package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_6_170916 {

    public static int solution(int m, int n, String[] board) {
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
}

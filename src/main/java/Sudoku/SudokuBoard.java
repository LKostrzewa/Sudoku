package Sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SudokuBoard {

    private int[][] board = new int[9][9];

    boolean checkBoard() {
        ArrayList<Integer> testArray = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> checkingArray = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                checkingArray.add(get(i, j));
            }
            Collections.sort(checkingArray);
            if (!checkingArray.equals(testArray)) return false;
            checkingArray.clear();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                checkingArray.add(get(j, i));
            }
            Collections.sort(checkingArray);
            if (!checkingArray.equals(testArray)) return false;
            checkingArray.clear();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                checkingArray.add(get(j / 3 + i / 3 * 3, j % 3 + i * 3 % 9));
            }
            Collections.sort(checkingArray);
            if (!checkingArray.equals(testArray)) return false;
            checkingArray.clear();
        }
        return true;
    }

    public boolean equals(SudokuBoard sudoku) {
        int licz = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == sudoku.get(i, j)) {
                    licz++;
                }
            }
        }
        if (licz == 81) {
            return true;
        } else return false;
    }

    public String toString() {
        String sout = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sout += board[i][j] + " ";
            }
            sout += "\n";
        }
        return sout;
    }

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) {
        board[x][y] = value;
    }
}

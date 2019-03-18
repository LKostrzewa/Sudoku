package Sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SudokuBoard {

    //private int[][] board = new int[9][9];

    private int[][] board = new int[9][9];

    boolean fillBoard() {
        Random rand = new Random();
        int x;
        int row = -1;
        int col = -1;
        boolean empty = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    empty = false;
                    break;
                }
            }
            if (!empty) break;
        }
        if (empty) return true;
        for (int i = 1; i <= 9; i++) {
            x = rand.nextInt(9) + 1;
            if (fits(row, col, x)) {
                board[row][col] = x;
                if (fillBoard()) return true;
                else board[row][col] = 0;
            }
        }
        return false;
    }

    boolean fits(int row, int col, int el) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == el) return false;
            if (board[i][col] == el) return false;
        }
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == el) return false;
            }
        }
        return true;
    }

    private boolean checkBoard(){
        ArrayList<Integer> testArray = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        ArrayList<Integer> checkingArray = new ArrayList<Integer>();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                checkingArray.add(get(i,j));
            }
            Collections.sort(checkingArray);
            if(!checkingArray.equals(testArray)) return false;
            checkingArray.clear();
        }

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                checkingArray.add(get(j,i));
            }
            Collections.sort(checkingArray);
            if(!checkingArray.equals(testArray)) return false;
            checkingArray.clear();
        }

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                checkingArray.add(get(j/3+i/3*3,j%3+i*3%9));
            }
            Collections.sort(checkingArray);
            if(!checkingArray.equals(testArray)) return false;
            checkingArray.clear();
        }
        return true;
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

    public int get(int x, int y) { return board[x][y]; }

    public void set(int x, int y, int value) { board[x][y] = value; }
}

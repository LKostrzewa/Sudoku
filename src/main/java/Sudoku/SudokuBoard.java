package Sudoku;

import java.util.Random;

public class SudokuBoard {

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

    void print() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    int[][] getBoard() { return board; }

    //public static void main(String[] args) {
        //SudokuBoard pawel = new SudokuBoard();
        //pawel.fillBoard();
        //pawel.print();
    //}
}

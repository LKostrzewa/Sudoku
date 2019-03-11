package Sudoku;

import java.util.Random;

public class SudokuBoard {

    private int[][] board = new int[9][9];

    void fillBoard(){
        Random rand = new Random();
        int x;
        for(int i=0; i<9; i++){
            for (int j=0; j<9;j++){
                x=rand.nextInt(9)+1;
                if(fits(i,j,x)){
                    board[i][j]=x;
                }
            }
        }
    }

    boolean fits( int row, int col,int el){
        for(int i=0;i<9;i++){
            if(board[row][i]==el) return false;
            if(board[i][col]==el) return false;
        }
        int startRow = row - row%3;
        int startCol = col - col%3;
        for(int i=startRow;i<startRow+3;i++){
            for(int j=startCol;j<startCol+3;j++){
                if(board[i][j]==el) return false;
            }
        }
        return true;
    }

    void print(){
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++) {
                System.out.print(board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SudokuBoard pawel = new SudokuBoard();
        pawel.fillBoard();
        pawel.print();
    }
}

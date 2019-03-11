package Sudoku;

import java.util.Random;

public class SudokuBoard {

    private int[][] board = new int[9][9];

    void fillBoard(){
        Random rand = new Random();
        for(int i=0; i<9; i++){
            for (int j=0; j<9;j++){
                if(board[i][j]==0 && fits(i,j,rand.nextInt(9)+1)) {
                    board[i][j]=rand.nextInt(9)+1;
                    System.out.println(board[i][j]);
                }
            }
        }
    }

    boolean fits( int m, int n,int el){
        for(int i=0;i<9;i++){
            if(board[m][i]==el) return false;
            if(board[i][n]==el) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        SudokuBoard pawel = new SudokuBoard();
        pawel.fillBoard();
    }
}

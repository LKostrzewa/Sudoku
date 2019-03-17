package Sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.MessageDigest;

import java.util.Arrays;

class SudokuBoardTest {


    @Test
    public void CorrectBoardCreatingTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();

        boolean flagRows = false;
        boolean flagCol = false;
        boolean flagSquares = false;

        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    if (sudokuBoard.getBoard()[j][k] == i) {
                        flagRows = true;
                        break;
                    } else flagRows = false;
                }
            }
        }

        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {
                    if (sudokuBoard.getBoard()[k][j] == i) {
                        flagCol = true;
                        break;
                    } else flagCol = false;
                }
            }
        }


        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (sudokuBoard.getBoard()[j][k] == i) {
                        flagSquares = true;
                        break;
                    } else flagSquares = false;
                }
                if (flagSquares) break;
            }
        }

        Assertions.assertTrue(flagRows);
        Assertions.assertTrue(flagCol);
        Assertions.assertTrue(flagSquares);
    }

    @Test
    public void alwaysDifferentBoardTest() {
        SudokuBoard sudoku1 = new SudokuBoard();
        sudoku1.fillBoard();
        SudokuBoard sudoku2 = new SudokuBoard();
        sudoku2.fillBoard();

       /* int[][] pom = new int[9][9];
        int[][] pom2 = new int[9][9];
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                pom[i][j] = i+j;
                pom2[i][j] = i+j;
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(pom[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(pom2[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }*/


        Assertions.assertTrue(!Arrays.deepEquals(sudoku1.getBoard(), sudoku2.getBoard()));
        //Assertions.assertTrue(Arrays.deepEquals(pom,pom2));
    }

}
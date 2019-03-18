package Sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.MessageDigest;

import java.util.ArrayList;
import java.util.Arrays;

class SudokuBoardTest {


    @Test
    public void CorrectBoardCreatingTest() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();

        ArrayList<Integer> Test = new ArrayList<Integer>(Arrays.asList( 1,2,3,4,5,6,7,8,9,
                                                                        1,2,3,4,5,6,7,8,9,
                                                                        1,2,3,4,5,6,7,8,9,
                                                                        1,2,3,4,5,6,7,8,9,
                                                                        1,2,3,4,5,6,7,8,9,
                                                                        1,2,3,4,5,6,7,8,9,
                                                                        1,2,3,4,5,6,7,8,9,
                                                                        1,2,3,4,5,6,7,8,9,
                                                                        1,2,3,4,5,6,7,8,9));

        //boolean flagRows = false;
        //boolean flagCol = false;
        //boolean flagSquares = false;

        ArrayList <Integer> Rows = new ArrayList<Integer>();
        ArrayList <Integer> Columns = new ArrayList<Integer>();
        ArrayList <Integer> Squares = new ArrayList<Integer>();

        for (int j = 0; j < 9; j++) {
            for (int i = 1; i <= 9; i++) {
                for (int k = 0; k < 9; k++) {
                    if (sudokuBoard.get(j,k) == i) {
                        //flagRows = true;
                        Rows.add(sudokuBoard.get(j,k));
                        //break;
                    } //else flagRows = false;
                }
            }
        }

        for (int j = 0; j < 9; j++) {
            for (int i = 1; i <= 9; i++) {
                for (int k = 0; k < 9; k++) {
                    if (sudokuBoard.get(k,j) == i) {
                        //flagCol = true;
                        Columns.add(sudokuBoard.get(k,j));
                        //break;
                    } //else flagCol = false;
                }
            }
        }

        for (int m=0; m<9; m++) {
            for (int i = 1; i <= 9; i++) {
                for (int j = 3*(m/3); j < 3*(m/3)+3; j++) {
                    for (int k = 3*(m%3); k < 3*(m%3)+3; k++) {
                        if (sudokuBoard.get(j,k) == i) {
                            //flagSquares = true;
                            Squares.add(sudokuBoard.get(j,k));
                            //break;
                        } //else flagSquares = false;
                    }
                    //if (flagSquares) break;
                }
            }
        }

        //Assertions.assertTrue(flagRows);
        //Assertions.assertTrue(flagCol);
        //Assertions.assertTrue(flagSquares);
        Assertions.assertEquals(Rows,Test);
        Assertions.assertEquals(Columns,Test);
        Assertions.assertEquals(Squares,Test);

    }

    @Test
    public void alwaysDifferentBoardTest() {
        SudokuBoard sudoku1 = new SudokuBoard();
        sudoku1.fillBoard();
        SudokuBoard sudoku2 = new SudokuBoard();
        sudoku2.fillBoard();

        Assertions.assertTrue(sudoku1.toString() != sudoku2.toString());
    }

}
package Sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class SudokuBoardTest {

    @Test
    public void CorrectBoardCreatingTest(){
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.fillBoard();

    }

    @Test
    public void alwaysDifferentBoardTest(){
        SudokuBoard sudoku1 = new SudokuBoard();
        sudoku1.fillBoard();
        SudokuBoard sudoku2 = new SudokuBoard();
        sudoku2.fillBoard();

        Assertions.assertTrue(!Arrays.equals(sudoku1.getBoard(),sudoku2.getBoard()));
    }
    
}
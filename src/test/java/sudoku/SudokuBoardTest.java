package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class SudokuBoardTest {
    @Test
    public void CheckingCorrectBoardMethod(){
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku = new SudokuBoard();
        solver.solve(sudoku);
        Assertions.assertTrue(sudoku.checkBoard());
        if(sudoku.get(2,4)!=8) {sudoku.set(2,4,8);}
        else sudoku.set(2,4,7);
        Assertions.assertFalse(sudoku.checkBoard());
    }

    @Test
    public void alwaysDifferentBoardTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku1 = new SudokuBoard();
        solver.solve(sudoku1);
        SudokuBoard sudoku2 = new SudokuBoard();
        solver.solve(sudoku2);

        Assertions.assertFalse(sudoku1.equals(sudoku2));
    }

    @Test
    public void toStringTest(){
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku1 = new SudokuBoard();
        solver.solve(sudoku1);
        SudokuBoard sudoku2 = new SudokuBoard();
        solver.solve(sudoku2);

        Assertions.assertNotEquals(sudoku1.toString(),sudoku2.toString());
    }

}
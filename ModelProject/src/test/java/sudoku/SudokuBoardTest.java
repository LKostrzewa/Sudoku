package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SudokuBoardTest {
    @Test
    public void CheckingCorrectBoardMethod() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku = new SudokuBoard();
        solver.solve(sudoku);
        SudokuBoard sudoku1 = new SudokuBoard();
        solver.solve(sudoku1);
        SudokuBoard sudoku2 = new SudokuBoard();
        solver.solve(sudoku2);

        Assertions.assertTrue(sudoku.checkBoard());

        int pom = sudoku.get(3, 1);
        sudoku.set(3, 1, sudoku.get(4, 1));
        sudoku.set(4, 1, pom);
        Assertions.assertFalse(sudoku.checkBoard());

        int pom1 = sudoku1.get(1, 8);
        sudoku1.set(1, 8, sudoku1.get(1, 5));
        sudoku1.set(1, 5, pom1);
        Assertions.assertFalse(sudoku1.checkBoard());

        for (int i = 0; i < 9; i++) {
            int pom2 = sudoku2.get(2, i);
            sudoku2.set(2, i, sudoku2.get(3, i));
            sudoku2.set(3, i, pom2);
        }
        Assertions.assertFalse(sudoku2.checkBoard());
    }

    @Test
    public void SameBoardTest() {
        SudokuBoard sudoku = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudoku);
        //System.out.println(sudoku);
        SudokuBoard sudoku3 = new SudokuBoard(sudoku);

        Assertions.assertEquals(sudoku,sudoku3);
        Assertions.assertEquals(sudoku.hashCode(),sudoku3.hashCode());
    }

    @Test
    public void toStringTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku1 = new SudokuBoard();
        solver.solve(sudoku1);
        SudokuBoard sudoku2 = new SudokuBoard();
        solver.solve(sudoku2);
        SudokuBoard sudoku3 = new SudokuBoard(sudoku1);
        Assertions.assertEquals(sudoku1.toString(),sudoku3.toString());
        Assertions.assertNotEquals(sudoku1.toString(), sudoku2.toString());
    }

}
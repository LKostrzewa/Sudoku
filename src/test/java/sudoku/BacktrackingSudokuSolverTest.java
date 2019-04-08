package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BacktrackingSudokuSolverTest {

    @Test
    public void alwaysDifferentBoardTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku1 = new SudokuBoard();
        solver.solve(sudoku1);
        SudokuBoard sudoku2 = new SudokuBoard();
        solver.solve(sudoku2);

        Assertions.assertFalse(sudoku1.equals(sudoku2));
        Assertions.assertNotEquals(sudoku1.hashCode(), sudoku2.hashCode());

        SudokuBoard sudoku3 = new SudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudoku3.set(i, j, sudoku1.get(i, j));
            }
        }

        Assertions.assertTrue(sudoku1.equals(sudoku3));
    }

}

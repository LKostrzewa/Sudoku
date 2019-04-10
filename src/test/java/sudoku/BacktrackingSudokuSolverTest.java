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
    }

}

package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JdbcSudokuBoardDaoTest {

    @Test
    public void JDBCTest() {
        SudokuBoard sudoku = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudoku);
        try {
            JdbcSudokuBoardDao jdbc = new JdbcSudokuBoardDao("siemka");
            //jdbc.write(sudoku);
            //jdbc.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

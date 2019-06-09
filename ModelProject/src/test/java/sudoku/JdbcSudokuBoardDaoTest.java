package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JdbcSudokuBoardDaoTest {

    @Test
    public void JDBCTest() {
        SudokuBoard sudoku = new SudokuBoard();
        SudokuBoard sudoku2;
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudoku);
        try {
            JdbcSudokuBoardDao jdbc = new JdbcSudokuBoardDao("siemka");
            jdbc.delete();
            jdbc.write(sudoku);
            sudoku2 = jdbc.read();
            System.out.println(sudoku.toString());
            System.out.println(sudoku2.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

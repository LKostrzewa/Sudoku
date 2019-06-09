package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static sudoku.SudokuBoardDaoFactory.getJDBCdao;

public class JdbcSudokuBoardDaoTest {

    @Test
    public void jdbcTest() {
        SudokuBoard sudoku = new SudokuBoard();
        SudokuBoard sudoku2;
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudoku);
        try (JdbcSudokuBoardDao jdbc = getJDBCdao("sudoku")) {
            jdbc.delete();
            jdbc.write(sudoku);
            sudoku2 = jdbc.read();
            Assertions.assertEquals(sudoku,sudoku2);
        }
    }
}

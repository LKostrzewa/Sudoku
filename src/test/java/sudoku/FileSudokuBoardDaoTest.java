package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileSudokuBoardDaoTest {

    @Test
    public void usingFileTest(){
        FileSudokuBoardDao files = new FileSudokuBoardDao("C:\\Users\\Lukasz\\Desktop\\Komponentowe\\Sudoku\\testowy.txt");
        SudokuBoard sudoku = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudoku);
        files.write(sudoku);
        SudokuBoard sudoku2 = new SudokuBoard(files.read());
        Assertions.assertEquals(sudoku,sudoku2);
    }
}

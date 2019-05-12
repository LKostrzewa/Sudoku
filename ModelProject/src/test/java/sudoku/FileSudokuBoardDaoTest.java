package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileSudokuBoardDaoTest {

    @Test
    public void usingFileTest(){
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        //FileSudokuBoardDao files = factory.getSudokuBoardDaoFactor("C:\\Users\\Lukasz\\Desktop\\Komponentowe\\Sudoku\\testowy.txt");
        FileSudokuBoardDao files = factory.getSudokuBoardDaoFactor("/Users/pawelbialek/Desktop/testowy.bin");
        SudokuBoard sudoku = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudoku);
        files.write(sudoku);
        SudokuBoard sudoku2 = new SudokuBoard(files.read());
        Assertions.assertEquals(sudoku,sudoku2);
    }
}

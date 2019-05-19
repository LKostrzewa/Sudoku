package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static sudoku.SudokuBoardDaoFactory.getSudokuBoardDaoFactory;

public class FileSudokuBoardDaoTest {

    @Test
    public void usingFileTest(){
        FileSudokuBoardDao files = getSudokuBoardDaoFactory("C:\\Users\\Lukasz\\Desktop\\Komponentowe\\sudoku\\testowy.bin");
        //FileSudokuBoardDao files = factory.getSudokuBoardDaoFactor("/Users/pawelbialek/Desktop/testowy.bin");
        SudokuBoard sudoku = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudoku);
        files.write(sudoku);
        SudokuBoard sudoku2 = new SudokuBoard();
        try {
            sudoku2 = (SudokuBoard)files.read().clone();
        }
        catch (CloneNotSupportedException e){}
        Assertions.assertEquals(sudoku,sudoku2);
    }
}

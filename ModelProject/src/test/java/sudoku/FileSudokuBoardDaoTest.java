package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static sudoku.SudokuBoardDaoFactory.getSudokuBoardDaoFactory;

public class FileSudokuBoardDaoTest {

    @Test
    public void usingFileTest(){
        try(FileSudokuBoardDao files = getSudokuBoardDaoFactory("witanko.bin")){
            SudokuBoard sudoku = new SudokuBoard();
            BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
            solver.solve(sudoku);
            files.write(sudoku);
            SudokuBoard sudoku2 = new SudokuBoard();
            sudoku2 = (SudokuBoard)files.read().clone();
            Assertions.assertEquals(sudoku,sudoku2);
        }
        //catch (IOException e ){
          //  System.out.println("Nie znaleziono pliku123");
        //}

        //FileSudokuBoardDao files = factory.getSudokuBoardDaoFactor("/Users/pawelbialek/Desktop/testowy.bin");
    }
   /* @Test
    public void testingTest(){
        try(FileSudokuBoardDao files = getSudokuBoardDaoFactory("witanko.bin")){
            SudokuBoard sudokuBoard = files.read();
            sudokuBoard.get(2,4);
        }
        catch (IOException e ){
            System.out.println("Nie znaleziono pliku123");
        }
    }*/
}

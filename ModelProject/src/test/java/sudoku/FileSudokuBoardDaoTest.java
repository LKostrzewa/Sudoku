package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static sudoku.SudokuBoardDaoFactory.getSudokuBoardDaoFactory;

public class FileSudokuBoardDaoTest {

    private Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);

    @Test
    public void usingFileTest(){
        try(FileSudokuBoardDao files = getSudokuBoardDaoFactory("sudoku.bin")){
            SudokuBoard sudoku = new SudokuBoard();
            SudokuBoard sudoku2 = new SudokuBoard();
            BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
            solver.solve(sudoku);
            try {
                files.write(sudoku);
            }
            catch (FileException er){
                logger.error("Wyjatek zapisu podczas testu");
            }
            try {
                sudoku2 = files.read().clone();
            }
            catch (FileException er){
                logger.error("Wyjatek odczytu podczas testu");
            }
            Assertions.assertEquals(sudoku,sudoku2);
        }
        catch (FileException asda){
            logger.error("Wyjatek zamkniecia podczas testu");
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

package sudoku;

import java.io.IOException;

public class SudokuBoardDaoFactory {

    public static FileSudokuBoardDao getSudokuBoardDaoFactory(final String fileName) {
        try (FileSudokuBoardDao file = new FileSudokuBoardDao(fileName)) {
            return file;
        }
        /*catch (IOException e ){
            System.out.println("Nie znaleziono pliku!");
            return null;
        }*/
        // Before:
        //return new FileSudokuBoardDao(fileName);
    }
}

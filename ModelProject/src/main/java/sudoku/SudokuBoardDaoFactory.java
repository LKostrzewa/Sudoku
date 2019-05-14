package sudoku;

import java.io.IOException;

public class SudokuBoardDaoFactory {

    public static FileSudokuBoardDao getSudokuBoardDaoFactory(final String fileName) {
        /*try (FileSudokuBoardDao file = new FileSudokuBoardDao(fileName)) {
            return file;
        }
        catch (IOException e ){
            System.out.println("Nie znaleziono pliku123");
            return null;
        }*/
        // Before:
        try {
            return new FileSudokuBoardDao(fileName);
        }
        catch (IOException e ){
            System.out.println("Nie znaleziono pliku123");
            return null;
        }
    }
}

package sudoku;

public class SudokuBoardDaoFactory {

    public static FileSudokuBoardDao
    getSudokuBoardDaoFactory(final String fileName) {
        /*try (FileSudokuBoardDao file =
        new FileSudokuBoardDao(fileName)) {
            return file;
        }
        catch (IOException e ){
            System.out.println("Nie znaleziono pliku123");
            return null;
        }*/
        // Before:
        return new FileSudokuBoardDao(fileName);

    }
}

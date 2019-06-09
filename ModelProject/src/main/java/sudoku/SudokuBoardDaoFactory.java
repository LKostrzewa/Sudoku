package sudoku;

public class SudokuBoardDaoFactory {

    // No nw czy to tak ma byc
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
        //return new FileSudokuBoardDao(fileName);
        return new FileSudokuBoardDao(fileName);
    }

    public static JdbcSudokuBoardDao getJDBCdao(final String fileName) {
        return new JdbcSudokuBoardDao(fileName);
    }
}

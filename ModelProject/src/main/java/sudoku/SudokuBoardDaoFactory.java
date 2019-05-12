package sudoku;

public class SudokuBoardDaoFactory {

    public FileSudokuBoardDao getSudokuBoardDaoFactory(final String fileName) {
        try (FileSudokuBoardDao file = new FileSudokuBoardDao(fileName)) {
            return file;
        }
        // Before:
        //return new FileSudokuBoardDao(fileName);
    }
}

package sudoku;

public class SudokuBoardDaoFactory {

    public FileSudokuBoardDao getSudokuBoardDaoFactor(final String fileName) {
        /*try (FileSudokuBoardDao file = new FileSudokuBoardDao(fileName)) {
            file.read();
            //Tu program nie dochodzi == brak wywołania close()
        }
        return null;*/
        // Before:
        return new FileSudokuBoardDao(fileName);
    }
}

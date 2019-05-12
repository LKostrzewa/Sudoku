package sudoku;

public class SudokuBoardDaoFactory {

    public FileSudokuBoardDao getSudokuBoardDaoFactor(final String fileName) {
        return new FileSudokuBoardDao(fileName);
    }
}

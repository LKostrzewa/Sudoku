package sudoku;

public class SudokuBoardDaoFactory {

    public FileSudokuBoardDao getSudokuBoardDaoFactor(String fileName){
        return new FileSudokuBoardDao(fileName);
    }
}

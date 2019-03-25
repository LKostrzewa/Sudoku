package sudoku;

public class SudokuElement {
    private SudokuField[] fields = new SudokuField[9];
    public SudokuElement(SudokuField[] fields){
        this.fields=fields;
    }
    public boolean verify(){
        return true;
    }
}

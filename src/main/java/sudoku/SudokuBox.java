package sudoku;

public class SudokuBox extends SudokuElement {
    public SudokuBox(SudokuField[] fields){
        super(fields);
    }

    public String toString() {
        String sout = "";
        for (int i = 0; i < 3; i++) {
            for(int j=i*3; j<3*i+3; j++)
            {
                sout += fields[j].getFieldValue() + " ";
            }
            sout += "\n";
        }
        return sout;
    }
}

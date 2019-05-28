package sudoku;


import java.util.List;

public class SudokuBox extends SudokuElement {
    public SudokuBox(final List<SudokuField> fields) {
        super(fields);
    }

    /*@Override
    public String toString() {
        String sout = "";
        for (int i = 0; i < 3; i++) {
            for (int j = i * 3; j < 3 * i + 3; j++) {
                sout += fields.get(j).getFieldValue() + " ";
            }
            sout += "\n";
        }
        return sout;
    }*/
}

package sudoku;

import com.google.common.base.MoreObjects;

import java.util.List;

public class SudokuRow extends SudokuElement {
    public SudokuRow(final List<SudokuField> fields) {
        super(fields);
    }

    /*@Override
    public String toString() {
        String sout = "";
        for (int i = 0; i < 9; i++) {
            sout += fields.get(i).getFieldValue() + " ";
        }
        sout += "\n";
        return sout;
    }*/
}
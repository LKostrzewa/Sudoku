package sudoku;

import java.util.List;


public class SudokuColumn extends SudokuElement {
    public SudokuColumn(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public final Object clone() {
        return new SudokuColumn(getListForClone());
    }

    /*@Override
    public String toString() {
        String sout = "";
        for (int i = 0; i < 9; i++) {
            sout += fields.get(i).getFieldValue();
            sout += "\n";
        }
        return sout;
    }*/
}

package sudoku;


import java.util.List;

public class SudokuRow extends SudokuElement {
    public SudokuRow(final List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public Object clone() {
        return new SudokuRow(getListForClone());
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

package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuElement {
    //protected SudokuField[] fields;
    protected List<SudokuField> fields;

    public SudokuElement(final List<SudokuField> fields) {
        this.fields = fields;
        /** Czy to działa?? **/
    }

    public boolean verify() {
        ArrayList<Integer> testArray = new ArrayList<Integer>(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> checkingArray = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            checkingArray.add(fields.get(i).getFieldValue());
        }
        Collections.sort(checkingArray);
        if (!checkingArray.equals(testArray)) {
            return false;
        }
        return true;
    }
}

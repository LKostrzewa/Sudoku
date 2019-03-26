package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SudokuElement {
    private SudokuField[] fields;
    public SudokuElement(SudokuField[] fields){
        this.fields=fields;
    }
    public boolean verify(){
        ArrayList<Integer> testArray = new ArrayList<Integer>(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> checkingArray = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
                checkingArray.add(fields[i].getFieldValue());
            }
            Collections.sort(checkingArray);
            if (!checkingArray.equals(testArray)) {
                return false;
            }
        return true;
    }
}

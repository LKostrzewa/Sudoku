package sudoku;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class SudokuElement implements Serializable, Cloneable {
    //protected SudokuField[] fields;
    protected List<SudokuField> fields;

    public SudokuElement(final List<SudokuField> fields) {
        this.fields = fields;
    }

    public boolean verify() {
        ArrayList<Integer> testArray = new ArrayList<>(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> checkingArray = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            checkingArray.add(fields.get(i).getFieldValue());
        }
        Collections.sort(checkingArray);
        return checkingArray.equals(testArray);
    }

    protected List<SudokuField> getListForClone() {
        List<SudokuField> sudokuFields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            sudokuFields.add(new SudokuField(fields.get(i).getFieldValue()));
        }
        return sudokuFields;
    }

    public abstract Object clone(); /*throws CloneNotSupportedException {
        return super.clone();
        //SudokuElement sudokuElement = new SudokuElement(fields);
        //clone poprawic (tzn napisac dla kazdego oddzielne)
    }*/

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuElement that = (SudokuElement) o;
        return Objects.equal(fields, that.fields);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(fields);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this)
                .add("fields", fields)
                .toString();
    }

    public final void set(final int x, final int val) {
        fields.get(x).setFieldValue(val);
    }

    public final int get(final int x) {
        return fields.get(x).getFieldValue();
    }
}

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

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }

    @Override
    public boolean equals(final Object o) {
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
    public int hashCode() {
        return Objects.hashCode(fields);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("fields", fields)
                .toString();
    }
}

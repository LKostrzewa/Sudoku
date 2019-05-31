package sudoku;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

public class SudokuField
        implements Serializable, Cloneable, Comparable<SudokuField> {
    private int value;

    SudokuField(final int val) {
        this.value = val;
    }

    public final int getFieldValue() {
        return value;
    }

    public final void setFieldValue(final int val) {
        this.value = val;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuField field = (SudokuField) o;
        return value == field.value;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }

    @Override
    public final int compareTo(final SudokuField o) {
        if (this.getFieldValue() < o.getFieldValue()) {
            return -1;
        }
        if (this.getFieldValue() == o.getFieldValue()) {
            return 0;
        } else {
            return 1;
        }
    }

    public final SudokuField clone() /*throws CloneNotSupportedException */ {
        return new SudokuField(getFieldValue());
    }
}

package sudoku;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private int value;

    SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
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
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("value", value)
                .toString();
    }

    @Override
    public int compareTo(SudokuField o) {
        if(this.getFieldValue()<o.getFieldValue()) return -1;
        if(this.getFieldValue()==o.getFieldValue()) return 0;
        else return 1;
    }

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
}

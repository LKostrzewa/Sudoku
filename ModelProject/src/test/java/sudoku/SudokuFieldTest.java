package sudoku;

import com.google.common.collect.Ordering;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuFieldTest {
    @Test
    public void correctTest(){
        int pom = 5;
        SudokuField field = new SudokuField(5);
        Assertions.assertEquals(field.getFieldValue(),pom);
        pom += 1;
        Assertions.assertNotEquals(field.getFieldValue(),pom);
        field.setFieldValue(pom);
        Assertions.assertEquals(field.getFieldValue(),pom);
    }

    @Test
    public void compareToTest(){
        SudokuField field1 = new SudokuField(5);
        SudokuField field2 = new SudokuField(7);
        SudokuField field3 = new SudokuField(5);
        SudokuField field4 = new SudokuField(3);
        List<SudokuField> list = Arrays.asList(field1,field2,field3,field4);
        Collections.sort(list);
        boolean sorted = Ordering.natural().isOrdered(list);
        Assertions.assertTrue(sorted);
        Assertions.assertEquals(field1.compareTo(field2),-1);
        Assertions.assertEquals(field1.compareTo(field3), 0);
        Assertions.assertEquals(field2.compareTo(field3),1);
    }
}

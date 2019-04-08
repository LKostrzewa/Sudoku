package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}

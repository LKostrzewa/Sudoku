package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuElementTest {

    @Test
    public void verifyTest() {
        List<SudokuField> list = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            list.set(i, new SudokuField(i + 1));
        }
        Collections.shuffle(list);
        SudokuRow row = new SudokuRow(list);
        Assertions.assertTrue(row.verify());
        Collections.shuffle(list);
        SudokuBox box = new SudokuBox(list);
        Assertions.assertTrue(box.verify());
        Collections.shuffle(list);
        SudokuColumn col = new SudokuColumn(list);
        Assertions.assertTrue(col.verify());
    }

    @Test
    public void toStringTest() {

        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku1 = new SudokuBoard();
        solver.solve(sudoku1);
        //SudokuBoard sudoku2 = new SudokuBoard(sudoku1);
        SudokuBoard sudoku2 = sudoku1.clone();
        Assertions.assertEquals(sudoku1.getColumn(0).toString(), sudoku2.getColumn(0).toString());
        Assertions.assertEquals(sudoku1.getRow(0).toString(), sudoku2.getRow(0).toString());
        Assertions.assertEquals(sudoku1.getBox(0, 0).toString(), sudoku2.getBox(0, 0).toString());

        Assertions.assertNotEquals(sudoku1.getColumn(0).toString(), sudoku1.getColumn(1).toString());
        Assertions.assertNotEquals(sudoku1.getBox(0, 0).toString(), sudoku1.getBox(0, 1).toString());
        Assertions.assertNotEquals(sudoku1.getRow(1).toString(), sudoku1.getRow(0).toString());
    }

    @Test
    public void cloneTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku1 = new SudokuBoard();
        solver.solve(sudoku1);

        SudokuColumn col1 = sudoku1.getColumn(0);
        SudokuColumn col2 = (SudokuColumn) col1.clone();
        Assertions.assertEquals(col1, col2);
        col2.set(0, col2.get(1));
        Assertions.assertNotEquals(col1, col2);

        SudokuRow row1 = sudoku1.getRow(0);
        SudokuRow row2 = (SudokuRow) row1.clone();
        Assertions.assertEquals(row1, row2);
        row2.set(0, row2.get(1));
        Assertions.assertNotEquals(row1, row2);

        SudokuBox box1 = sudoku1.getBox(0, 0);
        SudokuBox box2 = (SudokuBox) box1.clone();
        Assertions.assertEquals(box1, box2);
        box2.set(0, box2.get(1));
        Assertions.assertNotEquals(row1, row2);
    }
}

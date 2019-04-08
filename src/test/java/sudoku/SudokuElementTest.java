package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuElementTest {

    @Test
    public void verifyTest(){
        List<SudokuField> list = Arrays.asList(new SudokuField[9]);
        for(int i =0 ;i <9;i++){
            list.set(i, new SudokuField(i+1));
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
    public void toStringTest(){

        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku1 = new SudokuBoard();
        solver.solve(sudoku1);

        Assertions.assertNotEquals(sudoku1.getColumn(0).toString(), sudoku1.getColumn(1).toString());
        Assertions.assertNotEquals(sudoku1.getBox(0,0).toString(), sudoku1.getBox(0,1).toString());
        Assertions.assertNotEquals(sudoku1.getRow(1).toString(), sudoku1.getRow(0).toString());
    }
}

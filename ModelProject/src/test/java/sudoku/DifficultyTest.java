package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DifficultyTest {

    @Test
    public void cleanBoardTest(){
        SudokuBoard sudoku = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudoku);
        Difficluty d1 = Difficluty.EASY;
        d1.clean(sudoku);
        int sum =0;
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(sudoku.get(i,j)==0) sum++;
            }
        }
        Assertions.assertEquals(sum,20);

        SudokuBoard sudoku2 = new SudokuBoard();
        d1=Difficluty.MEDIUM;
        solver.solve(sudoku2);
        d1.clean(sudoku2);
        sum =0;
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(sudoku2.get(i,j)==0) sum++;
            }
        }
        Assertions.assertEquals(sum,40);

        SudokuBoard sudoku3 = new SudokuBoard();
        d1=Difficluty.HARD;
        solver.solve(sudoku3);
        d1.clean(sudoku3);
        sum = 0;
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(sudoku3.get(i,j)==0) sum++;
            }
        }
        Assertions.assertEquals(sum,60);
    }
}

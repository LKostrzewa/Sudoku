package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SudokuBoardTest {
    @Test
    public void CheckingCorrectBoardMethod(){
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku = new SudokuBoard();
        solver.solve(sudoku);
        SudokuBoard sudoku1 = new SudokuBoard();
        solver.solve(sudoku1);
        SudokuBoard sudoku2 = new SudokuBoard();
        solver.solve(sudoku2);

        Assertions.assertTrue(sudoku.checkBoard());

        int pom = sudoku.get(3,1);
        sudoku.set(3,1,sudoku.get(4,1));
        sudoku.set(4,1,pom);
        Assertions.assertFalse(sudoku.checkBoard());

        int pom1 = sudoku1.get(1,8);
        sudoku1.set(1,8,sudoku1.get(1,5));
        sudoku1.set(1,5,pom1);
        Assertions.assertFalse(sudoku1.checkBoard());

        for (int i=0; i<9; i++){
            int pom2 = sudoku2.get(2,i);
            sudoku2.set(2,i,sudoku2.get(3,i));
            sudoku2.set(3,i,pom2);
        }
        Assertions.assertFalse(sudoku2.checkBoard());
    }

    @Test
    public void alwaysDifferentBoardTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku1 = new SudokuBoard();
        solver.solve(sudoku1);
        SudokuBoard sudoku2 = new SudokuBoard();
        solver.solve(sudoku2);

        Assertions.assertFalse(sudoku1.equals(sudoku2));
        Assertions.assertNotEquals(sudoku1.hashCode(), sudoku2.hashCode());

        SudokuBoard sudoku3 = new SudokuBoard();
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                sudoku3.set(i,j,sudoku1.get(i,j));
            }
        }

        Assertions.assertTrue(sudoku1.equals(sudoku3));
    }

    @Test
    public void toStringTest(){
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku1 = new SudokuBoard();
        solver.solve(sudoku1);
        SudokuBoard sudoku2 = new SudokuBoard();
        solver.solve(sudoku2);
        System.out.println(sudoku2.toString());
        System.out.println(sudoku2.getBox(1,1).toString());

        Assertions.assertNotEquals(sudoku1.toString(),sudoku2.toString());
    }

}
package sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class SudokuBoardTest {


    @Test
    public void CorrectBoardCreatingTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard();
        solver.solve(sudokuBoard);

        ArrayList<Integer> Test = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9));

        ArrayList<Integer> Rows = new ArrayList<Integer>();
        ArrayList<Integer> Columns = new ArrayList<Integer>();
        ArrayList<Integer> Squares = new ArrayList<Integer>();

        for (int j = 0; j < 9; j++) {
            for (int i = 1; i <= 9; i++) {
                for (int k = 0; k < 9; k++) {
                    if (sudokuBoard.get(j, k) == i) {
                        Rows.add(sudokuBoard.get(j, k));
                        //break;
                    }
                }
            }
        }

        for (int j = 0; j < 9; j++) {
            for (int i = 1; i <= 9; i++) {
                for (int k = 0; k < 9; k++) {
                    if (sudokuBoard.get(k, j) == i) {
                        Columns.add(sudokuBoard.get(k, j));
                        //break;
                    }
                }
            }
        }

        for (int m = 0; m < 9; m++) {
            for (int i = 1; i <= 9; i++) {
                for (int j = 3 * (m / 3); j < 3 * (m / 3) + 3; j++) {
                    for (int k = 3 * (m % 3); k < 3 * (m % 3) + 3; k++) {
                        if (sudokuBoard.get(j, k) == i) {
                            Squares.add(sudokuBoard.get(j, k));
                            //break;
                        }
                    }
                }
            }
        }

        Assertions.assertEquals(Rows, Test);
        Assertions.assertEquals(Columns, Test);
        Assertions.assertEquals(Squares, Test);

    }

    @Test
    public void alwaysDifferentBoardTest() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku1 = new SudokuBoard();
        solver.solve(sudoku1);
        SudokuBoard sudoku2 = new SudokuBoard();
        solver.solve(sudoku2);

        System.out.println(sudoku1.toString());
        System.out.println(sudoku2.toString());

        Assertions.assertFalse(sudoku1.equals(sudoku2));
    }

}
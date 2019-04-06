package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public final boolean solve(final SudokuBoard sudoku) {
        ArrayList<Integer> randArray = new ArrayList<Integer>(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(randArray);
        int row = -1;
        int col = -1;
        boolean empty = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (sudoku.get(i, j) == 0) {
                    row = i;
                    col = j;
                    empty = false;
                    break;
                }
            }
            if (!empty) {
                break;
            }
        }
        if (empty) {
            return true;
        }
        for (int i = 0; i < 9; i++) {
            if (fits(row, col, randArray.get(i), sudoku)) {
                sudoku.set(row, col, randArray.get(i));
                if (solve(sudoku)) {
                    return true;
                } else {
                    sudoku.set(row, col, 0);
                }
            }
        }
        return false;
    }

    private boolean fits(int row, int col, int el, final SudokuBoard sudoku) {
        for (int i = 0; i < 9; i++) {
            if (sudoku.get(row, i) == el) {
                return false;
            }
            if (sudoku.get(i, col) == el) {
                return false;
            }
        }
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (sudoku.get(i, j) == el) {
                    return false;
                }
            }
        }
        return true;
    }
}

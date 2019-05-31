package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BacktrackingSudokuSolver {

    public final boolean solve(final SudokuBoard sudoku) {
        //CHECKSTYLE:OFF
        ArrayList<Integer> randArray = new ArrayList<>(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        //CHECKSTYLE:ON
        Collections.shuffle(randArray);
        int row = -1;
        int col = -1;
        boolean empty = true;
        for (int i = 0; i < SudokuBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
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
        for (int i = 0; i < SudokuBoard.BOARD_SIZE; i++) {
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

    private boolean fits(final int row, final int col,
                         final int el, final SudokuBoard sudoku) {
        for (int i = 0; i < SudokuBoard.BOARD_SIZE; i++) {
            if (sudoku.get(row, i) == el) {
                return false;
            }
            if (sudoku.get(i, col) == el) {
                return false;
            }
        }
        int startRow = row - row % SudokuBox.ELEMENT_SIZE;
        int startCol = col - col % SudokuBox.ELEMENT_SIZE;
        for (int i = startRow; i < startRow + SudokuBox.ELEMENT_SIZE; i++) {
            for (int j = startCol; j < startCol + SudokuBox.ELEMENT_SIZE; j++) {
                if (sudoku.get(i, j) == el) {
                    return false;
                }
            }
        }
        return true;
    }
}

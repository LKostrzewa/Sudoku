package sudoku;

import java.util.Arrays;
import java.util.List;


public class SudokuBoard {

    //private SudokuField[][] board = new SudokuField[9][9];
    private List<List<SudokuField>> board;

    public SudokuBoard() {

        board = Arrays.asList(new List[9]);

        for (int i = 0; i < 9; i++) {
            this.board.set(i, Arrays.asList(new SudokuField[9]));
        }

        //Teraz konieczne wpisanie 0
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.get(i).set(j, new SudokuField(0));
            }
        }
    }

    public final boolean checkBoard() {
        for (int i = 0; i < 9; i++) {
            if (!getRow(i).verify()) {
                return false;
            }
            if (!getColumn(i).verify()) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }


    public final boolean equals(final SudokuBoard sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i).get(j).getFieldValue() != sudoku.get(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                hashCode += board.get(i).get(j).hashCode();
            }
        }
        return hashCode;
        //return Arrays.hashCode(board);
    }

    public final String toString() {
        String sout = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sout += board.get(i).get(j).getFieldValue() + " ";
            }
            sout += "\n";
        }
        return sout;
    }

    public int get(int x, int y) {
        return board.get(x).get(y).getFieldValue();
    }

    public void set(int x, int y, int value) {
        board.get(x).get(y).setFieldValue(value);
    }

    public SudokuRow getRow(int x) {
        return new SudokuRow(board.get(x));
    }

    public SudokuColumn getColumn(int y) {
        List<SudokuField> pom = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            pom.set(i, board.get(i).get(y));
        }
        return new SudokuColumn(pom);
    }

    public SudokuBox getBox(int x, int y) {
        //SudokuField[] pom = new SudokuField[9];
        List<SudokuField> pom = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            pom.set(i, board.get(i / 3 + x * 3).get(i % 3 + y * 3));
        }
        return new SudokuBox(pom);
    }
}

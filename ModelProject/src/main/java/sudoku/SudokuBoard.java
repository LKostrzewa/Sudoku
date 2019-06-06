package sudoku;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


@DatabaseTable(tableName = "SudokuBoards")
public class SudokuBoard implements Serializable, Cloneable {

    //private SudokuField[][] board = new SudokuField[9][9];
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private List<List<SudokuField>> board;
    public static final int BOARD_SIZE = 9;

    public SudokuBoard() {

        board = Arrays.asList(new List[BOARD_SIZE]);

        for (int i = 0; i < BOARD_SIZE; i++) {
            this.board.set(i, Arrays.asList(new SudokuField[BOARD_SIZE]));
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board.get(i).set(j, new SudokuField(0));
            }
        }
    }

    @Override
    public final SudokuBoard clone() /*throws CloneNotSupportedException*/ {
        //board = Arrays.asList(new List[9]);
        SudokuBoard sudoku = new SudokuBoard();

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sudoku.set(i, j, get(i, j));
            }
        }
        return sudoku;
        //return super.clone();
    }

    /*public SudokuBoard(final SudokuBoard sudoku) {
        board = Arrays.asList(new List[9]);

        for (int i = 0; i < 9; i++) {
            this.board.set(i, Arrays.asList(new SudokuField[9]));
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board.get(i).set(j, new SudokuField(sudoku.get(i, j)));
            }
        }
    }*/

    public final boolean checkBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (!getRow(i).verify()) {
                return false;
            }
            if (!getColumn(i).verify()) {
                return false;
            }
        }
        for (int i = 0; i < SudokuBox.ELEMENT_SIZE; i++) {
            for (int j = 0; j < SudokuBox.ELEMENT_SIZE; j++) {
                if (!getBox(i, j).verify()) {
                    return false;
                }
            }
        }
        return true;
    }

    /*public final boolean equals(final SudokuBoard sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i).get(j).getFieldValue() != sudoku.get(i, j)) {
                    return false;
                }
            }
        }
        return true;
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

    public int hashCode() {
        int hashCode = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                hashCode += board.get(i).get(j).hashCode();
            }
        }
        return hashCode;
        //return Arrays.hashCode(board);
    }*/

    @Override
    public final int hashCode() {
        return Objects.hashCode(board);
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SudokuBoard that = (SudokuBoard) o;
        return Objects.equal(board, that.board);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this)
                .add("board", board)
                .toString();
    }

    public final int get(final int x, final int y) {
        return board.get(x).get(y).getFieldValue();
    }

    public final void set(final int x, final int y, final int value) {
        board.get(x).get(y).setFieldValue(value);
    }

    public final SudokuRow getRow(final int x) {
        return new SudokuRow(board.get(x));
    }

    public final SudokuColumn getColumn(final int y) {
        List<SudokuField> pom = Arrays.asList(new SudokuField[BOARD_SIZE]);
        for (int i = 0; i < BOARD_SIZE; i++) {
            pom.set(i, board.get(i).get(y));
        }
        return new SudokuColumn(pom);
    }

    public final SudokuBox getBox(final int x, final int y) {
        //SudokuField[] pom = new SudokuField[9];
        int elSize = SudokuBox.ELEMENT_SIZE;
        List<SudokuField> pom = Arrays.asList(new SudokuField[BOARD_SIZE]);
        for (int i = 0; i < BOARD_SIZE; i++) {
            pom.set(i, board.get(i / elSize + x * elSize)
                    .get(i % elSize + y * elSize));
        }
        return new SudokuBox(pom);
    }


    public final int getId() {
        return this.id;
    }
}

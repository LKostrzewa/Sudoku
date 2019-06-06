package sudoku;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class SudokuDBManager {

    public static final String ACCOUNT_ID_FIELD_NAME = "sudokuBoard_id";

    @DatabaseField(id = true)
    private String name;
    @DatabaseField(foreign = true, columnName = ACCOUNT_ID_FIELD_NAME)
    SudokuBoard sudokuBoard;

    SudokuDBManager() {
    }

    SudokuDBManager(String name, SudokuBoard sudokuBoard) {
        this.name = name;
        this.sudokuBoard = sudokuBoard;
    }

    public String getName() {
        return name;
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }
}
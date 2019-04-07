package sudoku;

import java.util.Arrays;
import java.util.List;


public class SudokuBoard {


    //private int[][] board = new int[9][9];
    //private SudokuField[][] board = new SudokuField[9][9];
    private List<List<SudokuField>> board;

    /*public SudokuBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuField el = new SudokuField(0);
                board[i][j] = el;
            }
        }
    }*/

    public SudokuBoard() {

        board = Arrays.asList(new List[9]);

        for (int i = 0; i < 9; i++) {
            this.board.set(i, Arrays.asList(new SudokuField[9]));
        }

        //Teraz konieczne wpisanie 0
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //this.board.get(i).set(j,new SudokuField(0));
                board.get(i).set(j, new SudokuField(0));
            }
        }
    }

    public final boolean checkBoard() {
        /*ArrayList<Integer> testArray = new ArrayList<Integer>(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        ArrayList<Integer> checkingArray = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                checkingArray.add(get(i, j));
            }
            Collections.sort(checkingArray);
            if (!checkingArray.equals(testArray)) {
                return false;
            }
            checkingArray.clear();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                checkingArray.add(get(j, i));
            }
            Collections.sort(checkingArray);
            if (!checkingArray.equals(testArray)) {
                return false;
            }
            checkingArray.clear();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                checkingArray.add(get(j / 3 + i / 3 * 3, j % 3 + i * 3 % 9));
            }
            Collections.sort(checkingArray);
            if (!checkingArray.equals(testArray)) {
                return false;
            }
            checkingArray.clear();
        }
        return true;*/
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
                /*if (board[i][j].getFieldValue() != sudoku.get(i, j)) {
                    return false;
                }*/
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
        List<SudokuField> pom = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            pom.set(i, new SudokuField(board.get(x).get(i).getFieldValue()));
        }
        return new SudokuRow(pom);
        // Czy tak może być?
    }

    public SudokuColumn getColumn(int y) {
        List<SudokuField> pom = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            pom.set(i, new SudokuField(board.get(i).get(y).getFieldValue()));
        }
        return new SudokuColumn(pom);
    }

    public SudokuBox getBox(int x, int y) {
        //SudokuField[] pom = new SudokuField[9];
        List<SudokuField> pom = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {
            pom.set(i, new SudokuField(board.get(i / 3 + x * 3).get(i % 3 + y * 3).getFieldValue()));
        }
        return new SudokuBox(pom);
    }
}

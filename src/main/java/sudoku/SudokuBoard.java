package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Klasa Sudoku.
 */
public class SudokuBoard {

    /**
     * Dwuwymiarowa tablica przechowująca planszę do gry w sudoku.
     */
    private int[][] board = new int[9][9];

    /**
     * Funkcja sprawdzająca czy zawartość tablicy 'board'
     * jest poprawna z zasadami gry w sudoku.
     *
     * @return true jeżeli tablica jest poprawna. W przeciwnym przypadku false.
     */
    public final boolean checkBoard() {
        ArrayList<Integer> testArray = new ArrayList<Integer>(
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
        return true;
    }

    /**
     * Nadpisana metoda equals. Porównuje zawartość
     * dwóch tablic sudoku.
     *
     * @param sudoku Tablica do której porównujemy.
     * @return True jeżeli obie tablice mają taka samą zawartość.
     */
    public final boolean equals(final SudokuBoard sudoku) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != sudoku.get(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public final int hashCode() {
        return 1;
    }

    /**
     * @return Zwraca string z zawartością tablicy sudoku.
     */
    public final String toString() {
        String sout = "";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sout += board[i][j] + " ";
            }
            sout += "\n";
        }
        return sout;
    }

    /**
     * getter
     *
     * @param x Kolumna
     * @param y Rząd
     * @return Wartosc z tablicy na pozycji (x,y).
     */
    public int get(int x, int y) {
        return board[x][y];
    }


    /**
     * setter
     *
     * @param x     Kolumna
     * @param y     Rząd
     * @param value Wartość do wpisania.
     */
    public void set(int x, int y, int value) {
        board[x][y] = value;
    }
}

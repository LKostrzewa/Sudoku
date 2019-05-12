package sudoku;

import java.io.*;
import java.util.Scanner;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String path;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;

    FileSudokuBoardDao(final String path) {
        this.path = path;
    }

    public SudokuBoard read() {
        SudokuBoard sudoku = new SudokuBoard();
        try {
            this.inputStream = new ObjectInputStream(new FileInputStream(path));
            sudoku = (SudokuBoard) inputStream.readObject();
        } catch (IOException e) {
            System.out.println("Nie znaleziono pliku!");
        } catch (ClassNotFoundException e) {
            System.out.println("Szukana klasa nie istnieje");
        }
        return sudoku;
    }

    public void write(final SudokuBoard obj) {
        //try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))){
        try {
            this.outputStream = new ObjectOutputStream(new FileOutputStream(path));
            outputStream.writeObject(obj);
        } catch (NotSerializableException e) {
            System.out.println("Dany obiekt nie jest instancja Serializable");
        } catch (IOException e) {
            System.out.println("Nie znaleziono pliku");
        }
    }

    public void close() {
        System.out.println("Zamknieto plik");
    }


    //To nw czy tak ma wygladac czy jak
    protected void finalize() throws Throwable {
        try {
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Problem z zamknieciem pliku");
        }
    }
}

package sudoku;

import java.io.*;
import java.util.Scanner;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    private String path;

    // Jeżeli chcemy finalize to chyba musi to być => wyklucza nam to wykorzystanie AutoCloseable
    // w ObjectInputStream oraz ObjectOutputStream :)
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;

    FileSudokuBoardDao(final String path) {
        this.path = path;
    }

    public SudokuBoard read() {
        SudokuBoard sudoku = new SudokuBoard();
        /*try {
            Scanner scanner = new Scanner(new File(path));
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sudoku.set(i, j, scanner.nextInt());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku");
        }

        return sudoku;*/

        //try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path))) {
        try {
            this.inputStream = new ObjectInputStream(new FileInputStream(path));
            sudoku = (SudokuBoard)inputStream.readObject();
        }
        catch (IOException e) {
            System.out.println("Nie znaleziono pliku!");
        }
        catch (ClassNotFoundException e){
            System.out.println("Szukana klasa nie istnieje");
        }
        return sudoku;
    }

    public void write(final SudokuBoard obj) {
        /*try (BufferedWriter outputWriter = new BufferedWriter(new FileWriter(path))) {

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    outputWriter.write((obj.get(i, j)) + " ");
                }
                outputWriter.newLine();
            }
            //outputWriter.close(); //to chyba musi byc w innej klasie
            //close();
        } catch (IOException e) {
            System.out.println("Nie znaleziono pliku");
        }*/
        //try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(path))){
        try {
            this.outputStream = new ObjectOutputStream(new FileOutputStream(path));
            outputStream.writeObject(obj);
        } catch (NotSerializableException e) {
            System.out.println("Nie znaleziono plikuXDD");
        } catch (IOException e) {
            System.out.println("Nie znaleziono pliku123");
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

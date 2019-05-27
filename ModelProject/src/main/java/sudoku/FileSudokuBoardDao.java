package sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Scanner;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);
    private String path;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    FileSudokuBoardDao(final String path) {
        this.path = path;
    }

    public SudokuBoard read() {
        SudokuBoard sudoku = new SudokuBoard();
        try {
            inputStream = new ObjectInputStream(new FileInputStream(path));
            sudoku = (SudokuBoard) inputStream.readObject();
        } catch (IOException e) {
            //System.out.println("Nie znaleziono pliku!");
            logger.error("Nie znaleziono pliku!");
        } catch (ClassNotFoundException e) {
            //System.out.println("Szukana klasa nie istnieje");
            logger.error("Szukana klasa nie istnieje!");
        }
        return sudoku;
    }

    public void write(final SudokuBoard obj) {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(path));
            outputStream.writeObject(obj);
        } catch (NotSerializableException e) {
            //System.out.println("Dany obiekt nie jest instancja Serializable");
            logger.error("Dany obiekt nie jest instancja Serializable");
        } catch (IOException e) {
            //System.out.println("Nie znaleziono pliku");
            logger.error("Nie znaleziono pliku");
        }
    }

    public void close() {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException e) {
            //System.out.println("Problem z zamknieciem pliku");
            logger.error("Problem z zamknięciem pliku");
        }
        //System.out.println("Zamknieto plik");
        logger.error("Zamknieto plik poprawnie");
    }


    //To nw czy tak ma wygladac czy jak
    protected void finalize() /*throws Throwable*/ {
        close();
       /* try {
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Problem z zamknieciem pliku");
        }*/
    }
}

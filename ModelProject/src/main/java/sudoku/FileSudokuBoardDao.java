package sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);
    private String path;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    FileSudokuBoardDao(final String filepath) {
        this.path = filepath;
    }

    public final SudokuBoard read() {
        SudokuBoard sudoku = new SudokuBoard();
        try {
            inputStream = new ObjectInputStream(new FileInputStream(path));
            sudoku = (SudokuBoard) inputStream.readObject();
        }
        //jedyny moj pomysl jstm otwarty na zmiany xd
        catch (IOException e) {
            try {
                throw new FileExeption("Nie znaleziono pliku", e);
            } catch (FileExeption er) {
                //System.out.println("Nie znaleziono pliku!");
                logger.error("Złapano : " + er);
                logger.error("Przyczyna : " + er.getCause());
                //moze tutaj to jakos w jednym loggerze ?? ja to nwm jak iksde
                logger.error(er.getMessage());
            }
        } catch (ClassNotFoundException e) {
            //System.out.println("Szukana klasa nie istnieje");
            logger.error("Szukana klasa nie istnieje!");
        }
        return sudoku;
    }

    public final void write(final SudokuBoard obj) {
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

    public final void close() {
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
        logger.info("Zamknieto plik poprawnie");
    }


    //To nw czy tak ma wygladac czy jak
    protected final void finalize() /*throws Throwable*/ {
        close();
       /* try {
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Problem z zamknieciem pliku");
        }*/
    }
}

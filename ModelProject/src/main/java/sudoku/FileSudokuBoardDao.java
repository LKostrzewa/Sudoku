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
        //jedyny moj pomysl jstm otwarty na zmiany xd
        SudokuBoard sudoku = new SudokuBoard();
        try {
            inputStream = new ObjectInputStream(new FileInputStream(path));
            sudoku = (SudokuBoard) inputStream.readObject();
        } catch (IOException e) {
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
            try {
                throw new FileExeption("Szukana klasa nie istnieje!");
            } catch (FileExeption er) {
                logger.error("Złapano : " + er);
                logger.error("Przyczyna : " + er.getCause());
                logger.error(er.getMessage());
            }
        }
        return sudoku;
    }

    public final void write(final SudokuBoard obj) {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(path));
            outputStream.writeObject(obj);
        } catch (NotSerializableException e) {
            try {
                throw new FileExeption(
                        "Dany obiekt nie jest instancja Serializable");
            } catch (FileExeption er) {
                logger.error("Złapano : " + er);
                logger.error("Przyczyna : " + er.getCause());
                logger.error(er.getMessage());
            }
        } catch (IOException e) {
            try {
                throw new FileExeption("Nie znaleziono pliku");
            } catch (FileExeption er) {
                logger.error("Złapano : " + er);
                logger.error("Przyczyna : " + er.getCause());
                logger.error(er.getMessage());
            }
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
            try {
                throw new FileExeption("Problem z zamknięciem pliku");
            } catch (FileExeption er) {
                logger.error("Złapano : " + er);
                logger.error("Przyczyna : " + er.getCause());
                logger.error(er.getMessage());
            }
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

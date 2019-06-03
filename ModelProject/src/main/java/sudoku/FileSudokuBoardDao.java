package sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.util.Locale;
import java.util.ResourceBundle;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);
    private String path;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private ResourceBundle resourceBundle;

    FileSudokuBoardDao(final String filepath) {
        this.path = filepath;
        resourceBundle = ResourceBundle.getBundle("bundles.Exeptions");

        //System.out.println(Locale.getDefault());
    }

    public final SudokuBoard read() {
        //jedyny moj pomysl jstm otwarty na zmiany xd
        SudokuBoard sudoku = new SudokuBoard();
        try {
            inputStream = new ObjectInputStream(new FileInputStream(path));
            sudoku = (SudokuBoard) inputStream.readObject();
        } catch (IOException e) {
            try {
                throw new FileExeption(resourceBundle.getString("fileNotFound"), e);
            } catch (FileExeption er) {
                //System.out.println("Nie znaleziono pliku!");
                logger.error(resourceBundle.getString("caught") + er);
                logger.error(resourceBundle.getString("cause") + er.getCause());
                logger.error(er.getMessage());
                //moze tutaj to jakos w jednym loggerze ?? ja to nwm jak iksde
            }
        } catch (ClassNotFoundException e) {
            try {
                throw new FileExeption(resourceBundle.getString("classNotExsist"));
            } catch (FileExeption er) {
                logger.error(resourceBundle.getString("caught") + er);
                logger.error(resourceBundle.getString("cause") + er.getCause());
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
                        resourceBundle.getString("notSerr"));
            } catch (FileExeption er) {
                logger.error(resourceBundle.getString("caught") + er);
                logger.error(resourceBundle.getString("cause") + er.getCause());
                logger.error(er.getMessage());
            }
        } catch (IOException e) {
            try {
                throw new FileExeption(resourceBundle.getString("fileNotFound"));
            } catch (FileExeption er) {
                logger.error(resourceBundle.getString("caught") + er);
                logger.error(resourceBundle.getString("cause") + er.getCause());
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
                throw new FileExeption(resourceBundle.getString("fileCloseErr"));
            } catch (FileExeption er) {
                logger.error(resourceBundle.getString("caught") + er);
                logger.error(resourceBundle.getString("cause") + er.getCause());
                logger.error(er.getMessage());
            }
        }
        //System.out.println("Zamknieto plik");
        logger.info(resourceBundle.getString("fileCloseCorr"));
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

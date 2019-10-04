package sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
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

    public final SudokuBoard read() throws FileException {
        //jedyny moj pomysl jstm otwarty na zmiany xd
        SudokuBoard sudoku = new SudokuBoard();
        try {
            inputStream = new ObjectInputStream(new FileInputStream(path));
            sudoku = (SudokuBoard) inputStream.readObject();
        } catch (IOException e) {
                throw new FileException(resourceBundle.
                        getString("fileNotFound"), e);
        } catch (ClassNotFoundException e) {
                throw new FileException(resourceBundle
                        .getString("classNotExsist"));
        }
        return sudoku;
    }

    public final void write(final SudokuBoard obj) throws FileException{
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(path));
            outputStream.writeObject(obj);
        } catch (NotSerializableException e) {
                throw new FileException(
                        resourceBundle.getString("notSerr"));
        } catch (IOException e) {
                throw new FileException(resourceBundle
                        .getString("fileNotFound"));
        }
    }

    public final void close() throws FileException{
        try {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (IOException e) {
            //try {
                throw new FileException(resourceBundle
                        .getString("fileCloseErr"));
           /* } catch (FileException er) {
                logger.error(resourceBundle.getString("caught") + er);
                logger.error(resourceBundle.getString("cause") + er.getCause());
                logger.error(er.getMessage());
            }*/
        }
        //System.out.println("Zamknieto plik");
        logger.info(resourceBundle.getString("fileCloseCorr"));
    }


    //To nw czy tak ma wygladac czy jak
    protected final void finalize() /*throws Throwable*/ {
        //close();
       /* try {
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Problem z zamknieciem pliku");
        }*/
    }
}

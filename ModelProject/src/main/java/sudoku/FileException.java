package sudoku;

public class FileException extends DaoException {
    public FileException(final String message) {
        super(message);
    }

    public FileException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

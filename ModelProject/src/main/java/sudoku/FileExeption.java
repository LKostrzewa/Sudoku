package sudoku;

public class FileExeption extends Exception {
    public FileExeption(final String message) {
        super(message);
    }

    public FileExeption(final String message, final Throwable cause) {
        super(message, cause);
    }
}

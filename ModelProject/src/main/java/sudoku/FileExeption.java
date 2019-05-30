package sudoku;

public class FileExeption extends Exception {
    public FileExeption(String message) {
        super(message);
    }

    public FileExeption(String message, Throwable cause) {
        super(message, cause);
    }
}

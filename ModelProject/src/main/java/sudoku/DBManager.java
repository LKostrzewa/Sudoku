package sudoku;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.IOException;
import java.sql.SQLException;

public class DBManager implements AutoCloseable {


    private static final String JDBC_DRIVER_HD = "jdbc:h2:./libraryDB";
    private static final String USER = "root";
    private static final String PASS = "admin";


    private static ConnectionSource connectionSource;

    public static void initDatabase() {
        createConnectionSource();
        dropTable();
        createTable();
        closeConnectionSource();

    }

    private static void createConnectionSource() {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD, USER, PASS);
        } catch (SQLException e) {
        }
    }

    public static ConnectionSource getConnectionSource() {
        if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException e) {
            }
        }
    }

    private static void createTable() {
        try {
            //TableUtils.createTableIfNotExists(connectionSource, )
            TableUtils.createTableIfNotExists(connectionSource, SudokuBoard.class);
        } catch (SQLException e) {
        }

    }

    private static void dropTable() {
        try {
            TableUtils.dropTable(connectionSource, SudokuBoard.class, true);

        } catch (SQLException e) {
        }

    }

    @Override
    public void close() throws Exception {
        if (connectionSource != null) {
            connectionSource.close();
        }
    }

    protected final void finalize() {
        try {
            close();
        } catch (Exception e) {
        }
    }

}

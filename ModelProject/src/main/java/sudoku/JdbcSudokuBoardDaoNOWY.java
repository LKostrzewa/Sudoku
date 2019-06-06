package sudoku;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

// TODO stworzyc klase ktor bedzie miala obiekt SudokuBoard oraz nazwe.

public class JdbcSudokuBoardDaoNOWY implements sudoku.Dao<SudokuBoard>, AutoCloseable {

    //private final static String DATABASE_URL = "org.apache.derby.jdbc.EmbeddedDriver";
    private final static String DATABASE_URL = "jdbc:derby:SudokuDB;create=true";

    private String name;

    private Dao<SudokuBoard, Integer> sudokuBoardsDao;
    private Dao<SudokuDBManager, String> sudokuDBManagersDao;

    private ConnectionSource connectionSource = null;

    public JdbcSudokuBoardDaoNOWY(String name) throws Exception {
        /*try {
            // create our data-source for the database
            connectionSource = new JdbcConnectionSource(DATABASE_URL);
            // setup our database and DAOs
            setupDatabase(connectionSource);
            // read and write some data
            readWriteData();
            // do a bunch of bulk operations
            readWriteBunch();
            // show how to use the SelectArg object
            useSelectArgFeature();
            // show how to use the SelectArg object
            useTransactions(connectionSource);
            System.out.println("\n\nIt seems to have worked\n\n");
        } finally {
            // destroy the data source which should close underlying connections
            if (connectionSource != null) {
                connectionSource.close();
            }
        }*/
        this.name = name;
        connectionSource = new JdbcConnectionSource(DATABASE_URL);
        setupDatabase(connectionSource);
    }

    public void setupDatabase(ConnectionSource connectionSource) throws Exception {
        sudokuBoardsDao = DaoManager.createDao(connectionSource, SudokuBoard.class);
        System.out.println("GIT");
        sudokuDBManagersDao = DaoManager.createDao(connectionSource, SudokuDBManager.class);

        TableUtils.createTableIfNotExists(connectionSource, SudokuBoard.class);
        TableUtils.createTableIfNotExists(connectionSource, SudokuDBManager.class);
    }

    @Override
    public SudokuBoard read() {
        QueryBuilder<SudokuDBManager, String> statementBuilder = sudokuDBManagersDao.queryBuilder();
        try {
            List<SudokuDBManager> sudokuDBManager = sudokuDBManagersDao.queryForAll();
            statementBuilder.where().like(SudokuDBManager.ACCOUNT_ID_FIELD_NAME, name);
            sudokuDBManager = sudokuDBManagersDao.query(statementBuilder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(sudokuDBManagersDao);
        return null;
    }

    @Override
    public void write(SudokuBoard obj) {
        try {
            System.out.println(sudokuBoardsDao);
            sudokuBoardsDao.create(obj);
            SudokuDBManager sudokuDBManager = new SudokuDBManager(name, obj);
            sudokuDBManagersDao.create(sudokuDBManager);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws Exception {
        if (connectionSource != null) {
            connectionSource.close();
        }
    }
}

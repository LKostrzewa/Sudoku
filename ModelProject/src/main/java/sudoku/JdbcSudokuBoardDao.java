package sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.logging.Level;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    String name;
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);

    public JdbcSudokuBoardDao(final String name) {
        this.name = name;

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            logger.error("Nie znaleziono klasy odpowiedzialnej za baze danych:\n" + e);
        }

        try {
            con = DriverManager.getConnection("jdbc:sqlite:SudokuBoars.db");
            st = con.createStatement();
        } catch (SQLException e) {
            logger.error("Wyjatek sql :\n " + e);
        }

        try {
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS BOARDS(" +
                            "name VARCHAR(30) PRIMARY KEY)");
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS FIELDS(" +
                            "row INT," +
                            "col INT," +
                            "VALUE INT, " +
                            "id_board VARCHAR(30)," +
                            "FOREIGN KEY (id_board) REFERENCES BOARDS(name)," +
                            "PRIMARY KEY (id_board, row, col))");
        } catch (SQLException e) {
            logger.error("Wyjatek sql :\n " + e);
        }
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        try {
            //con = DriverManager.getConnection("jdbc:sqlite:SudokuBoars.db");
            //st = con.createStatement();
            for(int i = 0; i < SudokuBoard.BOARD_SIZE; i++) {
                for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
                    String getField = "SELECT value FROM Fields " +
                            "WHERE row=\'" + i + "\' AND col=\'" + j + "\' AND id_board=\'" + name + "\';";
                    ResultSet resultSet = st.executeQuery(getField);
                    int val = resultSet.getInt("value");
                    sudokuBoard.set(i,j,val);
                }
            }
        } catch (SQLException e){
            logger.error("Problem z odczytem z bazy danych:\n " + e);
        }
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard obj) {
        try {
            String insertBrd = "INSERT INTO BOARDS VALUES(\'" + name + "\')";
            st.execute(insertBrd);
            for(int i = 0; i < SudokuBoard.BOARD_SIZE; i++){
                for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++){
                    String insertField = "INSERT INTO FIELDS VALUES(\'"+ i + "\',\'" + j + "\',\'" + obj.get(i,j) + "\', \'" + name + "\')";
                    st.execute(insertField);
                }
            }
        } catch (SQLException e) {
            logger.error("Problem z zapisem do bazy danych:\n " + e);
        }
    }

    public void delete() {
        try {
            String insertBrd = "DELETE FROM BOARDS WHERE name=\'" + name + "\';";
            st.execute(insertBrd);
            insertBrd = "DELETE FROM FIELDS WHERE id_board=\'" + name + "\';";
            st.execute(insertBrd);
        } catch (SQLException e) {
            logger.error("Problem z czysczeniem do bazy danych:\n " + e);
            //Throwable throwable = new FileExeption("Problem z czysczeniem do bazy danych:\n ", e);
            //throwable.initCause(e);
            //throw throwable;
        }
    }

    @Override
    public void close() {
        try {
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            logger.error("Zamknięcie bazy danych nie powiodło się: " + e);
        }
    }

    protected final void finalize() throws Exception {
        close();
    }

}

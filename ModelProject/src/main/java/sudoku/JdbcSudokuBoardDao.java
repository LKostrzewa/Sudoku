package sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.logging.Level;


// Na razie tu jest bajzel i to nie zadziała


public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    String name;
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);

    //String url = "jdbc:derby:testdb;user=USER12";

    public JdbcSudokuBoardDao(final String name) {
        this.name = name;

        try {
            Class.forName("org.sqlite.JDBC");
            //Connection con = DriverManager.getConnection("jdbc:derby:testdb;user=Romek");
           // Connection con = DriverManager.getConnection("jdbc:derby:SudokuBoardsDB");
            //st = con.createStatement();
        } catch (ClassNotFoundException e) {
            logger.error("Nie znaleziono klasy odpowiedzialnej za baze danych:\n" + e);
        /*} catch (IllegalAccessException er) {
            //Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);
            logger.error("Brak dostepu:\n" + er);
        } catch (InstantiationException err) {
            //Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);
            logger.error("Instcostam error:\n" + err);*/
        }

        try {
            con = DriverManager.getConnection("jdbc:sqlite:SudokuBoars.db");
            st = con.createStatement();
        } catch (SQLException e) {
            logger.error("Wyjatek sql :\n " + e);
        }

        try {
            //con = DriverManager.getConnection("jdbc:derby:SudokuBoardsDB");
            //st = con.createStatement();
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS BOARDS(" +
                            "name VARCHAR(30) PRIMARY KEY)");
            //st.executeUpdate("CREATE TABLE IF NOT EXSISTS FIELDS(ID INT PRIMARY KEY, VALUE INT, BOARD_ID VARCHAR(30) FOREIGN KEY REFERENCES BOARDS(ID))");
            st.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS FIELDS(" +
                            "row INT," +
                            "col INT," +
                            "VALUE INT, " +
                            "id_board VARCHAR(30)," +
                            "FOREIGN KEY (id_board) REFERENCES BOARDS(name)," +
                            "PRIMARY KEY (id_board, row, col))");
            //tu na razie tak bo nwm jak to id dziabnac i czy wgl jest potrzebne jak nie musi byc to wdg mnie nie jest
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

        }
        return sudokuBoard;
        /*try {

            //System.setProperty("derby.system.home", "/home/janbodnar/.derby");

            con = DriverManager.getConnection("jdbc:derby:SudokuBoardsDB");
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM USER12.CARS");
            while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(" ");
                System.out.print(rs.getString(2));
                System.out.print(" ");
                System.out.println(rs.getString(3));
            }

            DriverManager.getConnection("jdbc:derby:;shutdown=true");

        } catch (SQLException ex) {
            Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);
            logger.error("No nie udalo sie odczytac :')");
        }
        return null;*/
    }

    @Override
    public void write(SudokuBoard obj) {
        try {
            //System.setProperty("derby.system.home", "/home/janbodnar/.derby");
            //con = DriverManager.getConnection("jdbc:sqlite:SudokuBoars.db");
            //st = con.createStatement();
            //st.executeUpdate("CREATE TABLE BOARDS(ID VARCHAR(30) PRIMARY KEY, NAME VARCHAR(30)");
            //st.executeUpdate("CREATE TABLE FIELDS(ID INT PRIMARY KEY, VALUE INT, BOARD_ID VARCHAR(30) FOREIGN KEY REFERENCES BOARDS(ID))");
            String insertBrd = "INSERT INTO BOARDS VALUES(\'" + name + "\')";
            st.execute(insertBrd);
            for(int i = 0; i < SudokuBoard.BOARD_SIZE; i++){
                for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++){
                    String insertField = "INSERT INTO FIELDS VALUES(\'"+ i + "\',\'" + j + "\',\'" + obj.get(i,j) + "\', \'" + name + "\')";
                    st.execute(insertField);
                }
            }
        } catch (SQLException ex) {

            // Ten catch to nw
            Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);
            if (((ex.getErrorCode() == 50000)
                    && ("XJ015".equals(ex.getSQLState())))) {

                logger.debug(Level.INFO + "Derby shut down normally" + ex);
            } else {
                logger.debug(Level.SEVERE + ex.getMessage() + ex);
            }
        }
    }

    public void delete() {
        try {
            String insertBrd = "DELETE FROM BOARDS WHERE name=\'" + name + "\';";
            st.execute(insertBrd);
            insertBrd = "DELETE FROM FIELDS WHERE id_board=\'" + name + "\';";
            st.execute(insertBrd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
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
            Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);
            logger.error("Zamknięcie bazy danych nie powiodło się: " + e);
        }
    }

    protected final void finalize() throws Exception {
        close();
    }

}

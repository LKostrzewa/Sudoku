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

    //String url = "jdbc:derby:testdb;user=USER12";

    public JdbcSudokuBoardDao(final String name) {
        this.name = name;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Connection con = DriverManager.getConnection("jdbc:derby:testdb;user=Romek");
            Connection con = DriverManager.getConnection("jdbc:derby:SudokuBoardsDB");
            st = con.createStatement();
        } catch (Exception e) {
            Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);
            logger.error("Nie znaleziono klasy odpowiedzialnej za baze danych:\n" + e);
        }
    }

    @Override
    public SudokuBoard read() {

        try {

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
        return null;
    }

    @Override
    public void write(SudokuBoard obj) {
        try {
            //System.setProperty("derby.system.home", "/home/janbodnar/.derby");
            con = DriverManager.getConnection("jdbc:derby:SudokuBoardsDB");
            st = con.createStatement();
            st.executeUpdate("CREATE TABLE BOARDS(ID INT PRIMARY KEY, NAME VARCHAR(30)");
            /*st.executeUpdate("CREATE TABLE CARS(ID INT PRIMARY KEY,"
                    + "NAME VARCHAR(30), PRICE INT)");
            st.executeUpdate("INSERT INTO CARS VALUES(1, 'Audi', 52642)");
            st.executeUpdate("INSERT INTO CARS VALUES(2, 'Mercedes', 57127)");
            st.executeUpdate("INSERT INTO CARS VALUES(3, 'Skoda', 9000)");
            st.executeUpdate("INSERT INTO CARS VALUES(4, 'Volvo', 29000)");
            st.executeUpdate("INSERT INTO CARS VALUES(5, 'Bentley', 350000)");
            st.executeUpdate("INSERT INTO CARS VALUES(6, 'Citroen', 21000)");
            st.executeUpdate("INSERT INTO CARS VALUES(7, 'Hummer', 41400)");
            st.executeUpdate("INSERT INTO CARS VALUES(8, 'Volkswagen', 21600)");
            DriverManager.getConnection("jdbc:derby:;shutdown=true");*/
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

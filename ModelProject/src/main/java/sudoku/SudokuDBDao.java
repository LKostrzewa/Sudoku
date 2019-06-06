//package sudoku;
//
//import com.j256.ormlite.dao.DaoManager;
//import com.j256.ormlite.support.ConnectionSource;
//import org.omg.CORBA.portable.ApplicationException;
//
//import java.io.IOException;
//import java.sql.SQLException;
//
//public class SudokuDBDao {
//    protected final ConnectionSource connectionSource;
//
//    public SudokuDBDao() {
//        this.connectionSource = DBManager.getConnectionSource();
//
//    }
//
//    public void createorUpdate(SudokuBoard sudokuBoard) throws ApplicationException {
//        Dao<SudokuBoard> dao = getDao(sudoku.SudokuBoard.class);
//        try {
//            dao.write(sudokuBoard);
//        } catch (Exception e) {
//        }
//        finally {
//            this.closeDbConnection();
//        }
//    }
//
//    public void delete(SudokuBoard sudokuBoard) throws ApplicationException {
//        Dao<SudokuBoard> dao = getDao(SudokuBoard.class);
//        try {
//            dao.
//        } catch (SQLException e) {
//            LOGGER.warn(e.getMessage());
//            throw new ApplicationException(FXMLutils.getResourceBundle().getString("error.delete"));
//
//        }
//        finally {
//            this.closeDbConnection();
//        }
//    }
//
//    public <T extends SudokuBoard,I> void deleteById(Class<T>cls, Integer id) throws ApplicationException {
//        Dao<T,I>dao = getDao(cls);
//        try {
//            dao.deleteById((I)id);
//        } catch (SQLException e) {
//            LOGGER.warn(e.getMessage());
//            throw new ApplicationException(FXMLutils.getResourceBundle().getString("error.delete"));
//
//        }
//        finally {
//            this.closeDbConnection();
//        }
//    }
//
//    public <T extends SudokuBoard,I> T findById(Class<T>cls, Integer id) throws ApplicationException {
//        Dao<T,I>dao = getDao(cls);
//        try {
//            return dao.queryForId((I)id);
//        } catch (SQLException e) {
//            LOGGER.warn(e.getMessage());
//            throw new ApplicationException(FXMLutils.getResourceBundle().getString("error.not.found"));
//
//        }
//        finally {
//            this.closeDbConnection();
//        }
//    }
//
//    public > List<T> queryForAll(Class<T> cls) throws ApplicationException {
//        try {
//            Dao<T, I> dao = getDao(cls);
//            return dao.queryForAll();
//        } catch (SQLException e) {
//            LOGGER.warn(e.getMessage());
//            throw new ApplicationException(FXMLutils.getResourceBundle().getString("error.not.found.all"));
//
//        }
//        finally {
//            this.closeDbConnection();
//        }
//    }
//
//
//    public <T extends SudokuBoard, I> Dao<T, I> getDao(Class<T> cls) throws ApplicationException {
//        try {
//            return DaoManager.createDao(connectionSource, cls);
//        } catch (SQLException e) {
//        }
//        finally {
//            this.closeDbConnection();
//        }
//    }
//
//    private void closeDbConnection() {
//        try {
//            this.connectionSource.close();
//        } catch (IOException e) {
//        }
//    }
//}
//}

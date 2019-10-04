package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.*;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import static sudoku.SudokuBoardDaoFactory.getJDBCdao;
import static sudoku.SudokuBoardDaoFactory.getSudokuBoardDaoFactory;

public class BoardController {

    private Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);
    private MainController mainController;
    private SudokuBoard sudokuBoard;
    private ResourceBundle bundle;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.Exeptions");

    @FXML
    private GridPane gridPane;

    @FXML
    private Button checkButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button readButton;

    @FXML
    private Label fileLabel;

    @FXML
    private TextField fileField;

    @FXML
    private Button fChooBtn;

    @FXML
    private Button dbSaveBut;

    @FXML
    private Button dbReadBut;

    @FXML
    private Label dbLabel;

    @FXML
    private TextField dbField;

    @FXML
    public void initialize() {
    }


    public final void prepare(final MainController main) {
        setMainController(main);
        //SudokuBoard sudokuBoard = mainController.getSudokuBoard();
        this.sudokuBoard = main.getSudokuBoard();
        showBoard();
    }

    private void showAlertBox(final String msg) {
        Alert alert;
        if (msg.equals(bundle.getString("ErrorMsg"))) {
            alert = new Alert(Alert.AlertType.ERROR);
        } else if (msg.equals(bundle.getString("WinMsg"))) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        } else {
            alert = new Alert(Alert.AlertType.WARNING);
        }
        alert.setTitle(bundle.getString("InfMsg"));
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }

    private void showBoard() {
        for (int i = 0; i < SudokuBoard.BOARD_SIZE; i++) {
            for (int j = 0; j < SudokuBoard.BOARD_SIZE; j++) {
                TextField textField = new TextField(
                        Integer.toString(sudokuBoard.get(i, j)));
                //TextField textField = new TextField();
                /*textField.setOnMouseClicked(event -> {
                            int col = GridPane.getColumnIndex((Node) (event.getSource()));
                            int row = GridPane.getRowIndex((Node) (event.getSource()));
                            Stream<Node> textFieldStream = gridPane.getChildren().stream()
                                    .filter(node -> GridPane.getColumnIndex(node) == col)
                                    .filter(node -> GridPane.getRowIndex(node) == row);
                            //System.out.println(textFieldStream.count());
                        }
                );*/

                if (textField.getText().equals("0")) {
                    textField.setText("");
                } else {
                    textField.setEditable(false);
                }
                gridPane.add(textField, i, j);
                //gridPane.getStylesheets().add("style.css");
            }
        }
    }

    public final void setMainController(final MainController main) {
        this.mainController = main;
    }

    private boolean fillBoard() {
        List<Node> fields = gridPane.getChildren();
        TextField test;
        int row, col, val;
        for (Node i : fields) {
            test = (TextField) i;
            row = gridPane.getRowIndex(i);
            col = gridPane.getColumnIndex(i);
            try {
                val = Integer.parseInt(test.getText());
            } catch (NumberFormatException e) {
                val = 0; // tutaj nie do konca fancy ale zawsze cos :D
                //System.out.println("Bledny format danych");
            }
            if (val < 1 || val > SudokuBoard.BOARD_SIZE) {
                return false;
            }
            this.sudokuBoard.set(row, col, val);
        }
        return true;
    }

    private void cleanBoard(){
        List<Node> fields = gridPane.getChildren();
        //System.out.println(fields.size());
        for(Node i : fields.subList(1, fields.size()-1)){
            ((TextField) i).clear();
        }
    }

    @FXML
    public final void onActionButton() {
        if (fillBoard()) {
            if (!sudokuBoard.checkBoard()) {
                showAlertBox(bundle.getString("ErrorMsg"));
            } else {
                showAlertBox(bundle.getString("WinMsg"));
            }
        } else {
            showAlertBox(bundle.getString("WrgNrMsg"));
        }
    }

    @FXML
    public final void saveOnAction() {
        fillBoard();
        //System.out.println(Locale.getDefault());
        try (FileSudokuBoardDao files =
                     getSudokuBoardDaoFactory(fileField.getText())) {
            files.write(this.sudokuBoard);
        }
        catch (FileException er){
            logger.error(resourceBundle.getString("caught") + er);
            logger.error(resourceBundle.getString("cause") + er.getCause());
            logger.error(er.getMessage());
        }
        //catch (IOException e ){
        //logger.error("Nie znaleziono pliku do zapisu");
        //}
    }

    @FXML
    public final void readOnAction() {
        try (FileSudokuBoardDao files =
                     getSudokuBoardDaoFactory(fileField.getText())) {
            this.sudokuBoard = files.read();
            cleanBoard();
            showBoard();
        }
        catch (FileException er){
            logger.error(resourceBundle.getString("caught") + er);
            logger.error(resourceBundle.getString("cause") + er.getCause());
            logger.error(er.getMessage());
        }
        //catch (IOException e ){
        //System.out.println("Nie znaleziono pliku123");
        //}
    }

    @FXML
    public final void chooserBtnOnAct(){
        FileChooser fc = new FileChooser();
        File selected = fc.showOpenDialog(null);

        if(selected != null){
            fileField.setText(selected.getAbsolutePath());
        }
    }

    @FXML
    public final void dbSaveAct(){
        fillBoard();
        try (JdbcSudokuBoardDao db = getJDBCdao(dbField.getText())) {
            db.write(this.sudokuBoard);
        }
    }

    @FXML
    public final void dbReadAct(){
        cleanBoard();
        try (JdbcSudokuBoardDao db = getJDBCdao(dbField.getText())) {
            this.sudokuBoard = db.read();
            showBoard();
        }
    }

    public final void setBundle(final ResourceBundle resourceBundle) {
        this.bundle = resourceBundle;
    }
}

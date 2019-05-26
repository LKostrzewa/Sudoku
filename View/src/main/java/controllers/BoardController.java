package controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import org.checkerframework.checker.units.qual.A;
import sudoku.FileSudokuBoardDao;
import sudoku.SudokuBoard;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import static sudoku.SudokuBoardDaoFactory.getSudokuBoardDaoFactory;

public class BoardController {

    private MainController mainController;
    private SudokuBoard sudokuBoard;
    private ResourceBundle bundle;

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
    public void initialize() {
    }


    public void prepare(MainController mainController) {
        setMainController(mainController);
        //SudokuBoard sudokuBoard = mainController.getSudokuBoard();
        this.sudokuBoard = mainController.getSudokuBoard();
        showBoard();
    }

    private void showAlertBox(String msg){
        Alert alert;
        if(msg.equals(bundle.getString("ErrorMsg"))) alert = new Alert(Alert.AlertType.ERROR);
        else if(msg.equals(bundle.getString("WinMsg"))) alert = new Alert(Alert.AlertType.INFORMATION);
        else alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(bundle.getString("InfMsg"));
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }

    private void showBoard(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField(Integer.toString(sudokuBoard.get(i, j)));
                //TextField textField = new TextField();
                if (textField.getText().equals("0")) {
                    textField.setText("");
                }
                else {
                    textField.setEditable(false);
                }
                gridPane.add(textField, i, j);
            }
        }
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private boolean fillBoard(){
        List<Node> fields = gridPane.getChildren();
        TextField test;
        int row, col, val;
        for(Node i : fields) {
            test = (TextField) i;
            row = gridPane.getRowIndex(i);
            col = gridPane.getColumnIndex(i);
            try{
                val = Integer.parseInt(test.getText());
            }
            catch (NumberFormatException e){
                val = 0 ; // tutaj nie do konca fancy ale zawsze cos :D
                //System.out.println("Bledny format danych");
            }
            if (val<1 || val>9) {
                return false;
            }
            this.sudokuBoard.set(row,col,val);
        }
        return true;
    }

    @FXML
    public void onActionButton(){
        if(fillBoard()) {
            if (!sudokuBoard.checkBoard()) {
                showAlertBox(bundle.getString("ErrorMsg"));
            } else {
                showAlertBox(bundle.getString("WinMsg"));
            }
        }
        else showAlertBox(bundle.getString("WrgNrMsg"));
    }

    @FXML
    public void saveOnAction(){
        fillBoard();
        try(FileSudokuBoardDao files = getSudokuBoardDaoFactory(fileField.getText())){
            files.write(this.sudokuBoard);
        }
        catch (IOException e ){
            System.out.println("Nie znaleziono pliku123");
        }
    }

    @FXML
    public void readOnAction(){
        try(FileSudokuBoardDao files = getSudokuBoardDaoFactory("lol.bin")){
            this.sudokuBoard = files.read();
            showBoard();
        }
        catch (IOException e ){
            System.out.println("Nie znaleziono pliku123");
        }
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }
}

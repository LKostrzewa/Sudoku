package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import sudoku.SudokuBoard;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BoardController {

    private MainController mainController;
    private SudokuBoard sudokuBoard;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button checkButton;

    @FXML
    public void initialize() {
    }


    public void prepare(MainController mainController) {
        setMainController(mainController);
        //SudokuBoard sudokuBoard = mainController.getSudokuBoard();
        this.sudokuBoard = mainController.getSudokuBoard();
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

    @FXML
    public void onActionButton(){
        List<Node> fields = gridPane.getChildren();
        //SudokuBoard sudokuBoard = new SudokuBoard();
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
                System.out.println("Bledny format danych");
            }
            this.sudokuBoard.set(row,col,val);
        }
        if(!sudokuBoard.checkBoard()) System.out.println("Podane sudoku nie jest prawidłowe");
        else System.out.println("Wygrales !");
    }
}

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

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BoardController {

    private MainController mainController;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button checkButton;

    @FXML
    public void initialize() {
    }


    public void prepare(MainController mainController) {
        setMainController(mainController);
        SudokuBoard sudokuBoard = mainController.getSudokuBoard();
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
        //TextField txt = check();
        //System.out.println(txt.getText());
    }
    /* tu chcialem robic tak bo chciałem zrobic sprawdzanie kazdego pola na biezaco
    public TextField check(){
        List<Node> fields = gridPane.getChildren();
        TextField kupka = new TextField();
        for(Node i : fields){
            test = (TextField) i;
            if(((TextField) i).getText().equals("")) break;
        }
        return test;
    }*/

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void onActionButton(){

    }
}

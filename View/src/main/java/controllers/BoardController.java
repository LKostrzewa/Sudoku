package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import sudoku.SudokuBoard;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;

public class BoardController {

    private MainController mainController;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button backButton;


    @FXML
    public void initialize() {
        SudokuBoard sudokuBoard = mainController.getSudokuBoard();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //TextField textField = new TextField(Integer.toString(sudokuBoard.get(i,j)));
                TextField textField = new TextField();
                gridPane.add(textField, i, j);
            }
        }
    }

    @FXML
    void clickBackButton() {
        mainController.loadMenuScreen();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}

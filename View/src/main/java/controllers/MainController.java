package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sudoku.SudokuBoard;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainController {

    private SudokuBoard sudokuBoard;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    public void initialize() {
        loadMenuScreen();
    }

    public void loadMenuScreen() {
        //Locale.setDefault(new Locale("en"));
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxmls/MenuScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        loader.setResources(bundle);
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuScreenController menuScreenController = loader.getController();
        menuScreenController.setBundle(bundle);
        menuScreenController.setMainController(this);
        //BoardController boardController = loader.getController();
        //boardController.setMainController(this);
        setScreen(pane);
    }

    public void setScreen(Pane pane) {
        mainAnchorPane.getChildren().clear();
        mainAnchorPane.getChildren().add(pane);
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public void setSudokuBoard(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }
}

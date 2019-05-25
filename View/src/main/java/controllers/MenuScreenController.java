package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import sudoku.*;

import java.io.IOException;
import java.util.Observable;
import java.util.ResourceBundle;

public class MenuScreenController {

    private ResourceBundle bundle;
    @FXML
    private Button play;

    @FXML
    private ChoiceBox<String> choiceBox;

    private MainController mainController;
    //private SudokuBoard sudokuBoard;

    @FXML
    public void initialize(){
        //bundle
        //choiceBox.getItems().addAll(bundle.getString("EasyDif"),bundle.getString("NormalDif"),bundle.getString("HardDif"));
    }

    @FXML
    public void click() {
        //ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxmls/Board.fxml"));
        //loader.setResources(bundle);
        SudokuBoard sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        Difficluty d1;
        if (choiceBox.getValue().equals(bundle.getString("EasyDif"))) d1=Difficluty.EASY;
        else if (choiceBox.getValue().equals(bundle.getString("NormalDif"))) d1=Difficluty.MEDIUM;
        else d1=Difficluty.HARD;
        d1.clean(sudokuBoard);
        mainController.setSudokuBoard(sudokuBoard);

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BoardController boardController = loader.getController();
        boardController.prepare(mainController);
        mainController.setScreen(pane);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    public void setBundle(ResourceBundle bundle) {this.bundle = bundle;}
}

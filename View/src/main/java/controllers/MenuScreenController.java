package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert;
import sudoku.*;

import java.io.IOException;
import java.util.Locale;
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
    private ChoiceBox<?> langChoice;

    @FXML
    private Button langButton;

    @FXML
    public void initialize() {
        //bundle
        //choiceBox.getItems().addAll(bundle.getString("EasyDif"),bundle.getString("NormalDif"),bundle.getString("HardDif"));
    }

    @FXML
    public void langOnAction() {
        if (langChoice.getValue().equals("PL")) {
            Locale.setDefault(new Locale("pl"));
        } else {
            Locale.setDefault(new Locale("en"));
        }
        mainController.initialize();
    }

    @FXML
    public void onActionPlay() {
        //ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxmls/Board.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        loader.setResources(bundle);
        SudokuBoard sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        Difficluty d1;
        if (choiceBox.getValue().equals(bundle.getString("EasyDif"))) {
            d1 = Difficluty.EASY;
        } else if (choiceBox.getValue().equals(bundle.getString("NormalDif"))) {
            d1 = Difficluty.MEDIUM;
        } else {
            d1 = Difficluty.HARD;
        }
        d1.clean(sudokuBoard);
        mainController.setSudokuBoard(sudokuBoard);

        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BoardController boardController = loader.getController();
        boardController.setBundle(bundle);
        boardController.prepare(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public void creditsOnAction() {
        ResourceBundle bundle = ResourceBundle.getBundle("Resource");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(bundle.getString("title"));
        alert.setHeaderText(bundle.getString("authors"));
        alert.showAndWait();
    }

    public void setMainController(final MainController mainController) {
        this.mainController = mainController;
    }

    public void setChoiceBox(final ChoiceBox<String> choiceBox) {
        this.choiceBox.getItems().addAll(choiceBox.getItems());
    }
}

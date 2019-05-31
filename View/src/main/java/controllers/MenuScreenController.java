package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert;
import sudoku.SudokuBoard;
import sudoku.BacktrackingSudokuSolver;
import sudoku.Difficluty;

import java.io.IOException;
import java.util.Locale;
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
    }

    @FXML
    public final void langOnAction() {
        if (langChoice.getValue().equals("PL")) {
            Locale.setDefault(new Locale("pl"));
        } else {
            Locale.setDefault(new Locale("en"));
        }
        mainController.initialize();
    }

    @FXML
    public final void onActionPlay() {
        //ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        FXMLLoader loader = new FXMLLoader(
                this.getClass().getResource("/fxmls/Board.fxml"));
        ResourceBundle resourceBundle =
                ResourceBundle.getBundle("bundles.messages");
        loader.setResources(resourceBundle);
        SudokuBoard sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        Difficluty d1;
        if (choiceBox.getValue().equals(resourceBundle.getString("EasyDif"))) {
            d1 = Difficluty.EASY;
        } else if (choiceBox.getValue()
                .equals(resourceBundle.getString("NormalDif"))) {
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
        boardController.setBundle(resourceBundle);
        boardController.prepare(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public final void creditsOnAction() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Resource");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(resourceBundle.getString("title"));
        alert.setHeaderText(resourceBundle.getString("authors"));
        alert.showAndWait();
    }

    public final void setMainController(final MainController main) {
        this.mainController = main;
    }

    public final void setChoiceBox(final ChoiceBox<String> box) {
        this.choiceBox.getItems().addAll(box.getItems());
    }
}

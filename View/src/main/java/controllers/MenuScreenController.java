package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import sudoku.*;

import java.io.IOException;

public class MenuScreenController {

    @FXML
    private Button play;

    @FXML
    private ChoiceBox<?> choiceBox;

    private MainController mainController;
    //private SudokuBoard sudokuBoard;

    @FXML
    public void click() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudokuBoard);
        Difficluty d1;
        System.out.println(choiceBox.getValue());
        if (choiceBox.getValue().equals("Łatwy")) d1=Difficluty.EASY;
        else if (choiceBox.getValue().equals("Normalny")) d1=Difficluty.MEDIUM;
        else d1=Difficluty.HARD;
        d1.clean(sudokuBoard);
        mainController.setSudokuBoard(sudokuBoard);

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxmls/Board.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BoardController boardController = loader.getController();
        boardController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}

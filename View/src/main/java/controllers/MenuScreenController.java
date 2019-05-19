package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import sudoku.*;
import java.awt.event.ActionEvent;

public class MenuScreenController {

    @FXML
    private Button play;

    @FXML
    private ChoiceBox<?> choiceBox;

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
    }

}

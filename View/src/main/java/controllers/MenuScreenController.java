package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.awt.event.ActionEvent;

public class MenuScreenController {

    @FXML
    private Button play;

    @FXML
    private ChoiceBox<?> choiceBox;

    @FXML
    public void click() {
        System.out.println("No elo");
        System.out.println(choiceBox.getValue());
    }

}

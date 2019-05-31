package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxmls/MainScene.fxml"));
        //StackPane pane = loader.load();
        AnchorPane pane = loader.load();
        Scene scene = new Scene(pane);
        //Stage stage = new Stage();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sudoku");
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}

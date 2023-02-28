package org.example;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

import java.util.*;

/**
 * @author ${USER}
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/interfaces/StudentView.fxml")));
        primaryStage.setTitle("Tuto app");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

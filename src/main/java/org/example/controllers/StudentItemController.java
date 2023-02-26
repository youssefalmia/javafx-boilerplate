package org.example.controllers;

import javafx.fxml.*;
import javafx.scene.image.*;
import javafx.scene.text.*;

import java.net.*;
import java.util.*;

/**
 * @author Jozef
 */
public class StudentItemController implements Initializable {

    @FXML
    private ImageView profilPic;
    @FXML
    private Text Name;
    @FXML
    private Text Age;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
    }
}

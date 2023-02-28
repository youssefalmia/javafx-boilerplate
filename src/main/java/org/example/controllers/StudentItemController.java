package org.example.controllers;

import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import org.example.model.*;

import java.net.*;
import java.util.*;

/**
 * @author Jozef
 */
public class StudentItemController implements Initializable {

    @FXML
    private ImageView profilPic;
    @FXML
    private Text name;
    @FXML
    private Text age;
    @FXML
    private Text idText;
    @FXML
    private Button deleteBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set a mouse click event handler for the delete button
        deleteBtn.setOnMouseClicked(event -> {});
    }

    // So that when we add a student from StudentController, we can set its data in here
    public void setData(Student student) {

    }


}

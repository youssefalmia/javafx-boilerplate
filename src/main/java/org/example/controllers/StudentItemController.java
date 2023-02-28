package org.example.controllers;

import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import org.example.model.*;

import java.io.*;
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
    @FXML
    private Text idText;
    @FXML
    private Button deleteBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");

        // Set a mouse click event handler for the delete button
        deleteBtn.setOnMouseClicked(event -> {
            // Retrieve the border pane that contains the delete button, using a series of getParent() calls
            BorderPane borderPane = (BorderPane) ((HBox) ((VBox) ((HBox) ((Button) event.getSource()).getParent()).getParent()).getParent()).getParent();

            // Get the hash code of the border pane, which will be used to identify it
            int hashCode = borderPane.hashCode();

            // Retrieve the text node in the top of the border pane, which contains the student ID
            Text topText = (Text) borderPane.getTop();
            int studentId = Integer.parseInt(topText.getText());

            // Call the deleteNode() method of the StudentController singleton instance, passing in the hash code and student ID
            StudentController.getInstance().deleteNode(hashCode, studentId);
        });
    }

    public void setData(Student student) {
        this.Name.setText(student.getName());
        this.Age.setText(String.valueOf(student.getAge()));
        this.idText.setText(String.valueOf(student.getId()));
        if (student.getProfilePicUrl() != null) {
            this.profilPic.setImage(new Image(student.getProfilePicUrl()));
        }
    }


}

package org.example.controllers;

import javafx.fxml.*;
import javafx.scene.image.*;
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
    private Text Name;
    @FXML
    private Text Age;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
    }

    public void setData(Student student) {
        this.Name.setText(student.getName());
        this.Age.setText(String.valueOf(student.getAge()));
        this.profilPic.setImage(new Image(student.getProfilePicUrl()));
    }
}

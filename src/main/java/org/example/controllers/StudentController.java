package org.example.controllers;

import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import org.example.dao.*;
import org.example.model.*;
import org.example.utils.*;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Jozef
 */
public class StudentController implements Initializable{
    @FXML
    Button button;
    @FXML
    TextField nameField;
    @FXML
    TextField ageField;
    @FXML
    Text errorText;
    @FXML
    Text prof;
    @FXML
    BorderPane borderPane;
    IStudentDao studentDao = new StudentDao();

    Stage stage = new Stage();

    final FileChooser fileChooser = new FileChooser();

    @FXML
    public void addStudent(){
        errorText.setText("");
        if(nameField.getText().isEmpty() || ageField.getText().isEmpty()){
            errorText.setText("Name and age are required");
            return;
        }
        Student student = new Student();
        student.setName(nameField.getText());
        student.setAge(Integer.parseInt(ageField.getText()));
        student.setProfilePicUrl(UploadAPI.UPLOAD_PATH+prof.getText());
        student = studentDao.add(student);
        System.out.println(student);
    }

    public void findById(){
        System.out.println("jaw");
    }

    public void browseFiles(){
        File file = fileChooser.showOpenDialog(null);//stage);
        if (file != null){
            try {
                String newName = UploadAPI.upload(file);
                Image image = new Image(new FileInputStream(file));
                prof.setText(newName);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../../../interfaces/StudentItem.fxml")));
            this.borderPane.setCenter(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
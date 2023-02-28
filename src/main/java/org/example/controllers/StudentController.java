package org.example.controllers;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
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
public class StudentController implements Initializable {
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
    @FXML
    VBox personVbox;
    @FXML
    HBox hBoxScroll;
    @FXML
    ScrollPane scroll;
    IStudentDao studentDao = new StudentDao();
    @FXML
    TextField searchField;


    // To make ur controller accessible from other controllers ( thank me later )
    private static StudentController instance;

    public StudentController() {
        instance = this;
    }

    public static StudentController getInstance() {
        return instance;
    }

    final FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // This method adds a new student to the system
    @FXML
    public void addStudent() {

    }

    public void findById() {

    }


    public void addCardToScrollPane(ObservableList<Student> students) {

    }

    public void deleteNode(int hashcode, int studentId) {

    }

    public void browseFiles() {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try {
                String newName = UploadAPI.upload(file);
                prof.setText(newName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

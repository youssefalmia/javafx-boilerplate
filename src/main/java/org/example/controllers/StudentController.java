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

    // This method adds a new student to the system
    @FXML
    public void addStudent() {
        // Clear the error text
        errorText.setText("");
        // Check if the name and age fields are empty
        if (nameField.getText().isEmpty() || ageField.getText().isEmpty()) {
            // Set the error text to indicate that the name and age are required and return
            errorText.setText("Name and age are required");
            return;
        }
        // Create a new Student object and set its name, age, and profile picture URL
        Student student = new Student();
        student.setName(nameField.getText());
        student.setAge(Integer.parseInt(ageField.getText()));
        student.setProfilePicUrl(UploadAPI.UPLOAD_PATH + prof.getText());
        // Add the student to the database
        student = studentDao.add(student);
        // Add the student card to the scroll pane
        addCardToScrollPane(FXCollections.observableArrayList(student));
    }

    public void findById() {
        // Clear the previous results
        personVbox.getChildren().clear();

        // Check if searchField is empty
        if (searchField.getText().isEmpty()) {
            // If searchField is empty, show all students
            addCardToScrollPane(studentDao.getAll());
            return;
        }

        // If searchField is not empty, find the student with the given ID
        int studentId = Integer.parseInt(searchField.getText());
        Student st = studentDao.getById(studentId);

        // If student is not found, do nothing
        if (st.getId() == 0) {
            return;
        }

        // Show the found student
        addCardToScrollPane(FXCollections.observableArrayList(st));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        scroll.setFitToWidth(true);
        addCardToScrollPane(studentDao.getAll());
    }

    public void addCardToScrollPane(ObservableList<Student> students) {
        try {
            for (Student st : students) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../interfaces/StudentItem.fxml"));
                Node node = loader.load();

                // Set the data of the corresponding StudentItemController
                StudentItemController studentItemController = loader.getController();
                studentItemController.setData(st);

                // Add the StudentItem to the VBox
                personVbox.getChildren().add(node);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteNode(int hashcode, int studentId) {
        // Remove the Node with the given hashcode from the VBox
        personVbox.getChildren().removeIf(e -> e.hashCode() == hashcode);

        // Delete the corresponding student from the database
        studentDao.deleteById(studentId);

    }
}

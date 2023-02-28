package org.example.controllers;

import javafx.collections.*;
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

    ObservableList<Student> students = null;

    // To make ur controller accessible from other controllers ( thank me later )
    private static StudentController instance;
    public StudentController() {
        instance = this;
    }
    public static StudentController getInstance() {
        return instance;
    }

    final FileChooser fileChooser = new FileChooser();

    @FXML
    public void addStudent() {
        errorText.setText("");
        if (nameField.getText().isEmpty() || ageField.getText().isEmpty()) {
            errorText.setText("Name and age are required");
            return;
        }
        Student student = new Student();
        student.setName(nameField.getText());
        student.setAge(Integer.parseInt(ageField.getText()));
        student.setProfilePicUrl(UploadAPI.UPLOAD_PATH + prof.getText());
        student = studentDao.add(student);
        addCardToScrollPane(FXCollections.observableArrayList(student));
    }

    public void findById() {
        if(searchField.getText().isEmpty()){
            addCardToScrollPane(this.students);
            return;
        }
        int studentId = Integer.parseInt(searchField.getText());
        Node node = personVbox.getChildren().stream()
                .filter(e -> {
                    int studentIdFromBorderpane = Integer.parseInt(((Text)((BorderPane)e).getTop()).getText());
                    return studentIdFromBorderpane == studentId;
                })
                .findFirst()
                .orElse(null);

        if (node != null) {
            personVbox.getChildren().setAll(node);
        }
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
        hBoxScroll.setMinWidth(scroll.getHvalue());
        this.students = studentDao.getAll();
        addCardToScrollPane(this.students);
    }

    public void addCardToScrollPane(ObservableList<Student> students) {
        try {
            for (Student st : students) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../interfaces/StudentItem.fxml"));
                Node node = loader.load();

                StudentItemController studentItemController = loader.getController();
                studentItemController.setData(st);

                personVbox.getChildren().add(node);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteNode(int hashcode, int studentId) {

        personVbox.getChildren().stream().filter(e -> e.hashCode() == hashcode).forEach(e -> System.out.println(e.hashCode()));

        personVbox.getChildren().removeIf(e -> e.hashCode() == hashcode);

        studentDao.deleteById(studentId);

    }
}

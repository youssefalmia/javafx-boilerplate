package org.example.dao;

import javafx.collections.*;
import org.example.model.*;

import java.sql.*;

/**
 * @author Jozef
 */
public interface IStudentDao {
    public Student getById(int id);
    public ObservableList<Student> getAll();
    public Student add(Student student);
    public void update(Student student);
    public void deleteById(int id);
}

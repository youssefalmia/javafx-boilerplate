package org.example.dao;

import javafx.collections.*;
import org.example.model.*;
import org.example.utils.*;

import java.sql.*;

/**
 * @author Jozef
 */
public class StudentDao implements IStudentDao {

    Connection connection = DataSource.getInstance().getCnx();
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public Student getById(int id) {
        String req = "select * from student where id=?";
        Student student = new Student();
        try {
            ps = connection.prepareStatement(req);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setAge(rs.getInt(3));
                student.setProfilePicUrl(rs.getString(4));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public ObservableList<Student> getAll() {
        String req = "select * from student";
        ObservableList<Student> students = FXCollections.observableArrayList();
        try {
            rs = connection.createStatement().executeQuery(req);
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setName(rs.getString(2));
                student.setAge(rs.getInt(3));
                student.setProfilePicUrl(rs.getString(4));

                students.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }

    @Override
    public Student add(Student student) {

        String req = "insert into student values(?,?,?,?)";
        try {
            ps = connection.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.setInt(3, student.getAge());
            ps.setString(4,student.getProfilePicUrl());
            ps.execute();

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                student.setId(rs.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;
    }

    @Override
    public void update(Student Student) {

    }

    @Override
    public void deleteById(int id) {

    }
}

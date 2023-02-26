package org.example.model;

/**
 * @author Jozef
 */
public class Student {
    private int id;
    private String name;
    private int age;
    private String profilePicUrl;

    public Student(int id, String name, int age, String profilePicUrl) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.profilePicUrl = profilePicUrl;
    }

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", profilePicUrl='" + profilePicUrl + '\'' +
                '}';
    }
}

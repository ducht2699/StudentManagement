package com.example.studentmanagement;

import java.sql.Date;

public class Students {
    private int StudentID;
    private String name;
    private String dob;
    private String email;
    private String address;
    private boolean isSelected;

    public Students(int studentID, String name, String dob, String email, String address) {
        StudentID = studentID;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.isSelected = false;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

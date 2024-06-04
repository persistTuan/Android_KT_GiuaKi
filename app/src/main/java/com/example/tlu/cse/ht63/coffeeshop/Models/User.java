package com.example.tlu.cse.ht63.coffeeshop.Models;

import java.util.Date;

public class User {
    private int id;
    private String fullName;
    private String userName;
    private String password;
    private boolean is_employee;
    private boolean status;
    private Date created_at;

    public User(String fullName, String userName, String password, boolean is_employee, boolean status, Date created_at) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.is_employee = is_employee;
        this.status = status;
        this.created_at = created_at;
    }

    public User(String fullName, String userName, String password, boolean is_employee) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.is_employee = is_employee;
    }

    public User(int id, String fullName, String userName, String password, boolean is_employee, boolean status, Date created_at) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.is_employee = is_employee;
        this.status = status;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIs_employee() {
        return is_employee;
    }

    public void setIs_employee(boolean is_employee) {
        this.is_employee = is_employee;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}

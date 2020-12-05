package com.example.application.object;

public class User {
    private String userID;
    private String userName;
    private String name;
    private String password;
    private String date;
    private String gender;
    private boolean login;

    protected User() {
        userID = "UnRegistered";
        userName = "UnRegistered";
        name = "UnRegistered";
        password = "UnRegistered";
        date = "UnRegistered";
        gender = "UnRegistered";
        login = false;
    }

    public User(String userID, String userName, String name, String password, String date, String gender, boolean login) {
        this.userID = userID;
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.date = date;
        this.gender = gender;
        this.login = login;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}

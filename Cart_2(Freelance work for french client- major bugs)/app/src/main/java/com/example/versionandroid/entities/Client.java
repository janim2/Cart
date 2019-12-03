package com.example.versionandroid.entities;

public class Client {
    private String userName;
    private String email;
    private String lastName;
    private String firstName;
    private int idPrivilege;

    private String password;

    public Client() {

    }

    public Client(String userName, String email, String lastName, String firstName) {
        this.userName = userName;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdPrivilege() {
        return idPrivilege;
    }

    public void setIdPrivilege(int idPrivilege) {
        this.idPrivilege = idPrivilege;
    }
}

package com.android.letsgetplaced;

public class notification {


    private String name;
    private String username;



    public notification() {
    }

    public notification(String name, String username) {
        this.name = name;
        this.username = username;


    }

    public String getname() {
        return name;
    }

    public void setStudentname(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

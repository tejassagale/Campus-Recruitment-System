package com.android.letsgetplaced;

public class Student {

    private String name;
    private String contact;
    private int age;
    private String gender;
    private float cgpa;
    private String branch;
    private int ssc;
    private int hsc;
    private int noofkt;

    public int getErpid() {
        return erpid;
    }

    public void setErpid(int erpid) {
        this.erpid = erpid;
    }

    private int erpid;


    public Student() {
    }

    public Student(String name, String contact, int age, String gender,String branch, float cgpa,int ssc,int hsc,int noofkt,int erpid) {
        this.name = name;
        this.contact = contact;
        this.age = age;
        this.gender = gender;
        this.cgpa = cgpa;
        this.branch = branch;
        this.ssc = ssc;
        this.hsc = hsc;
        this.noofkt=noofkt;
        this.erpid=erpid;

    }

    public String getname() {
        return name;
    }

    public void setStudentname(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getCgpa() {
        return cgpa;
    }

    public void setCgpi(float cgpa) {
        this.cgpa = cgpa;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getSSC() {
        return ssc;
    }

    public void setSSC(int ssc) {
        this.ssc = ssc;
    }

    public int getHSC() {
        return hsc;
    }

    public int getNoofkt() {
        return noofkt;
    }

    public void setNoofkt(int noofkt) {
        this.noofkt = noofkt;
    }

    public void setHSC(int hsc) {
        this.hsc = hsc;
    }


}

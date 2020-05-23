package com.android.letsgetplaced;

public class Placed {
 private String cname;
 private String sname;
 private String salary;
    private String branch;

    public Placed(String cname, String sname, String salary, String branch) {
        this.cname = cname;
        this.sname = sname;
        this.salary = salary;
        this.branch = branch;
    }
    public Placed() {
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}

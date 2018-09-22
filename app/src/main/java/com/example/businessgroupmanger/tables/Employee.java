package com.example.businessgroupmanger.tables;

public class Employee {
    private String eno;
    private String ename;
    private String sex;
    private int    achieve;

    public Employee() {
    }

    public Employee(String eno, String ename, String sex, int achieve) {
        this.eno = eno;
        this.ename = ename;
        this.sex = sex;
        this.achieve = achieve;
    }

    public String getEno() {
        return eno;
    }

    public void setEno(String eno) {
        this.eno = eno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAchieve() {
        return achieve;
    }

    public void setAchieve(int achieve) {
        this.achieve = achieve;
    }
}

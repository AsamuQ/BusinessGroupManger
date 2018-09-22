package com.example.businessgroupmanger.tables;

public class Shop {
    private String sno;
    private String sname;
    private String address;

    public Shop() {
    }

    public Shop(String sno, String sname, String address) {
        this.sno = sno;
        this.sname = sname;
        this.address = address;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

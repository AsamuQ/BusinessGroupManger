package com.example.businessgroupmanger.tables;

public class Goods {
    private String gno;
    private String gname;
    private String gimg;
    private float  uprice;

    public Goods() {
    }

    public Goods(String gno, String gname, String gimg, float uprice) {
        this.gno = gno;
        this.gname = gname;
        this.gimg = gimg;
        this.uprice = uprice;
    }

    public String getGno() {
        return gno;
    }

    public void setGno(String gno) {
        this.gno = gno;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGimg() {
        return gimg;
    }

    public void setGimg(String gimg) {
        this.gimg = gimg;
    }

    public float getUprice() {
        return uprice;
    }

    public void setUprice(float uprice) {
        this.uprice = uprice;
    }
}

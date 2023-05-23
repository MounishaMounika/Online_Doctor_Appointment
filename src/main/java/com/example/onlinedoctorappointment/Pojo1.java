package com.example.onlinedoctorappointment;

public class Pojo1 {
    String docmail,docname,hosname,doctype,hcn,fee,time,docid,address,docspecial;

    public String getDocspecial() {
        return docspecial;
    }

    public void setDocspecial(String docspecial) {
        this.docspecial = docspecial;
    }

    public String getDocmail() {
        return docmail;
    }

    public void setDocmail(String docmail) {
        this.docmail = docmail;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getHosname() {
        return hosname;
    }

    public void setHosname(String hosname) {
        this.hosname = hosname;
    }

    public String getDoctype() {
        return doctype;
    }

    public void setDoctype(String doctype) {
        this.doctype = doctype;
    }

    public String getHcn() {
        return hcn;
    }

    public void setHcn(String hcn) {
        this.hcn = hcn;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Pojo1(String docmail, String docname, String hosname, String doctype,String docspecial, String hcn, String fee, String time, String docid, String address)
    {
        this.docmail=docmail;
        this.docname=docname;
        this.hosname=hosname;
        this.doctype=doctype;
        this.hcn=hcn;
        this.fee=fee;
        this.time=time;
        this.docid=docid;
        this.address=address;
        this.docspecial=docspecial;
    }

    public Pojo1() {
    }
}

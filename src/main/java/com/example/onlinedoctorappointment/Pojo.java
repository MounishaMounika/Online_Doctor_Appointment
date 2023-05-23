package com.example.onlinedoctorappointment;

class Pojo {
    String name,mobile,state,district,city,pincode,street,mail,id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pojo(String name1, String phonenum, String state, String district, String city, String pincode, String street, String mail, String id) {
        this.name=name1;
        this.mobile=phonenum;
        this.state=state;
        this.district=district;
        this.city=city;
        this.pincode=pincode;
        this.street=street;
        this.mail=mail;
        this.id=id;

    }

    public Pojo() {
    }
}

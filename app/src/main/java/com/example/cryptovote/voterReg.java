package com.example.cryptovote;

public class voterReg {
    private String fname;
    private String lname;
    private String email;
    private String adhaar;
    private String dob;

    public voterReg(){

    }

    public voterReg(String fname, String lname, String email, String adhaar, String dob) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.adhaar = adhaar;
        this.dob = dob;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdhaar() {
        return adhaar;
    }

    public void setAdhaar(String adhaar) {
        this.adhaar = adhaar;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}

package com.example.cryptovote;

public class candidate {
    private String name;
    private String ID;
    private int bID;

    public candidate(){

    }

    public candidate(String name, String ID, int bID) {
        this.name = name;
        this.ID = ID;
        this.bID = bID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getbID() {
        return bID;
    }

    public void setbID(int bID) {
        this.bID = bID;
    }
}

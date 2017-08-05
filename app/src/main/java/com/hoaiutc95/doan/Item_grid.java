package com.hoaiutc95.doan;

/**
 * Created by Thu Hoai on 3/22/2017.
 */

public class Item_grid {
    private int id;

    public int getIdcd() {
        return idcd;
    }

    public void setIdcd(int idcd) {
        this.idcd = idcd;
    }

    private  int idcd;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private  byte[] image;

    public Item_grid(String name, byte[] image) {
        this.name = name;
        this.image = image;
    }

    public Item_grid() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

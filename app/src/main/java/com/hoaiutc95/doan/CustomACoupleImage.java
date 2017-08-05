package com.hoaiutc95.doan;

import android.graphics.Bitmap;

/**
 * Created by Thu Hoai on 5/19/2017.
 */

public class CustomACoupleImage {
    public Bitmap b;
    public String name_b;
    public int sound;

    public CustomACoupleImage(Bitmap b, String name_b) {
        this.b = b;
        this.name_b = name_b;
    }

    public CustomACoupleImage(Bitmap b, String name_b, int sound) {
        this.b = b;
        this.name_b = name_b;
        this.sound = sound;
    }

    public Bitmap getB() {
        return b;
    }

    public void setB(Bitmap b) {
        this.b = b;
    }

    public String getName_b() {
        return name_b;
    }

    public void setName_b(String name_b) {
        this.name_b = name_b;
    }

    public int getSound() {
        return sound;
    }

    public void setSound(int sound) {
        this.sound = sound;
    }
}

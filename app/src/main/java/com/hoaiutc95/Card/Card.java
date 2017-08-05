package com.hoaiutc95.Card;

import android.widget.Button;

/**
 * Created by Thu Hoai on 5/9/2017.
 */

public class Card {
    public Button button;
    public int x;
    public int y;

    public Card(Button button, int x, int y) {
        this.x = x;
        this.y = y;
        this.button = button;
    }
}

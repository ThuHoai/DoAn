package com.hoaiutc95.doan;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Thu Hoai on 5/12/2017.
 */

public class CutomTextView extends android.support.v7.widget.AppCompatTextView {
    public CutomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CutomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CutomTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.DEFAULT;

            switch (getTypeface().getStyle()) {
                case Typeface.BOLD:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/DejaVuSans-Bold.ttf");
                    break;

                case Typeface.ITALIC:
                    tf = Typeface.createFromAsset(getContext().getAssets(),  "fonts/DejaVuSansMono.ttf");
                    break;


                default:
                    tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/DejaVuSans-Bold.ttf");
                    break;
            }

            setTypeface(tf);
        }
    }

}
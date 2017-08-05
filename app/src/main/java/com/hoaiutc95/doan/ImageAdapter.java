package com.hoaiutc95.doan;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Thu Hoai on 4/13/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context myContext;
    private int itemBackground;
    private ArrayList<Item_grid> a;


    @Override
    public int getCount() {
        return a.size();
    }

    public ImageAdapter(Context myContext, ArrayList<Item_grid> a) {
        this.myContext = myContext;
        this.a = a;
        TypedArray c = myContext.obtainStyledAttributes(R.styleable.Gallery1);
        itemBackground = c.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 0);
        c.recycle(); // giair phongs sau khisuwr dungj

    }


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView image = new ImageView(myContext);
        Item_grid contact = a.get(position);

        byte[] outImage = contact.getImage();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);

        image.setImageBitmap(theImage);

        image.setScaleType(ImageView.ScaleType.FIT_XY);
        image.setPadding(1, 1, 2, 1);
        image.setLayoutParams(new Gallery.LayoutParams(180, 150));

        image.setBackgroundResource(itemBackground);
        return image;
    }
}


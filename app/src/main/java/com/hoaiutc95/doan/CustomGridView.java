package com.hoaiutc95.doan;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 * Created by Thu Hoai on 3/22/2017.
 */

public class CustomGridView extends ArrayAdapter {


    Context myContext;
    ArrayList<Item_grid> data = new ArrayList<>();
    int layoutResourceId;

    public CustomGridView(Context context, int layoutResourceId,ArrayList data ) {
        super(context, layoutResourceId,data);
        this.myContext = context;
        this.data = data;
        this.layoutResourceId=layoutResourceId;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item_grid contact = data.get(position);
        RecordCategories holder = new RecordCategories();
        if(convertView==null){
            convertView = ((Activity)myContext).getLayoutInflater().inflate(layoutResourceId,parent,false);
        }
        holder.txtName = (TextView) convertView.findViewById(R.id.item_text);
        holder.img = (ImageView) convertView.findViewById(R.id.item_image);

        byte[] outImage=contact.getImage();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.txtName.setText(contact.getName());
        holder.img.setImageBitmap(theImage);
        return convertView;
    }

    static class RecordCategories{
        TextView txtName;
        ImageView img;
    }



}

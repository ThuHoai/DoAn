package com.hoaiutc95.doan;

import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * Created by Thu Hoai on 3/18/2017.
 */

public class loaddata extends SQLiteDataController {



    public loaddata(Context con) {
        super(con);
    }




    public  ArrayList<Item_grid> getCatagory(){
        ArrayList<Item_grid> arr= new ArrayList();
        openDataBase();
        String sql = "select idcd,tenCD,anh from chude";


        Cursor cursor = database.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                Item_grid contact = new Item_grid();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setImage(cursor.getBlob(2));
                // Adding contact to list
                arr.add(contact);
            } while (cursor.moveToNext());
        }
        return  arr;
    }

    public  ArrayList<Item_grid> getPicture(int id){
        ArrayList<Item_grid> arr= new ArrayList();
        openDataBase();
        String sql = "select * from anh where idcd =" + id;


        Cursor cursor = database.rawQuery(sql,null);
        if (cursor.moveToFirst()) {
            do {
                Item_grid contact = new Item_grid();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setIdcd(Integer.parseInt(cursor.getString(1)));
                contact.setName(cursor.getString(2));
                contact.setImage(cursor.getBlob(3));
                // Adding contact to list
                arr.add(contact);
            } while (cursor.moveToNext());
        }
        return  arr;
    }




}

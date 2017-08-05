package com.hoaiutc95.Diaglog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoaiutc95.doan.CustomGridView;
import com.hoaiutc95.doan.Item_grid;
import com.hoaiutc95.doan.MemoryMatchActivity;
import com.hoaiutc95.doan.R;
import com.hoaiutc95.doan.loaddata;

import java.util.ArrayList;


 // Created by Thu Hoai on 5/20/2017.



public class DialogCatagories extends Activity{
    Context context;
    Dialog dialog;
    ArrayList<Item_grid> lstCatagory;

    public DialogCatagories(Context context) {
        this.context = context;
    }


 /*   public void showCatagories() {
        if (this.dialog != null) {
            this.dialog.show();
        } else {
            dialog = new Dialog(context);
            this.dialog.requestWindowFeature(1);


            this.dialog.setContentView(R.layout.dialgo_catagory);
            CustomGridView customGridView = new CustomGridView(context,R.layout.row_dialgo,getDataCatagory());
           GridView gridAblum = (GridView) dialog.findViewById(R.id.grv_dialog);
            gridAblum.setAdapter(customGridView);
            gridAblum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent i = new Intent("");
                            i.putExtra("possiton", position);
                    startActivity(i);
                }
            });

        }
        this.dialog.show();
    }
    private ArrayList<Item_grid> getDataCatagory() {
        loaddata picture = new loaddata(context);
        lstCatagory = new ArrayList<>();
        lstCatagory = picture.getCatagory();
        return lstCatagory;

    }
*/
}

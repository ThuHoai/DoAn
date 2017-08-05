package com.hoaiutc95.doan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;

public class SelectPlayGame extends AppCompatActivity {
     ArrayList<Item_grid> lstCatagory;
    GridView gridAblum;
    int[] intArr;
    Button btnHome;
    CustomGridView customGridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_play_game);
        gridAblum = (GridView) findViewById(R.id.grdCategories);
        btnHome = (Button) findViewById(R.id.btn_home);

        customGridView = new CustomGridView(this,R.layout.row_grid,getDataCatagory());


        gridAblum.setAdapter(customGridView);

        int j = getDataCatagory().size();
        intArr = new int[j];
        for(int i=0;i<getDataCatagory().size();i++){
            intArr[i]=getDataCatagory().get(i).getId();
        }
        gridAblum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SelectPlayGame.this,About_Learning.class);
                intent.putExtra("lstArr",intArr);
                intent.putExtra("possition",position);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private ArrayList<Item_grid> getDataCatagory() {
        loaddata picture = new loaddata(getApplicationContext());
        lstCatagory = new ArrayList<>();
       lstCatagory = picture.getCatagory();
       return lstCatagory;

    }

}

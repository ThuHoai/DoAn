package com.hoaiutc95.doan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Game_Home extends AppCompatActivity implements View.OnClickListener{
     Button _findAphaBet;
    Button  _findImage, btnBack;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__home);
        _findAphaBet = (Button) findViewById(R.id.timchucai);
        btnBack = (Button) findViewById(R.id.btnBack);
        _findAphaBet.setOnClickListener(this);
        _findImage = (Button) findViewById(R.id.chonanhghepchu);
        _findImage.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.roate_botton);
        _findImage.startAnimation(animation);
        _findAphaBet.startAnimation(animation);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.timchucai:
                //startActivity(new Intent(Game_Home.this,DoanChuActivity.class));break;
                startActivity(new Intent(Game_Home.this,DoanChuFragmentActivity.class));break;
            case  R.id.chonanhghepchu:
                startActivity(new Intent(Game_Home.this,MemoryMatchActivity.class));break;
            case  R.id.btnBack:
               finish();
                break;

        }
    }
}

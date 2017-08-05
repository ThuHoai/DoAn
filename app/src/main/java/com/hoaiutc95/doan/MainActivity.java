package com.hoaiutc95.doan;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.hoaiutc95.Sound.AmThanh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    ImageView btLearning;
    ImageView btWritting;
    ImageView btGames, iChim;
    AnimationDrawable rocketAnimation;
    Button btLoa;
    ImageButton btnLoa,btnEmail;
    Button btInfo;
    int length;
    boolean isPause = false;

    Animation animation;
    AmThanh amThanh = new AmThanh(this);
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        AnhXa();
        AnimationMenu();
        createDB();
        amThanh.playBackgroundMusic(R.raw.conheodat);

        btLearning.setOnClickListener(new Learning1());
        btWritting.setOnClickListener(new Write());
        btGames.setOnClickListener(new Game());
        btnLoa.setOnClickListener(new Loa());

        iChim.setBackgroundResource(R.drawable.my_anim);

        btnEmail.setOnClickListener(new Feedback());

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        AnimationDrawable anim = (AnimationDrawable) iChim.getBackground();
        if (hasFocus) {
            // Starting the animation when in Focus
            anim.start();
        } else {
            // Stoping the animation when not in Focus
            anim.stop();
        }

    }

    private void createDB() {// khởi tạo database
        SQLiteDataController sql = new SQLiteDataController(this);
        try {
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Write implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            PlayButton();
            startActivity(new Intent(MainActivity.this, BeTapViet.class));
        }
    }

    class Loa implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (amThanh.isPlayingBackground()) {
                amThanh.pauseBackground();
                length = amThanh.getCurrentPossition();
                // Changing button image to play button
                btnLoa.setBackgroundResource(R.drawable.ic_speakeroff);

            } else {
                amThanh.playBackgroundMusic(length);
                btnLoa.setBackgroundResource(R.drawable.ic_speakeron);

            }
        }
    }

    class Game implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            PlayButton();
            startActivity(new Intent(MainActivity.this, Game_Home.class));

        }
    }

    class Learning1 implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            PlayButton();
            startActivity(new Intent(MainActivity.this, SelectPlayGame.class));


        }

    }

    class  Feedback implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            PlayButton();
            sendEmailFeedback();
        }
    }

    public void PlayButton() {
        amThanh.playButton();
    }


    public void AnimationMenu() {
        animation = AnimationUtils.loadAnimation(this, R.anim.zoom);
        btLearning.startAnimation(animation);
        btWritting.startAnimation(animation);
        btGames.startAnimation(animation);
    }

    public void AnhXa() {
        btLearning = (ImageView) findViewById(R.id.img_learn1);
        btWritting = (ImageView) findViewById(R.id.img_learn2);
        btGames = (ImageView) findViewById(R.id.img_game);
        iChim = (ImageView) findViewById(R.id.imChim);
        btnLoa = (ImageButton) findViewById(R.id.imgLoa);
        btnEmail = (ImageButton) findViewById(R.id.imgEmail);
    }

    @Override
    protected void onResume() {
        btLearning.startAnimation(animation);
        btWritting.startAnimation(animation);
        btGames.startAnimation(animation);
        // amThanh.playBackgroundMusic(R.raw.conheodat);
        // btnLoa.setBackgroundResource(R.drawable.ic_speakeron);
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (btLearning != null) {
            btLearning.clearAnimation();
            btWritting.clearAnimation();
            btGames.clearAnimation();
            btnLoa.setBackgroundResource(R.drawable.ic_speakeroff);
            length = amThanh.getCurrentPossition();
            amThanh.onDes();

        }
        super.onPause();
    }

    public void sendEmailFeedback() {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"hoaiuct95@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "PHẢN HỒI (Dạy bé học) " +getAllInfo());
        i.putExtra(Intent.EXTRA_TEXT   , BuildConfig.FLAVOR);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public String screenSize() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return "Screen: " +displaymetrics.widthPixels + "X" + displaymetrics.heightPixels;
    }

    public String getAllInfo() {
        return "\n" +getDeviceName() + "\n" +  getVersion()+ "\n" +screenSize();
    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        //lay model thiet bi
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return "Model: "+(capitalize(manufacturer) + " " + model).substring(7);
        }
    }
    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        return !Character.isUpperCase(first) ? Character.toUpperCase(first) + s.substring(1) : s;
    }

    public String getVersion() {
        //Android SDK: 19 (4.4.4)"
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;
        return "Version: " + release ;
    }
}

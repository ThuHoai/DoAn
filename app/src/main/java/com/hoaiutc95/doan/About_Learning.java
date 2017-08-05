package com.hoaiutc95.doan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoaiutc95.Sound.AmThanh;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class About_Learning extends AppCompatActivity {
    ArrayList<Item_grid> lsPicture;
    int current_possition;
    ImageView img_;
    ImageButton btnMusic;
    Gallery gallery;
    ImageView imgBground;
    int[] music;
    Button btnBack;
    int maxV, curV;
    boolean isSound = true;
    int length;
    TextView txt_;
    AmThanh amThanh;
    AudioManager myAudio;
    private Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_alphabet);
        loadFragment();
        OnOffSound();
        btnMusic = (ImageButton) findViewById(R.id.btnMusic);
        btnBack = (Button) findViewById(R.id.btn_home);
        txt_ = (TextView) findViewById(R.id.txtPicture);
        ImageAdapter imageAdapter = new ImageAdapter(this, lsPicture);
        amThanh = new AmThanh(getApplicationContext());
        gallery = (Gallery) findViewById(R.id.gallery);
        gallery.setAdapter(imageAdapter);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                byte[] outImage = lsPicture.get(position).getImage();
                ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
                Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                img_.setImageBitmap(theImage);
                animation = AnimationUtils.loadAnimation(About_Learning.this, R.anim.zoom_image);
                img_.startAnimation(animation);
                txt_.setText(lsPicture.get(position).getName());
                amThanh.playCata(music[position]);
                current_possition = position;

            }
        });
        img_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 animation =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
                 img_.startAnimation(animation);
                amThanh.playCata(music[current_possition]);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                amThanh.playButton();
                onBackPressed();
            }
        });
        btnMusic.setOnClickListener(new OnOffMusic());

    }


    public void loadFragment() {
        img_ = (ImageView) findViewById(R.id.imgPicture);
        imgBground = (ImageView) findViewById(R.id.imgBacground);
        lsPicture = new ArrayList<>();
        music = new int[]{};
        Bundle extras = getIntent().getExtras();
        int[] arrCatagory = extras.getIntArray("lstArr");
        int possition = extras.getInt("possition");
        switch (arrCatagory[possition]) {
            case 1:
                lsPicture = getDataAnimal(1);
                music = PhatAm._alphabet;
                break;
            case 2:
                Drawable a = getResources().getDrawable(R.drawable.color_layer_bgr4);
                imgBground.setBackground(a);
                lsPicture = getDataAnimal(2);
                music = PhatAm._animal;
                 break;
            case 3:
                Drawable b = getResources().getDrawable(R.drawable.color_layer_bgr4);
                imgBground.setBackground(b);
                lsPicture = getDataAnimal(3);
                music = PhatAm._device;
                break;
            case 4:
                lsPicture = getDataAnimal(4);
                music = PhatAm._color;
                break;
            case 5:
                Drawable c= getResources().getDrawable(R.drawable.fruits_r);
                imgBground.setBackground(c);
                lsPicture = getDataAnimal(5);
                music = PhatAm._fruit;
                 break;
            case 6:
                lsPicture = getDataAnimal(6);
                music = PhatAm._number;
                break;
            case 7:
                lsPicture = getDataAnimal(7);
                music = PhatAm._shape; break;
        }

    }

    public ArrayList<Item_grid> getDataAnimal(int idcd) {
        loaddata picture = new loaddata(getApplicationContext());
        ArrayList lstData = new ArrayList<>();
        lstData = picture.getPicture(idcd);
        return lstData;
    }

    public class OnOffMusic implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(isSound == true){
                amThanh.playButton();
                myAudio.setStreamVolume(AudioManager.STREAM_MUSIC,curV,0);
                btnMusic.setBackgroundResource(R.drawable.ic_speakeroff);
                isSound = false;
            }else{
                amThanh.playButton();
                myAudio.setStreamVolume(AudioManager.STREAM_MUSIC,maxV,0);
                btnMusic.setBackgroundResource(R.drawable.ic_speakeron);
                isSound = true;

            }
        }
    }

    public void OnOffSound(){
        myAudio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        maxV = myAudio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        curV = myAudio.getStreamVolume(AudioManager.STREAM_MUSIC);
        curV = 0;
    }
}

package com.hoaiutc95.doan;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.hoaiutc95.Card.Card;
//import com.hoaiutc95.Diaglog.DialogCatagories;
import com.hoaiutc95.Sound.AmThanh;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Thu Hoai on 5/20/2017.
 */

public class MemoryMatchActivity extends AppCompatActivity {
    private static int COL_COUNT;
    private static int ROW_COUNT;
    private int diem, maxV, curV;
    ;
    private int lever;
    public static Object lock;
    AmThanh amThanh;
    private int[][] cards;
    private Context context;
    private Card firstCard;
    private Card seconedCard;
    private int[] mangx;
    private int[] mangy;
    private TableLayout mainTable;
    private ArrayList<CustomACoupleImage> images;
    private ArrayList<Item_grid> picture;
    private TableRow.LayoutParams params;
    private Drawable backImage;
    private ButtonListener buttonListener;
    View rootview;
    Toolbar mToolBar;
    Button btnCatagories, btnBack, btnSound;
    private UpdateCardsHandler handler;
    private int turns;
    int idcd = 5;
    boolean isSound = true;
    ArrayList<Item_grid> lstCatagory;
    ArrayList<int[]> arr_sound;
    AudioManager myAudio;

    static {
        COL_COUNT = -1;
        ROW_COUNT = -1;
        lock = new Object();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_acouple2);
        this.lever = 0;
        this.mangx = new int[]{2, 3, 4, 5, 6};
        this.mangy = new int[]{2, 2, 2, 2, 3};
        ArraySound();

        myAudio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        maxV = myAudio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        curV = myAudio.getStreamVolume(AudioManager.STREAM_MUSIC);
        curV = 0;

        amThanh = new AmThanh(this);
        mainTable = (TableLayout) findViewById(R.id.TableLayout03);
        params = new TableRow.LayoutParams((int) getResources().getDimension(R.dimen.size_acouple), (int) getResources().getDimension(R.dimen.size_acouple));
         params.setMargins(0,0,8, 8);
        btnCatagories = (Button) findViewById(R.id.btnCatagories);
        btnSound = (Button) findViewById(R.id.btnSound);
        btnBack = (Button) findViewById(R.id.btnHome);
        backImage = getResources().getDrawable(R.drawable.btn_hidden);
        handler = new UpdateCardsHandler();

        this.context = this.mainTable.getContext();
        Collections.shuffle(loadImages());
        newGame(mangx[lever], mangy[lever]);

        btnCatagories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               OnOffSound();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private ArrayList<Item_grid> getDataCatagory() {
        loaddata picture = new loaddata(context);
        lstCatagory = new ArrayList<>();
        lstCatagory = picture.getCatagory();
        return lstCatagory;

    }

    private void newGame(int c, int r) {
        this.diem = 0;
        ROW_COUNT = r;
        COL_COUNT = c;
        cards = (int[][]) Array.newInstance(Integer.TYPE, new int[]{COL_COUNT, ROW_COUNT});
        mainTable.removeView(findViewById(R.id.TableRow01));
        this.mainTable.removeView(findViewById(R.id.TableRow02));
        TableRow tr = (TableRow) findViewById(R.id.TableRow03);
        tr.removeAllViews();

        mainTable = new TableLayout(this.context);

        tr.addView(this.mainTable);

        //Dua hang vao cot
        for (int y = 0; y < ROW_COUNT; y++) {
            this.mainTable.addView(createRow(y));
        }
        firstCard = null;
        loadCards();
    }

    private TableRow createRow(int y) {
        TableRow row = new TableRow(this.context);
        //center_horizontal
        row.setHorizontalGravity(17);
        for (int x = 0; x < COL_COUNT; x++) {
            row.addView(createImageButton(x, y));
        }
        return row;
    }

    //Tao button tao vi tri x y
    private View createImageButton(int x, int y) {
        Button button = new Button(context);
        button.setBackgroundDrawable(this.backImage);
        button.setId(x * 100 + y);
        //độ rộng của button
        button.setLayoutParams(this.params);
        button.setOnClickListener(new ButtonListener());
        return button;
    }

    //set so de dua vao row
    private void loadCards() {
        try {
            int i;
            int size = COL_COUNT * ROW_COUNT;
            ArrayList<Integer> list = new ArrayList();
            for (i = 0; i < size; i++) {
                list.add(new Integer(i));
            }
            Random r = new Random();
            for (i = size - 1; i >= 0; i--) {
                int t = 0;
                if (i > 0) {
                    t = r.nextInt(i);
                }
                t = list.remove(t).intValue();

                this.cards[i % COL_COUNT][i / COL_COUNT] = t % (size / 2);
                Log.i("loadCards()", "card[" + (i % COL_COUNT) + "][" + (i / COL_COUNT) + "]=" + this.cards[i % COL_COUNT][i / COL_COUNT]);
            }
        } catch (Exception e) {
            Log.e("loadCards()", e + " ");
        }

    }

    class ButtonListener implements View.OnClickListener {
        ButtonListener() {
        }

        @Override
        public void onClick(View v) {
            synchronized (lock) {
                if (firstCard == null || seconedCard == null) {
                    int id = v.getId();
                    turnCard((Button) v, id /100, id %100);
                    return;
                }
            }

        }

        class Hengio extends TimerTask {
            Hengio() {
            }

            public void run() {
                try {
                    synchronized (lock) {
                        handler.sendEmptyMessage(0); //gui thong diep rong bao thuc hien xong
                    }
                } catch (Exception e) {
                    Log.e("E1", e.getMessage());
                }
            }
        }

        private void turnCard(Button button, int x, int y) {
            Drawable bitmap_drawable = new BitmapDrawable(getResources(), images.get(cards[x][y]).getB());
            // amThanh.playnoidung(PhatAm._fruit[cards[x][y]]);
            button.setBackgroundDrawable((Drawable) bitmap_drawable);
            if (firstCard == null) {
                firstCard = new Card(button, x, y);
                amThanh.playnoidung(images.get(cards[firstCard.x][firstCard.y]).getSound());
                Log.i("E2", "anh firtcard:" + firstCard);
            } else if (firstCard.x != x || firstCard.y != y) {
                seconedCard = new Card(button, x, y);
                amThanh.playnoidung(images.get(cards[seconedCard.x][seconedCard.y]).getSound());
                new Timer(false).schedule(new ButtonListener.Hengio(), 1300);
            }

        }
    }

    class UpdateCardsHandler extends Handler {
        UpdateCardsHandler() {
        }

        //nhan duoc thong diep tu button thuc hien kiem tra check
        public void handleMessage(Message msg) {
            synchronized (lock) {
                checkCards();

            }
        }

        public void checkCards() {

            if (cards[seconedCard.x][seconedCard.y] == cards[firstCard.x][firstCard.y]) {

              /*  firstCard.button.setVisibility(View.INVISIBLE);
                seconedCard.button.setVisibility(View.INVISIBLE);*/
                firstCard.button.setEnabled(false);
                seconedCard.button.setEnabled(false);

                diem = diem + 1;
                amThanh.playnoidung(R.raw.magic_newquestion);

                if (diem == (ROW_COUNT * COL_COUNT) / 2) {
                    if (lever < 4) {
                        {
                            lever = lever + 1;
                            Collections.shuffle(loadImages());

                        }
                        newGame(mangx[lever], mangy[lever]);
                    } else {
                        mainTable.removeAllViews();
                        showDialog();
                    }
                }
            } else {
                seconedCard.button.setBackgroundDrawable(backImage);
                firstCard.button.setBackgroundDrawable(backImage);
            }
            firstCard = null;
            seconedCard = null;
        }
    }

    private ArrayList loadImages() {

        int[] sound = arr_sound.get(idcd - 1);
        images = new ArrayList<CustomACoupleImage>();
        for (int i = 0; i < getDataPic(idcd).size(); i++) {
            byte[] outImage = getDataPic(idcd).get(i).getImage();
            ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            String n_image = getDataPic(idcd).get(i).getName();
            CustomACoupleImage a = new CustomACoupleImage(theImage, n_image, sound[i]);
            images.add(a);
        }
        return images;

    }

    private ArrayList<Item_grid> getDataPic(int idcd) {
        loaddata d = new loaddata(getApplicationContext());
        picture = new ArrayList<>();
        picture = d.getPicture(idcd);
        return picture;
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialgo_catagory);
        CustomGridView customGridView = new CustomGridView(context, R.layout.row_dialgo, getDataCatagory());
        GridView gridAblum = (GridView) dialog.findViewById(R.id.grv_dialog);
        gridAblum.setAdapter(customGridView);
        gridAblum.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int[] is = new int[]{1, 2, 3, 4, 5, 6, 7};
                idcd = is[position];
                getDataPic(idcd);
                lever = 0;
                Collections.shuffle(loadImages());
                newGame(mangx[lever], mangy[lever]);
                dialog.dismiss();
            }
        });
       // newGame(mangx[0], mangy[0]);


        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
        dialog.show();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:
                   lever=0;
                   newGame(mangx[lever],mangy[lever]);

        }
        return super.onTouchEvent(event);
    }

    private void ArraySound() {
        arr_sound = new ArrayList();
        arr_sound.add(PhatAm._alphabet);
        arr_sound.add(PhatAm._animal);
        arr_sound.add(PhatAm._device);
        arr_sound.add(PhatAm._color);
        arr_sound.add(PhatAm._fruit);
        arr_sound.add(PhatAm._number);
        arr_sound.add(PhatAm._shape);
    }

   private  void OnOffSound() {
       if (isSound == true) {
           amThanh.playButton();
           myAudio.setStreamVolume(AudioManager.STREAM_MUSIC, curV, 0);
           btnSound.setBackgroundResource(R.drawable.btn_sound_off);
           isSound = false;
       } else {
           amThanh.playButton();
           myAudio.setStreamVolume(AudioManager.STREAM_MUSIC, maxV, 0);
           btnSound.setBackgroundResource(R.drawable.btn_sound_on);
           isSound = true;
       }
   }

}

package com.hoaiutc95.fragment_menu;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hoaiutc95.Diaglog.DialogReport;
import com.hoaiutc95.Sound.AmThanh;
import com.hoaiutc95.doan.BuildConfig;
import com.hoaiutc95.doan.DoanChuActivity;
import com.hoaiutc95.doan.R;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class DoanChuFragment extends Fragment {
    View v;
    private String[] chuv;
    private int[] amTV;
    private String dapAn;
    int height;
    int width;
    Button btDapAn;
    int possitionDapAn;
    private RelativeLayout llContent;
    private Random ran;
    AmThanh amThanh;
    int scorce;
    Button btnhome;
    Button btnScorce;
    private CountDownTimer timeNext;
    private CountDownTimer timer;
    private CountDownTimer timer2;
    private Animation animation;
    private Vibrator vibrator;

    public DoanChuFragment() {
        ran = new Random();
        amThanh = new AmThanh(getActivity());
        possitionDapAn = 0;
        scorce = 0;
      //  dapAn = BuildConfig.FLAVOR;
        chuv = new String[]{"a", "ă", "â", "b", "c", "d", "đ", "e", "ê", "g", "h", "i", "k", "l", "m", "n", "o", "ô", "ơ", "p", "q", "r", "s", "t", "u", "ư", "v", "x", "y"};
        amTV = new int[]{R.raw.a, R.raw.a1, R.raw.a2, R.raw.b, R.raw.c, R.raw.chud, R.raw.d1,
                R.raw.e, R.raw.chue1, R.raw.chug, R.raw.h, R.raw.i, R.raw.k, R.raw.chul, R.raw.chum,
                R.raw.chun,R.raw.chuo, R.raw.chuo1, R.raw.o2, R.raw.chup, R.raw.q, R.raw.r, R.raw.s,
                R.raw.t, R.raw.u, R.raw.u1, R.raw.v, R.raw.x, R.raw.y};
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_doan_chu, container, false);

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("");

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake_anim);
        vibrator = (Vibrator) getActivity().getSystemService(getContext().VIBRATOR_SERVICE);//"vibrator"
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        btnhome = (Button) v.findViewById(R.id.btnBack);
        btnScorce = (Button) v.findViewById(R.id.btnScorce);
        btnScorce.setText("0");

        this.height = displayMetrics.heightPixels;
        this.width = displayMetrics.widthPixels;

        Log.v("ok", "width=" + this.width + " height " + this.height);

        amThanh = new AmThanh(getActivity());
        llContent = (RelativeLayout) v.findViewById(R.id.llContent);
        btDapAn = (Button) v.findViewById(R.id.btnDapAn);

        runTimetoAdd();
        runTimetoAdd2();
        this.timeNext = new NewBalloon(500000, 20000).start();
        createDapAn();

        btDapAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNumber(possitionDapAn);
            }
        });

        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return v;
    }

    //hơn 3 phut
    private void runTimetoAdd() {
        this.timer = new OverBallon(200000,3000).start();
    }

    private void runTimetoAdd2() {
        this.timer2 = new updateBallon(600000, 2500).start();
    }

    //Thoi gian ket thuc hien thi dailog
    class OverBallon extends CountDownTimer {
        OverBallon(long x0, long x1) {
            super(x0, x1);
        }

        public void onTick(long l) {
            addBallon();
        }

        public void onFinish() {
            timeNext.cancel();
            timer2.cancel();
            timer.cancel();
            llContent.removeAllViews();
            amThanh.playnoidung(R.raw.timeup);
            DialogReport a = new DialogReport(getActivity());
            a.showWin(scorce);

        }
    }

    private void createDapAn() {
        possitionDapAn = ran.nextInt(chuv.length);
        btDapAn.setText(chuv[possitionDapAn]);
        dapAn = chuv[possitionDapAn];
    }

    private void addBallon() {
        amThanh.playnoidung(R.raw.new_balloon);
        //vi tri xuat hien bong ngau nhien
        int randomBalloonPositionStart = this.ran.nextInt(this.width *2) - this.width;
        Button txtBalloon = new Button(getActivity());
        if (ran.nextInt(3) < 1) {
            txtBalloon.setText(dapAn);
        } else {
            txtBalloon.setText(chuv[ran.nextInt(chuv.length)] );
        }

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) getResources().getDimension(R.dimen.balloon_width), (int) getResources().getDimension(R.dimen.balloon_height));
        int a = this.ran.nextInt(7);
        if (a == 0) {
            txtBalloon.setBackgroundResource(R.drawable.ic_balloon1);
        } else if (a == 1) {
            txtBalloon.setBackgroundResource(R.drawable.ic_balloon2);
        } else if (a == 2) {
            txtBalloon.setBackgroundResource(R.drawable.ic_balloon3);
        } else if (a == 3) {
            txtBalloon.setBackgroundResource(R.drawable.ic_balloon4);
        } else if (a == 4) {
            txtBalloon.setBackgroundResource(R.drawable.ic_balloon5);
        } else if (a == 5) {
            txtBalloon.setBackgroundResource(R.drawable.ic_balloon6);
        } else if (a == 6) {
            txtBalloon.setBackgroundResource(R.drawable.ic_balloon7);
        }
        txtBalloon.setTextColor(Color.parseColor("#FFFFFF"));
        txtBalloon.setTextSize(getResources().getDimension(R.dimen.balloon_text_Size));
       layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.setMargins(this.ran.nextInt(this.width / 2)+ 120 , 0, 0, 0);
        txtBalloon.setLayoutParams(layoutParams);
        txtBalloon.setOnClickListener(new ClickBallon(txtBalloon));
        llContent.addView(txtBalloon);

        ObjectAnimator translationY = ObjectAnimator.ofFloat(txtBalloon, "translationY", new float[]{(float) (-this.height)});
        ObjectAnimator translationX = ObjectAnimator.ofFloat(txtBalloon, "translationX", new float[]{randomBalloonPositionStart});
        translationX.setInterpolator(new AccelerateInterpolator());
        translationY.setInterpolator(new AccelerateInterpolator());
        AnimatorSet as = new AnimatorSet();
        //thiet lap cac hinh anh xuat hien choi cung 1 luc
        as.playTogether(new Animator[]{translationX, translationY});
        as.setDuration(9000);
        as.start();
        //.setInterpolator(new AccelerateInterpolator()) .setInterpolator(new AccelerateInterpolator())
    }

    class ClickBallon implements View.OnClickListener {
        Button val$txtBallon;

        public ClickBallon(Button val$txtBallon) {
            this.val$txtBallon = val$txtBallon;
        }

        @Override
        public void onClick(View v) {
            if (val$txtBallon.getText().toString().equals(dapAn)) {
                llContent.removeView(val$txtBallon);
                amThanh.playnoidung(R.raw.balloon_pop);
                scorce = scorce + 1;
                btnScorce.setText(String.valueOf(scorce));
                return;

            } else {
                // scorce = scorce -1;
                btnScorce.setText(String.valueOf(scorce));
                vibrator.vibrate(300);
                amThanh.playnoidung(R.raw.sairui);
                val$txtBallon.startAnimation(animation);
            }

        }
    }

    public class NewBalloon extends CountDownTimer {

        public NewBalloon(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            createDapAn();
            playNumber(possitionDapAn);
            btDapAn.startAnimation(animation);

        }

        @Override
        public void onFinish() {

        }
    }

    public class updateBallon extends CountDownTimer {
        public updateBallon(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            addBallon();
        }

        @Override
        public void onFinish() {

        }
    }


    private void playNumber(int i) {
        try {
            amThanh.playnoidung(amTV[i]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        if (this.timer != null) {
            this.timer.cancel();
        }
        if (this.timer2 != null) {
            this.timer2.cancel();
        }
        if (this.timeNext != null) {
            this.timeNext.cancel();
        }
        super.onDestroy();
    }


}

package com.hoaiutc95.Sound;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import com.hoaiutc95.doan.MainActivity;
import com.hoaiutc95.doan.R;

/**
 * Created by Thu Hoai on 3/30/2017.
 */

public class AmThanh {
    private Context context;
    private MediaPlayer mediaBackMusic;
    private MediaPlayer mediaButton;
    private MediaPlayer mediaDung;
    public static MediaPlayer mediaPlayer;
    private MediaPlayer mediaBalloon;
    private MediaPlayer mediaSai;

    public AmThanh(Context context) {
        this.context = context;
    }


    public void playBackgroundMusic(int file) {
        if (this.mediaBackMusic == null) {
            this.mediaBackMusic = MediaPlayer.create(this.context, file);
            this.mediaBackMusic.setLooping(true);
            this.mediaBackMusic.start();
            return;
        }
        this.mediaBackMusic.start();

    }

    public int getCurrentPossition() {
        return mediaBackMusic.getCurrentPosition();
    }


    public int getDuracation() {
        return mediaPlayer.getDuration();
    }


    public boolean isPlayingBackground() {
        if (this.mediaBackMusic.isPlaying()) {
            return true;
        }
        return false;
    }

    public void Restart() {
        mediaBackMusic.pause();
    }

    public void Pause() {
        mediaPlayer.pause();
    }

    public void pauseBackground() {
        if (this.mediaBackMusic.isPlaying()) {
            this.mediaBackMusic.pause();
        }
    }

    public void onDes() {
        mediaBackMusic.pause();
    }

    public void stopBackground() {
        if (this.mediaBackMusic != null) {
            this.mediaBackMusic.stop();
        }
    }

    public void playButton() {
        if (this.mediaButton == null) {
            this.mediaButton = MediaPlayer.create(this.context, R.raw.but);
            this.mediaButton.start();
            return;
        }
        this.mediaButton.start();
    }


    public void playCata(int file1) {
        this.mediaPlayer = MediaPlayer.create(this.context, file1);

        this.mediaPlayer.start();
        //goi khi phat nhac xong
        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();//giai phong
                mp = null;

            }


    });

}

    public void playnoidung(int file1) {
        this.mediaBalloon = MediaPlayer.create(this.context, file1);
        this.mediaBalloon.start();
        //goi khi phat nhac xong
        this.mediaBalloon.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                mp = null;

            }
        });

    }

    public void setLooping(boolean giatri) {
        this.mediaPlayer.setLooping(giatri);
    }

    public void setVolume(float leftVolume, float rightVolume) {
        this.mediaPlayer.setVolume(leftVolume, rightVolume);
    }


}

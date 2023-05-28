package com.example.lw8;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    int imageNumber;
    VideoView videoPlayer;
    MediaPlayer mPlayer;
    Button nextBtn, previousBtn;
    Timer mTimer;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTimer = new Timer();

        ImageView img = (ImageView) findViewById(R.id.img);
        InputStream imageStream = this.getResources().openRawResource(R.raw.ncfu);
        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
        img.setImageBitmap(bitmap);
        imageNumber = 0;
        nextBtn = findViewById(R.id.nextButton);
        previousBtn = findViewById(R.id.previousButton);

        mPlayer = MediaPlayer.create(this, R.raw.powerwolf);
        mPlayer.start();

        videoPlayer = (VideoView) findViewById(R.id.videoPlayer);
        Uri myVideo = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.oblivion);
        videoPlayer.setVideoURI(myVideo);
        MediaController mediaController = new MediaController(this);
        videoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoPlayer);
        videoPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                if(videoPlayer.isPlaying()){
                    mPlayer.pause();
                }
                return false;
            }
        });
        videoPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mPlayer.start();
                    }
                }, 1500);
            }
        });
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!videoPlayer.isPlaying() && !mPlayer.isPlaying()){
                    mPlayer.start();
                }
            }
        }, 1500, 1500);

        AudioManager audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curValue = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar volumeControl = (SeekBar) findViewById(R.id.volumeControl);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curValue);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void nextBtn(View v){
        imageNumber = (imageNumber + 1) % 3;
        InputStream imageStream;
        switch(imageNumber){
            case 1:
                imageStream = this.getResources().openRawResource(R.raw.ncfu1); break;
            case 2:
                imageStream = this.getResources().openRawResource(R.raw.ncfu2); break;
            default:
                imageStream = this.getResources().openRawResource(R.raw.ncfu); break;
        }
        ImageView img = (ImageView) findViewById(R.id.img);
        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
        img.setImageBitmap(bitmap);
    }

    public void previousBtn(View v){
        imageNumber = (imageNumber - 1) % 3 < 0 ? (3 - (imageNumber - 1)) % 3 : (imageNumber - 1) % 3;
        InputStream imageStream;
        switch(imageNumber){
            case 1:
                imageStream = this.getResources().openRawResource(R.raw.ncfu1); break;
            case 2:
                imageStream = this.getResources().openRawResource(R.raw.ncfu2); break;
            default:
                imageStream = this.getResources().openRawResource(R.raw.ncfu); break;
        }
        ImageView img = (ImageView) findViewById(R.id.img);
        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
        img.setImageBitmap(bitmap);
    }
}
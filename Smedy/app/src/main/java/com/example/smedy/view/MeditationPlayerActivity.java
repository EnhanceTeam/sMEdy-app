package com.example.smedy.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.smedy.R;

public class MeditationPlayerActivity extends AppCompatActivity {
    private Toolbar toolbarMeditationPlayer;
    private ImageButton imgBtnPlayPauseMeditationPlayer;
    private TextView txtTitleMeditationPlayer, txtStartTimeMeditationPlayer, txtEndTimeMeditationPlayer;
    private SeekBar seekBarMeditationPlayer;
    private MediaPlayer mediaPlayer;
    private String judul;
    private String uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation_player);
        getWindow().setStatusBarColor(getColor(R.color.soft_blue_100));

        initVar();
        getIntentData();
//        prepareMediaPlayer();
        setThread();
        setListener();
    }

    private void initVar() {
        toolbarMeditationPlayer = findViewById(R.id.toolbarMeditationPlayer);
        imgBtnPlayPauseMeditationPlayer = findViewById(R.id.imgBtnPlayPauseMeditationPlayer);
        txtTitleMeditationPlayer = findViewById(R.id.txtTitleMeditationPlayer);
        txtStartTimeMeditationPlayer = findViewById(R.id.txtStartTimeMeditationPlayer);
        txtEndTimeMeditationPlayer = findViewById(R.id.txtEndTimeMeditationPlayer);
        seekBarMeditationPlayer = findViewById(R.id.seekBarMeditationPlayer);

        setSupportActionBar(toolbarMeditationPlayer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        mediaPlayer.pause();
    }

    private void setListener() {
        imgBtnPlayPauseMeditationPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    imgBtnPlayPauseMeditationPlayer.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayer.pause();
                    seekBarMeditationPlayer.setMax(mediaPlayer.getDuration() / 1000);
                }else{
                    imgBtnPlayPauseMeditationPlayer.setImageResource(R.drawable.ic_baseline_pause_24);
                    mediaPlayer.start();
                    seekBarMeditationPlayer.setMax(mediaPlayer.getDuration() / 1000);
                }
            }
        });

        seekBarMeditationPlayer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (mediaPlayer != null && b) {
                    mediaPlayer.seekTo(i * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void getIntentData() {
        Intent intent = getIntent();
        judul = intent.getStringExtra("judul");
        uri = intent.getStringExtra("uri");
        txtStartTimeMeditationPlayer.setText("00:00");
        if (judul != null) {
            mediaPlayer = MediaPlayer.create(MeditationPlayerActivity.this, Uri.parse(uri));
            mediaPlayer.start();
            imgBtnPlayPauseMeditationPlayer.setImageResource(R.drawable.ic_baseline_pause_24);
            txtTitleMeditationPlayer.setText(judul);
            imgBtnPlayPauseMeditationPlayer.setEnabled(true);
            txtEndTimeMeditationPlayer.setText(formattedTime(mediaPlayer.getDuration() / 1000));
            seekBarMeditationPlayer.setMax(mediaPlayer.getDuration() / 1000);
        } else {
            imgBtnPlayPauseMeditationPlayer.setEnabled(false);
            txtEndTimeMeditationPlayer.setText("00:00");
        }

    }

    private void setThread() {
        MeditationPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int currentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBarMeditationPlayer.setProgress(currentPosition);
                    txtStartTimeMeditationPlayer.setText(formattedTime(currentPosition));
                }
                new Handler().postDelayed(this, 1000);
            }
        });
    }

    private String formattedTime(int time) {
        String result = "";
        int second = time % 60;
        int minute = time / 60;
        String resultSec = "";
        String resultMin = "";

        if (second < 10) {
            resultSec = "0" + second;
        } else {
            resultSec = String.valueOf(second);
        }

        if (minute < 10) {
            resultMin = "0" + minute;
        } else {
            resultMin = String.valueOf(minute);
        }

        result += resultMin + ":" + resultSec;

        return result;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            mediaPlayer.pause();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
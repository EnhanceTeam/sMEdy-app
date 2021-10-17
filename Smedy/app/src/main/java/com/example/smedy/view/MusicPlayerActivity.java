package com.example.smedy.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.smedy.R;
import com.example.smedy.helper.Const;
import com.example.smedy.model.Music;
import com.example.smedy.viewModel.MusicViewModel;

import java.util.ArrayList;

public class MusicPlayerActivity extends AppCompatActivity {
    private ImageView musicPlayerImageView, musicPlayerPlayPauseImageView;
    private SeekBar musicPlayerSeekBar;
    private TextView musicPlayerCurrentTimeTextView, musicPlayerTotalTimeTextView, musicPlayerTitleTextView;
    private MediaPlayer mediaPlayer;
    private MusicViewModel viewModel;
    private String titleFormat, musicFormat, tokenFormat, musicUrl;
    private Intent intent;
    private Handler handler = new Handler();
    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentTime = mediaPlayer.getCurrentPosition();
            musicPlayerCurrentTimeTextView.setText(millisecondsToTimer(currentTime));
        }
    };
    private Observer<ArrayList<Music>> showMusic = new Observer<ArrayList<Music>>() {
        @Override
        public void onChanged(ArrayList<Music> music) {
            musicPlayerTitleTextView.setText(titleFormat);
        }
    };

//    @Override
//    protected void onStart() {
//        super.onStart();
//        prepareMediaPlayer();
//        mediaPlayer.start();
//        musicPlayerPlayPauseImageView.setImageResource(R.drawable.ic_baseline_pause_24);
//        updateSeekBar();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        initialize();
        setListener();
        prepareMediaPlayer();
        setBuffering();

    }

    private void setBuffering() {
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                musicPlayerSeekBar.setSecondaryProgress(i);
            }
        });
    }

    private void setListener() {
        musicPlayerPlayPauseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    musicPlayerPlayPauseImageView.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                } else {
                    mediaPlayer.start();
                    musicPlayerPlayPauseImageView.setImageResource(R.drawable.ic_baseline_pause_24);
                    updateSeekBar();
                }
            }
        });

        musicPlayerSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SeekBar tmpSeekBar = (SeekBar) view;
                int playPosition = (mediaPlayer.getDuration() / 100) * tmpSeekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                musicPlayerCurrentTimeTextView.setText(millisecondsToTimer(mediaPlayer.getCurrentPosition()));

                return false;
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                musicPlayerSeekBar.setProgress(0);
                musicPlayerPlayPauseImageView.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                musicPlayerCurrentTimeTextView.setText("00:00");
                musicPlayerTotalTimeTextView.setText("00:00");
                mediaPlayer.reset();
                prepareMediaPlayer();
            }
        });

    }

    private void prepareMediaPlayer() {
        try {
            mediaPlayer.setDataSource(musicUrl);
            mediaPlayer.prepare();
            musicPlayerTotalTimeTextView.setText(millisecondsToTimer(mediaPlayer.getDuration()));
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateSeekBar() {
        if (mediaPlayer.isPlaying()) {
            musicPlayerSeekBar.setProgress((int) ((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration() * 100));

            handler.postDelayed(updater, 1000);
        }
    }

    private void initialize() {
        intent = getIntent();
        titleFormat = intent.getStringExtra("title");
        musicFormat = intent.getStringExtra("music");
        tokenFormat = intent.getStringExtra("token");

        musicPlayerImageView = findViewById(R.id.musicPlayerImageView);
        musicPlayerPlayPauseImageView = findViewById(R.id.musicPlayerPlayPauseImageView);
        musicPlayerSeekBar = findViewById(R.id.musicPlayerSeekBar);
        musicPlayerCurrentTimeTextView = findViewById(R.id.musicPlayerCurrentTimeTextView);
        musicPlayerTotalTimeTextView = findViewById(R.id.musicPlayerTotalTimeTextView);
        musicPlayerTitleTextView = findViewById(R.id.musicPlayerTitleTextView);

        viewModel.setResultGetMusic();
        viewModel.getResultGetMusic().observe(MusicPlayerActivity.this, showMusic);

        mediaPlayer = new MediaPlayer();
        musicPlayerSeekBar.setMax(100);

        musicUrl = Const.MUSIC_LINK + musicFormat + Const.MUSIC_TOKEN + tokenFormat;
    }

    private String millisecondsToTimer(long milliSeconds) {
        String time = "";
        String secondsString;

        int hours = (int) (milliSeconds / (1000 * 60 * 60) / (1000 * 60));
        int minutes = (int) (milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0) {
            time = hours + ":";
        }

        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        time = time + minutes + ":" + secondsString;

        return time;

    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
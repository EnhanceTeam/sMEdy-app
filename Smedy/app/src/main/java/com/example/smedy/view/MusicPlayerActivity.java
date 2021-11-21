package com.example.smedy.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smedy.R;
import com.example.smedy.helper.Const;
import com.example.smedy.model.Music;
import com.example.smedy.viewmodel.MusicViewModel;

import java.util.ArrayList;

public class MusicPlayerActivity extends AppCompatActivity {
    private Toolbar toolbarMusicPlayer;
    private ImageView musicPlayerImageView, musicPlayerPlayPauseImageView;
    private SeekBar musicPlayerSeekBar;
    private TextView musicPlayerCurrentTimeTextView, musicPlayerTotalTimeTextView, musicPlayerTitleTextView;
    private MediaPlayer mediaPlayer;
    private MusicViewModel viewModel;
    private String titleFormat, musicFormat, tokenFormat, musicUrl;
    private Intent intent;
    private Handler handler = new Handler();
//    private Runnable updater = new Runnable() {
//        @Override
//        public void run() {
//            updateSeekBar();
//            long currentTime = mediaPlayer.getCurrentPosition();
//            musicPlayerCurrentTimeTextView.setText(millisecondsToTimer(currentTime));
//        }
//    };
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
//        play();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        getWindow().setStatusBarColor(getColor(R.color.soft_blue_100));

        initVar();
        getIntentData();
        setThread();
        setListener();


        // TODO: add some codes

//        initialize();
//        setListener();
//        prepareMediaPlayer();
//        play();
//        setBuffering();

    }

    private void setThread() {
        MusicPlayerActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    int currentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    musicPlayerSeekBar.setProgress(currentPosition);
                    musicPlayerCurrentTimeTextView.setText(formattedTime(currentPosition));
                }
                new Handler().postDelayed(this, 1000);
            }
        });
    }

    private void getIntentData() {
        intent = getIntent();
        titleFormat = intent.getStringExtra("title");
        musicFormat = intent.getStringExtra("music");
        tokenFormat = intent.getStringExtra("token");
        musicPlayerCurrentTimeTextView.setText("00:00");
        musicUrl = Const.MUSIC_LINK + musicFormat + Const.MUSIC_TOKEN + tokenFormat;

        if (titleFormat != null) {
            mediaPlayer = MediaPlayer.create(MusicPlayerActivity.this, Uri.parse(musicUrl));
            mediaPlayer.start();
            musicPlayerPlayPauseImageView.setImageResource(R.drawable.ic_baseline_pause_24);
            musicPlayerTitleTextView.setText(titleFormat);
            musicPlayerPlayPauseImageView.setEnabled(true);
            musicPlayerTotalTimeTextView.setText(formattedTime(mediaPlayer.getDuration() / 1000));
            musicPlayerSeekBar.setMax(mediaPlayer.getDuration() / 1000);
        } else {
            musicPlayerPlayPauseImageView.setEnabled(false);
            musicPlayerTotalTimeTextView.setText("00:00");
        }

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
    public void onBackPressed() {
        super.onBackPressed();

        mediaPlayer.pause();
    }

    private void setListener() {
        musicPlayerPlayPauseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    musicPlayerPlayPauseImageView.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                    mediaPlayer.pause();
                    musicPlayerSeekBar.setMax(mediaPlayer.getDuration() / 1000);
                }else{
                    musicPlayerPlayPauseImageView.setImageResource(R.drawable.ic_baseline_pause_24);
                    mediaPlayer.start();
                    musicPlayerSeekBar.setMax(mediaPlayer.getDuration() / 1000);
                }
            }
        });

        musicPlayerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

    private void initVar() {
        toolbarMusicPlayer = findViewById(R.id.toolbarMusicPlayer);
        musicPlayerImageView = findViewById(R.id.musicPlayerImageView);
        musicPlayerPlayPauseImageView = findViewById(R.id.musicPlayerPlayPauseImageView);
        musicPlayerSeekBar = findViewById(R.id.musicPlayerSeekBar);
        musicPlayerCurrentTimeTextView = findViewById(R.id.musicPlayerCurrentTimeTextView);
        musicPlayerTotalTimeTextView = findViewById(R.id.musicPlayerTotalTimeTextView);
        musicPlayerTitleTextView = findViewById(R.id.musicPlayerTitleTextView);

        setSupportActionBar(toolbarMusicPlayer);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            mediaPlayer.pause();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

//    private void setBuffering() {
//        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
//            @Override
//            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
//                musicPlayerSeekBar.setSecondaryProgress(i);
//            }
//        });
//    }
//
//    private void setListener() {
//        musicPlayerPlayPauseImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mediaPlayer.isPlaying()) {
//                    pause();
//                } else {
//                    play();
//                }
//            }
//        });
//
//        musicPlayerSeekBar.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                SeekBar tmpSeekBar = (SeekBar) view;
//                int playPosition = (mediaPlayer.getDuration() / 100) * tmpSeekBar.getProgress();
//                mediaPlayer.seekTo(playPosition);
//                musicPlayerCurrentTimeTextView.setText(millisecondsToTimer(mediaPlayer.getCurrentPosition()));
//
//                return false;
//            }
//        });
//
//        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//            @Override
//            public void onCompletion(MediaPlayer mediaPlayer) {
//                musicPlayerSeekBar.setProgress(0);
//                musicPlayerPlayPauseImageView.setImageResource(R.drawable.ic_baseline_play_arrow_24);
//                musicPlayerCurrentTimeTextView.setText("00:00");
//                musicPlayerTotalTimeTextView.setText("00:00");
//                mediaPlayer.reset();
//                prepareMediaPlayer();
//            }
//        });
//
//    }
//
//    private void play() {
//        mediaPlayer.start();
//        musicPlayerPlayPauseImageView.setImageResource(R.drawable.ic_baseline_pause_24);
//        updateSeekBar();
//    }
//
//    private void pause() {
//        handler.removeCallbacks(updater);
//        mediaPlayer.pause();
//        musicPlayerPlayPauseImageView.setImageResource(R.drawable.ic_baseline_play_arrow_24);
//    }
//
//    private void prepareMediaPlayer() {
//        try {
//            mediaPlayer.setDataSource(musicUrl);
//            mediaPlayer.prepare();
//            musicPlayerTotalTimeTextView.setText(millisecondsToTimer(mediaPlayer.getDuration()));
//        } catch (Exception e) {
//            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void updateSeekBar() {
//        if (mediaPlayer.isPlaying()) {
//            musicPlayerSeekBar.setProgress((int) ((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration() * 100));
//
//            handler.postDelayed(updater, 1000);
//        }
//    }
//
//    private void initialize() {
//        intent = getIntent();
//        titleFormat = intent.getStringExtra("title");
//        musicFormat = intent.getStringExtra("music");
//        tokenFormat = intent.getStringExtra("token");
//
//        toolbarMusicPlayer = findViewById(R.id.toolbarMusicPlayer);
//        musicPlayerImageView = findViewById(R.id.musicPlayerImageView);
//        musicPlayerPlayPauseImageView = findViewById(R.id.musicPlayerPlayPauseImageView);
//        musicPlayerSeekBar = findViewById(R.id.musicPlayerSeekBar);
//        musicPlayerCurrentTimeTextView = findViewById(R.id.musicPlayerCurrentTimeTextView);
//        musicPlayerTotalTimeTextView = findViewById(R.id.musicPlayerTotalTimeTextView);
//        musicPlayerTitleTextView = findViewById(R.id.musicPlayerTitleTextView);
//
//        setSupportActionBar(toolbarMusicPlayer);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeButtonEnabled(true);
//
//        viewModel = new ViewModelProvider(MusicPlayerActivity.this).get(MusicViewModel.class);
//        viewModel.setResultGetMusic();
//        viewModel.getResultGetMusic().observe(MusicPlayerActivity.this, showMusic);
//
//        mediaPlayer = new MediaPlayer();
//        musicPlayerSeekBar.setMax(100);
//
//        musicUrl = Const.MUSIC_LINK + musicFormat + Const.MUSIC_TOKEN + tokenFormat;
//    }
//
//    private String millisecondsToTimer(long milliSeconds) {
//        String time = "";
//        String secondsString;
//
//        int hours = (int) (milliSeconds / (1000 * 60 * 60) / (1000 * 60));
//        int minutes = (int) (milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
//        int seconds = (int) ((milliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
//
//        if (hours > 0) {
//            time = hours + ":";
//        }
//
//        if (seconds < 10) {
//            secondsString = "0" + seconds;
//        } else {
//            secondsString = "" + seconds;
//        }
//
//        time = time + minutes + ":" + secondsString;
//
//        return time;
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        pause();
//        finish();
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (item.getItemId() == android.R.id.home) {
//            pause();
//            finish();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

}
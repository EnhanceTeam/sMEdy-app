
package com.example.smedy.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.smedy.R;
import com.example.smedy.adapter.MusicAdapter;
import com.example.smedy.model.Music;
import com.example.smedy.viewModel.MusicViewModel;

import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    private RecyclerView musicRV;
    private MusicViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        initialize();

    }

    private void initialize() {
        musicRV = findViewById(R.id.musicRV);

        viewModel = new ViewModelProvider(MusicActivity.this).get(MusicViewModel.class);
        viewModel.setResultGetMusic();
        viewModel.getResultGetMusic().observe(MusicActivity.this, showMusic);
    }

    private Observer<ArrayList<Music>> showMusic = new Observer<ArrayList<Music>>() {
        @Override
        public void onChanged(ArrayList<Music> music) {
            musicRV.setLayoutManager(new LinearLayoutManager(MusicActivity.this));
            MusicAdapter adapter = new MusicAdapter(MusicActivity.this);
//            adapter.setMusicList(music.get(position));
            musicRV.setAdapter(adapter);
        }
    };

}
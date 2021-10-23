package com.example.smedy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smedy.model.Music;
import com.example.smedy.repositories.MusicRepository;

import java.util.ArrayList;

public class MusicViewModel extends AndroidViewModel {

    private MusicRepository repository;

    public MusicViewModel(@NonNull Application application) {
        super(application);
        repository = MusicRepository.getInstance();
    }

    //===Begin of viewModel getMusic

    private MutableLiveData<ArrayList<Music>> resultGetMusic = new MutableLiveData<>();

    public void setResultGetMusic() {
        resultGetMusic = repository.getMusic();
    }

    public LiveData<ArrayList<Music>> getResultGetMusic() {
        return resultGetMusic;
    }

    //===End of viewModel getMusic
}

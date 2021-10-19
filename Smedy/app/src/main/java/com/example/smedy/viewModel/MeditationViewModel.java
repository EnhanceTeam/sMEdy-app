package com.example.smedy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smedy.model.Meditation;
import com.example.smedy.repositories.MeditationRepository;

import java.util.ArrayList;

public class MeditationViewModel extends AndroidViewModel {
    private MeditationRepository meditationRepository;
    private MutableLiveData<ArrayList<Meditation>> resultGetMeditasi = new MutableLiveData<>();

    public MeditationViewModel(@NonNull Application application) {
        super(application);

        meditationRepository = MeditationRepository.getInstance();
    }

    public void getMeditation(){
        resultGetMeditasi = meditationRepository.getMeditation();
    }

    public LiveData<ArrayList<Meditation>> getMedtationResult(){
        return resultGetMeditasi;
    }
}

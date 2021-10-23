package com.example.smedy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smedy.model.Psychologist;
import com.example.smedy.repositories.PsychologistRepository;

import java.util.ArrayList;

public class PsychologistViewModel extends AndroidViewModel {
    private PsychologistRepository repository;

    public PsychologistViewModel(@NonNull Application application){
        super(application);
        repository = PsychologistRepository.getInstance();
    }

    private MutableLiveData<ArrayList<Psychologist>> resultGetPsychologist = new MutableLiveData<>();

    public void getPsychologist(){
        resultGetPsychologist = repository.getPsychologistData();
    }

    public LiveData<ArrayList<Psychologist>>getResultGetPsychologist(){
        return  resultGetPsychologist;
    }
}

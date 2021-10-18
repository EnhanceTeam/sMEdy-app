package com.example.smedy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smedy.model.Psikiater;
import com.example.smedy.repository.PsikiaterRepository;

import java.util.ArrayList;

public class PsikiaterViewModel extends AndroidViewModel {
    private PsikiaterRepository repository;

    public PsikiaterViewModel(@NonNull Application application){
        super(application);
        repository = PsikiaterRepository.getInstance();
    }

    private MutableLiveData<ArrayList<Psikiater>> resultGetPsikiater = new MutableLiveData<>();

    public void getPsikiater(){
        resultGetPsikiater = repository.getPsikiaterData();
    }

    public LiveData<ArrayList<Psikiater>>getResultGetPsikiater(){
        return  resultGetPsikiater;
    }
}

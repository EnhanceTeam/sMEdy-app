package com.example.smedy.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smedy.model.Appointment;
import com.example.smedy.model.Psychologist;
import com.example.smedy.repositories.AppointmentRepository;
import com.example.smedy.repositories.PsychologistRepository;

import java.util.ArrayList;

public class AppointmentViewModel extends AndroidViewModel {
    private AppointmentRepository repository;

    public AppointmentViewModel(@NonNull Application application){
        super(application);
        repository = AppointmentRepository.getInstance();
    }

    private MutableLiveData<ArrayList<Appointment>> resultGetAppointment = new MutableLiveData<>();

    public void getAppointment(String status){
        resultGetAppointment = repository.getAppointmentData(status);
    }

    public LiveData<ArrayList<Appointment>> getResultGetAppointment(){
        return  resultGetAppointment;
    }
}

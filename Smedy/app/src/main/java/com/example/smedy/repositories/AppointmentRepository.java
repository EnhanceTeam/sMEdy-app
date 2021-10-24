package com.example.smedy.repositories;

import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.example.smedy.helper.Const;
import com.example.smedy.model.Appointment;
import com.example.smedy.model.Psychologist;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    private static AppointmentRepository repository;
    private ArrayList<Appointment> appointmentList = new ArrayList<>();
    private MutableLiveData<ArrayList<Appointment>> result = new MutableLiveData<>();

    private FirebaseFirestore fStore = FirebaseFirestore.getInstance();
    private Query historyReference;

    private String userId = FirebaseAuth.getInstance().getUid();

    public static AppointmentRepository getInstance() {
        if (repository == null) {
            repository = new AppointmentRepository();
        }
        return repository;
    }

    public MutableLiveData<ArrayList<Appointment>> getAppointmentData(String status) {

        loadDatabase(status);

        return result;
    }

    private void loadDatabase(String status) {
        historyReference = fStore.collection(Const.DB_APPOINTMENT).document(userId).collection(Const.DB_HISTORY);

//        TODO Sort by date

        historyReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                appointmentList.clear();

                for (QueryDocumentSnapshot doc : value) {
                    if (doc != null) {
                        String itemId = doc.getId();
                        Appointment appointment = doc.toObject(Appointment.class).withId(itemId);

                        if (status.equalsIgnoreCase("history") && !appointment.getStatus().equalsIgnoreCase("upcoming")) {
                            appointmentList.add(appointment);
                        } else {
                            if (appointment.getStatus().equalsIgnoreCase(status)) {
                                appointmentList.add(appointment);
                            }
                        }
                    }
                }

                result.setValue(appointmentList);
            }
        });
    }
}

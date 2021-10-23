package com.example.smedy.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.smedy.helper.Const;
import com.example.smedy.model.Psychologist;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PsychologistRepository {

    private static PsychologistRepository repository;
    private ArrayList<Psychologist> listPsychologist = new ArrayList<>();
    private MutableLiveData<ArrayList<Psychologist>> result = new MutableLiveData<>();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String TAG = "Repo";

    private PsychologistRepository() {
    }

    public static PsychologistRepository getInstance() {
        if (repository == null) {
            repository = new PsychologistRepository();
        }
        return repository;
    }

    public MutableLiveData<ArrayList<Psychologist>> getPsychologistData() {

        loadDatabase();

        return result;
    }

    private void loadDatabase() {

        db.collection(Const.DB_PSYCHOLOGIST).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                listPsychologist.clear();
                if (!queryDocumentSnapshots.isEmpty()) {
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot documentSnapshot : list) {
                        listPsychologist.add(documentSnapshot.toObject(Psychologist.class));
                    }

                    result.setValue(listPsychologist);
                    Log.e(TAG, "onSuccess: added");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "onFailure", e);
            }
        });
    }
}

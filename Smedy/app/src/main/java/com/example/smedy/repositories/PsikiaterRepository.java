package com.example.smedy.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.smedy.model.Psikiater;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PsikiaterRepository {

    private static PsikiaterRepository repository;
    private ArrayList<Psikiater> listPsikiater = new ArrayList<>();
    private MutableLiveData<ArrayList<Psikiater>> result = new MutableLiveData<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "Repo";

    private PsikiaterRepository(){}

    public static PsikiaterRepository getInstance(){
        if (repository == null){
            repository = new PsikiaterRepository();
        }
        return repository;
    }

    public MutableLiveData<ArrayList<Psikiater>> getPsikiaterData(){

        loadDatabase();

        result.setValue(listPsikiater);
        return result;
    }

    private void loadDatabase(){

        db.collection("psikiater").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                listPsikiater.clear();
                if (!queryDocumentSnapshots.isEmpty()){
                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot documentSnapshot : list){
                        listPsikiater.add(documentSnapshot.toObject(Psikiater.class));
                    }

                    result.postValue(listPsikiater);
                    Log.e(TAG,"onSuccess: added");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG,"onFailure",e);
            }
        });
    }
}

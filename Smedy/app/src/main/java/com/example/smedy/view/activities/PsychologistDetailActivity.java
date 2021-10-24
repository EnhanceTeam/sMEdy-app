package com.example.smedy.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smedy.R;
import com.example.smedy.adapter.PsychologistAdapter;
import com.example.smedy.model.Psychologist;
import com.example.smedy.view.RegisterActivity;
import com.example.smedy.viewmodel.PsychologistViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PsychologistDetailActivity extends AppCompatActivity {

    private Toolbar toolbarPsychologistActivity;
    private TextView txtNamePsychologistActivity, txtSpecialistPsychologistActivity, txtEducationPsychologistActivity, txtExperiencePsychologistActivity, txtLocationPsychologistActivity, txtRatingPsychologistActivity, txtPhonePsychologistActivity, txtEmailPsychologistActivity;
    private ImageView imgProfilePsychologistActivity;
    private Button btnMakeAppointmentPsychologistActivity,BtnWH1, BtnWH2, BtnWH3;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseFirestore fStore;
    String userID;

    private String namaPsikolog, spesialis, foto, jam, tanggal,status;

    private CalendarView calendarAppointment;

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psychologist_detail);

        initialize();
        setListener();
    }

    private void setListener() {
        btnMakeAppointmentPsychologistActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                Psychologist psychologist = intent.getParcelableExtra("psychologist");
                namaPsikolog = psychologist.getNama();
                spesialis = psychologist.getSpecialist();
                foto = psychologist.getFoto();
                if (jam == null){
                    jam = "08.00";
                }
                status = "upcoming";


                DocumentReference userReference = fStore.collection("appointment").document(userID).collection("history").document();
                Map<String, Object> user_info = new HashMap<>();
                user_info.put("namaPsikolog", namaPsikolog);
                user_info.put("spesialis", spesialis);
                user_info.put("foto", foto);
                user_info.put("jam", jam);
                user_info.put("tanggal", tanggal);
                user_info.put("status", status);
                userReference.set(user_info).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(PsychologistDetailActivity.this, "Appointment Submitted", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(PsychologistDetailActivity.this, "Failed to make Appointment", Toast.LENGTH_SHORT).show();
                        Log.e("error", e.toString());
                    }
                });
                finish();
            }
        });

        BtnWH1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnWH1.setTextColor(getColor(R.color.white));
                BtnWH1.setBackgroundColor(getColor(R.color.primary_500));

                BtnWH2.setTextColor(getColor(R.color.dark_grey));
                BtnWH2.setBackgroundColor(getColor(R.color.white_smoke));

                BtnWH3.setTextColor(getColor(R.color.dark_grey));
                BtnWH3.setBackgroundColor(getColor(R.color.white_smoke));

                jam = BtnWH1.getText().toString();
            }
        });

        BtnWH2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnWH1.setTextColor(getColor(R.color.dark_grey));
                BtnWH1.setBackgroundColor(getColor(R.color.white_smoke));

                BtnWH2.setTextColor(getColor(R.color.white));
                BtnWH2.setBackgroundColor(getColor(R.color.primary_500));

                BtnWH3.setTextColor(getColor(R.color.dark_grey));
                BtnWH3.setBackgroundColor(getColor(R.color.white_smoke));

                jam = BtnWH2.getText().toString();
            }
        });

        BtnWH3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnWH1.setTextColor(getColor(R.color.dark_grey));
                BtnWH1.setBackgroundColor(getColor(R.color.white_smoke));

                BtnWH2.setTextColor(getColor(R.color.dark_grey));
                BtnWH2.setBackgroundColor(getColor(R.color.white_smoke));

                BtnWH3.setTextColor(getColor(R.color.white));
                BtnWH3.setBackgroundColor(getColor(R.color.primary_500));

                jam = BtnWH3.getText().toString();
            }
        });
    }

    private void initialize() {
        Intent intent = getIntent();
        Psychologist psychologist = intent.getParcelableExtra("psychologist");

        toolbarPsychologistActivity = findViewById(R.id.toolbarPsychologistActivity);
        txtNamePsychologistActivity = findViewById(R.id.txtNamePsychologistActivity);
        txtSpecialistPsychologistActivity = findViewById(R.id.txtSpecialistPsychologistActivity);
        txtEducationPsychologistActivity = findViewById(R.id.txtEducationPsychologistActivity);
        txtExperiencePsychologistActivity = findViewById(R.id.txtExperiencePsychologistActivity);
        txtLocationPsychologistActivity = findViewById(R.id.txtLocationPsychologistActivity);
        imgProfilePsychologistActivity = findViewById(R.id.imgProfilePsychologistActivity);
        txtRatingPsychologistActivity = findViewById(R.id.txtRatingPsychologistActivity);
        txtPhonePsychologistActivity = findViewById(R.id.txtPhonePsychologistActivity);
        txtEmailPsychologistActivity = findViewById(R.id.txtEmailPsychologistActivity);
        BtnWH1 = findViewById(R.id.BtnWH1);
        BtnWH2 = findViewById(R.id.BtnWH2);
        BtnWH3 = findViewById(R.id.BtnWH3);
        btnMakeAppointmentPsychologistActivity = findViewById(R.id.btnMakeAppointmentPsychologistActivity);
        calendarAppointment = findViewById(R.id.calendarAppointment);

        //Tanggal
        calendarAppointment.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarAppointment, int i, int i1, int i2) {
                tanggal = i2 + "/" + (i1+1) + "/" + i;
                Log.d("calendarAppointment", tanggal);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        userID = user.getUid();

        setSupportActionBar(toolbarPsychologistActivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Glide.with(getApplicationContext()).load(psychologist.getFoto()).into(imgProfilePsychologistActivity);
        txtNamePsychologistActivity.setText(psychologist.getNama());
        txtSpecialistPsychologistActivity.setText(psychologist.getSpecialist());
        txtEducationPsychologistActivity.setText(psychologist.getLulusan());
        txtExperiencePsychologistActivity.setText(psychologist.getTahun() + "y");
        txtLocationPsychologistActivity.setText(psychologist.getLokasi());
        txtRatingPsychologistActivity.setText(psychologist.getRating());
        txtPhonePsychologistActivity.setText(psychologist.getNoTelp());
        txtEmailPsychologistActivity.setText(psychologist.getEmail());

        BtnWH1.setTextColor(getColor(R.color.white));
        BtnWH1.setBackgroundColor(getColor(R.color.primary_500));

        BtnWH2.setTextColor(getColor(R.color.dark_grey));
        BtnWH2.setBackgroundColor(getColor(R.color.white_smoke));

        BtnWH3.setTextColor(getColor(R.color.dark_grey));
        BtnWH3.setBackgroundColor(getColor(R.color.white_smoke));

        jam = BtnWH1.getText().toString();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
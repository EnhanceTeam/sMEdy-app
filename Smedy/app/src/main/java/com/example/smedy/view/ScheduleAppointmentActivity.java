package com.example.smedy.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smedy.R;

public class ScheduleAppointmentActivity extends AppCompatActivity {

    private TextView txtViewNamaScheduleAppointment,txtViewLulusanScheduleAppointment
            ,txtViewPengalamanScheduleAppointment,txtViewBiayaScheduleAppointment
            , txtViewLokasiScheduleAppointment;
    private ImageView imgFotoScheduleAppointment;
    private Button btnMakeScheduleAppointment;
    private String nama, tahun, lulusan, biaya, lokasi, foto;

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_appointment);

        txtViewNamaScheduleAppointment = findViewById(R.id.txtViewNamaScheduleAppointment);
        txtViewLulusanScheduleAppointment = findViewById(R.id.txtViewLulusanScheduleAppointment);
        txtViewPengalamanScheduleAppointment = findViewById(R.id.txtViewPengalamanScheduleAppointment);
        txtViewBiayaScheduleAppointment = findViewById(R.id.txtViewBiayaScheduleAppointment);
        txtViewLokasiScheduleAppointment = findViewById(R.id.txtViewLokasiScheduleAppointment);
        imgFotoScheduleAppointment = findViewById(R.id.imgFotoScheduleAppointment);
        btnMakeScheduleAppointment = findViewById(R.id.btnMakeScheduleAppointment);

        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        lulusan = intent.getStringExtra("lulusan");
        tahun = intent.getStringExtra("tahun");
        biaya = intent.getStringExtra("biaya");
        lokasi = intent.getStringExtra("lokasi");
        foto = intent.getStringExtra("foto");

        Glide.with(getApplicationContext()).load(foto).into(imgFotoScheduleAppointment);
        txtViewNamaScheduleAppointment.setText(nama);
        txtViewLulusanScheduleAppointment.setText(lulusan);
        txtViewPengalamanScheduleAppointment.setText(tahun + " year");
        txtViewBiayaScheduleAppointment.setText(biaya);
        txtViewLokasiScheduleAppointment.setText(lokasi);

        btnMakeScheduleAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
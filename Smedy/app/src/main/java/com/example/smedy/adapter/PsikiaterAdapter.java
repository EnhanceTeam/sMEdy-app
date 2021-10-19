package com.example.smedy.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.smedy.R;
import com.example.smedy.model.Psikiater;
import com.example.smedy.view.MakeAppointmentFragment;
import com.example.smedy.view.ScheduleAppointmentActivity;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class PsikiaterAdapter extends RecyclerView.Adapter<PsikiaterAdapter.CardViewViewHolder>{

    private ArrayList<Psikiater> listPsikiater;
    private Context context;

    public PsikiaterAdapter(ArrayList<Psikiater> listPsikiater, Context context) {
        this.listPsikiater = listPsikiater;
        this.context = context;
    }

    @NonNull
    @Override
    public PsikiaterAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_psikiater, parent,false);
        return new PsikiaterAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PsikiaterAdapter.CardViewViewHolder holder, int position) {
        Psikiater psikiater = listPsikiater.get(position);

        holder.TxtNamaCvPsikiater.setText(psikiater.getNama());
        holder.TxtTahunCvPsikiater.setText(String.valueOf(psikiater.getTahun()) + " year");
        holder.TxtLokasiCvPsikiater.setText(String.valueOf(psikiater.getLokasi()));
        Glide.with(context).load(psikiater.getFoto()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.ImgFotoCvPsikiater);

        holder.CvPsikiater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ScheduleAppointmentActivity.class);
                intent.putExtra("nama",psikiater.getNama());
                intent.putExtra("tahun",psikiater.getTahun());
                intent.putExtra("foto",psikiater.getFoto());
                intent.putExtra("lokasi",psikiater.getLokasi());
                intent.putExtra("lulusan",psikiater.getLulusan());
                intent.putExtra("biaya",psikiater.getBiaya());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPsikiater.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView ImgFotoCvPsikiater;
        TextView TxtNamaCvPsikiater, TxtTahunCvPsikiater, TxtLokasiCvPsikiater;
        CardView CvPsikiater;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            ImgFotoCvPsikiater = itemView.findViewById(R.id.ImgFotoCvPsikiater);
            TxtNamaCvPsikiater = itemView.findViewById(R.id.TxtNamaCvPsikiater);
            TxtTahunCvPsikiater = itemView.findViewById(R.id.TxtTahunCvPsikiater);
            TxtLokasiCvPsikiater = itemView.findViewById(R.id.TxtLokasiCvPsikiater);
            CvPsikiater = itemView.findViewById(R.id.CvPsikiater);
        }
    }
}
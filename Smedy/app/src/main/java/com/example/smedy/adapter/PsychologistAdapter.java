package com.example.smedy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.smedy.R;
import com.example.smedy.model.Psychologist;
import com.example.smedy.view.activities.PsychologistDetailActivity;

import java.util.ArrayList;

public class PsychologistAdapter extends RecyclerView.Adapter<PsychologistAdapter.CardViewViewHolder> {

    private ArrayList<Psychologist> listPsychologist;
    private Context context;

    public PsychologistAdapter(ArrayList<Psychologist> listPsychologist, Context context) {
        this.listPsychologist = listPsychologist;
        this.context = context;
    }

    @NonNull
    @Override
    public PsychologistAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_psychologist, parent, false);
        return new PsychologistAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PsychologistAdapter.CardViewViewHolder holder, int position) {
        Psychologist psychologist = listPsychologist.get(position);

        holder.TxtNamaCvPsikiater.setText(psychologist.getNama());
//        holder.TxtTahunCvPsikiater.setText(String.valueOf(psikiater.getTahun()) + " year");
        holder.TxtLokasiCvPsikiater.setText(String.valueOf(psychologist.getLokasi()));
        holder.TxtSpecialistCvPsikiater.setText(String.valueOf(psychologist.getSpecialist()));
        holder.TxtRatingCvPsikiater.setText(String.valueOf(psychologist.getRating()));
        Glide.with(context).load(psychologist.getFoto()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.ImgFotoCvPsikiater);

        holder.CvPsikiater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PsychologistDetailActivity.class);
                intent.putExtra("psychologist", psychologist);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPsychologist.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView ImgFotoCvPsikiater;
        TextView TxtNamaCvPsikiater, TxtSpecialistCvPsikiater, TxtLokasiCvPsikiater, TxtRatingCvPsikiater;
        CardView CvPsikiater;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);

            ImgFotoCvPsikiater = itemView.findViewById(R.id.ImgFotoCvPsikiater);
            TxtNamaCvPsikiater = itemView.findViewById(R.id.TxtNamaCvPsikiater);
            TxtSpecialistCvPsikiater = itemView.findViewById(R.id.TxtSpecialistCvPsikiater);
            TxtLokasiCvPsikiater = itemView.findViewById(R.id.TxtLokasiCvPsikiater);
            TxtRatingCvPsikiater = itemView.findViewById(R.id.TxtRatingCvPsikiater);
            CvPsikiater = itemView.findViewById(R.id.CvPsikiater);
        }
    }
}

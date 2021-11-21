package com.example.smedy.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smedy.R;
import com.example.smedy.helper.LoadingDialog;
import com.example.smedy.model.Meditation;
import com.example.smedy.view.MeditationPlayerActivity;

import java.util.ArrayList;

public class MeditationHomeAdapter extends RecyclerView.Adapter<MeditationHomeAdapter.MeditationViewHolder> {
    private ArrayList<Meditation> listMeditasi;
    private Context context;
    private LoadingDialog loadingDialog;
    private Activity activity;

    public MeditationHomeAdapter(ArrayList<Meditation> listMeditasi, Context context, Activity activity) {
        this.listMeditasi = listMeditasi;
        this.context = context;
        this.activity = activity;
        loadingDialog = new LoadingDialog(activity);
    }

    @NonNull
    @Override
    public MeditationHomeAdapter.MeditationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.meditation_fragment_viewholder, parent, false);
        return new MeditationHomeAdapter.MeditationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeditationHomeAdapter.MeditationViewHolder holder, int position) {
        final Meditation meditation = listMeditasi.get(position);

        holder.txtTitleMeditationFragmentViewholder.setText(meditation.getTitle());
        holder.txtDescMeditationFragmentViewholder.setText(meditation.getDescription());
        holder.txtDurationMeditationFragmentViewholder.setText(meditation.getDuration());

        holder.cvMeditationFragmentViewholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoading();

                Intent intent = new Intent(context, MeditationPlayerActivity.class);
                intent.putExtra("judul", meditation.getTitle());
                intent.putExtra("uri", meditation.getUrl());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.stopLoading();
                    }
                }, 500);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        int jumlah = 0;
        if(listMeditasi.size()>3){
            jumlah = 3;
        }else{
            jumlah = listMeditasi.size();
        }
        return jumlah;
    }

    public class MeditationViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitleMeditationFragmentViewholder, txtDescMeditationFragmentViewholder, txtDurationMeditationFragmentViewholder;
        CardView cvMeditationFragmentViewholder;

        public MeditationViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitleMeditationFragmentViewholder = itemView.findViewById(R.id.txtTitleMeditationFragmentViewholder);
            txtDescMeditationFragmentViewholder = itemView.findViewById(R.id.txtDescMeditationFragmentViewholder);
            txtDurationMeditationFragmentViewholder = itemView.findViewById(R.id.txtDurationMeditationFragmentViewholder);
            cvMeditationFragmentViewholder = itemView.findViewById(R.id.cvMeditationFragmentViewholder);
        }
    }
}


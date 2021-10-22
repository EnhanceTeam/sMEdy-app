package com.example.smedy.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smedy.R;
import com.example.smedy.model.Meditation;
import com.example.smedy.view.MeditationPlayerActivity;

import java.util.ArrayList;

public class MeditationAdapter extends RecyclerView.Adapter<MeditationAdapter.MeditationViewHolder> {
    private ArrayList<Meditation> listMeditasi;
    private Context context;

    public MeditationAdapter(ArrayList<Meditation> listMeditasi, Context context) {
        this.listMeditasi = listMeditasi;
        this.context = context;
    }

    @NonNull
    @Override
    public MeditationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.meditation_fragment_viewholder, parent, false);
        return new MeditationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeditationViewHolder holder, int position) {
        final Meditation meditation = listMeditasi.get(position);

        holder.txtTitleMeditationFragmentViewholder.setText(meditation.getTitle());
        holder.txtDescMeditationFragmentViewholder.setText(meditation.getDescription());
        holder.txtDurationMeditationFragmentViewholder.setText(meditation.getDuration());

        holder.cvMeditationFragmentViewholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MeditationPlayerActivity.class);
                intent.putExtra("dataMeditasi", meditation);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listMeditasi.size();
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

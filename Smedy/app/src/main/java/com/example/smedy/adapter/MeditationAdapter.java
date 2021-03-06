package com.example.smedy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smedy.R;
import com.example.smedy.model.Meditation;

import java.util.ArrayList;

public class MeditationAdapter extends RecyclerView.Adapter<MeditationAdapter.MeditationViewHolder> {
    private ArrayList<Meditation> listMeditasi;
    private Context context;
    private MeditationClickListener meditationClickListener;

    public MeditationAdapter(ArrayList<Meditation> listMeditasi, Context context, MeditationClickListener meditationClickListener) {
        this.listMeditasi = listMeditasi;
        this.context = context;
        this.meditationClickListener = meditationClickListener;
    }

    @NonNull
    @Override
    public MeditationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.meditation_fragment_viewholder, parent, false);
        return new MeditationViewHolder(view, meditationClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MeditationViewHolder holder, int position) {
        final Meditation meditation = listMeditasi.get(position);

        holder.txtTitleMeditationFragmentViewholder.setText(meditation.getTitle());
        holder.txtDescMeditationFragmentViewholder.setText(meditation.getDescription());
        holder.txtDurationMeditationFragmentViewholder.setText(meditation.getDuration());
    }

    @Override
    public int getItemCount() {
        return listMeditasi.size();
    }

    public class MeditationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtTitleMeditationFragmentViewholder, txtDescMeditationFragmentViewholder, txtDurationMeditationFragmentViewholder;
        CardView cvMeditationFragmentViewholder;
        MeditationClickListener meditationClickListener;

        public MeditationViewHolder(@NonNull View itemView, MeditationClickListener meditationClickListener) {
            super(itemView);

            this.meditationClickListener = meditationClickListener;

            txtTitleMeditationFragmentViewholder = itemView.findViewById(R.id.txtTitleMeditationFragmentViewholder);
            txtDescMeditationFragmentViewholder = itemView.findViewById(R.id.txtDescMeditationFragmentViewholder);
            txtDurationMeditationFragmentViewholder = itemView.findViewById(R.id.txtDurationMeditationFragmentViewholder);
            cvMeditationFragmentViewholder = itemView.findViewById(R.id.cvMeditationFragmentViewholder);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            meditationClickListener.onMeditationViewholderClick(getAdapterPosition());
        }
    }

    public interface MeditationClickListener{
        public void onMeditationViewholderClick(int position);
    }
}

package com.example.smedy.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smedy.R;
import com.example.smedy.model.Music;
import com.example.smedy.view.MusicPlayerActivity;

import java.util.ArrayList;

public class MusicHomeAdapter extends RecyclerView.Adapter<MusicHomeAdapter.MusicViewHolder>{
    private Context context;
    private ArrayList<Music> musicList;

    public MusicHomeAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(ArrayList<Music> musicList) {
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public MusicHomeAdapter.MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_music, parent, false);
        return new MusicHomeAdapter.MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicHomeAdapter.MusicViewHolder holder, int position) {

        final Music music = getMusicList().get(position);
        holder.musicTextViewTitle.setText(music.getTitle());
        holder.musicCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MusicPlayerActivity.class);
                intent.putExtra("title", music.getTitle());
                intent.putExtra("music", music.getMusic());
                intent.putExtra("token", music.getAccess_token());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        int jumlah = 0;
        if(musicList.size()>3){
            jumlah = 3;
        }else{
            jumlah = musicList.size();
        }
        return jumlah;
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {

        CardView musicCardView;
        TextView musicTextViewTitle;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);

            musicCardView = itemView.findViewById(R.id.musicCardView);
            musicTextViewTitle = itemView.findViewById(R.id.musicTextViewTitle);

        }
    }
}
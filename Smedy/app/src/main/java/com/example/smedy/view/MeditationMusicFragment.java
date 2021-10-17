package com.example.smedy.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.smedy.R;


public class MeditationMusicFragment extends Fragment {
    private CardView meditationMusicCardView;
    private Intent intent;
    private View view;

    public MeditationMusicFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_meditation_music, container, false);

        initialize();
        setListener();

        return view;
    }

    private void setListener() {
        meditationMusicCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(),MusicPlayerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initialize() {
        meditationMusicCardView = view.findViewById(R.id.meditationMusicCardView);
    }
}
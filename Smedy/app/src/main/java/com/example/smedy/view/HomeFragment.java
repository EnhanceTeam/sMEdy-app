package com.example.smedy.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.smedy.R;
import com.example.smedy.adapter.MeditationHomeAdapter;
import com.example.smedy.adapter.MusicHomeAdapter;
import com.example.smedy.adapter.PsikologHomeAdapter;
import com.example.smedy.model.Meditation;
import com.example.smedy.model.Music;
import com.example.smedy.model.Psychologist;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView RVPsychologistHome, RVMusicHome, RVMeditationHome;
    private ImageView ImgUserHome;
    private View view;

    private com.example.smedy.viewmodel.PsychologistViewModel psychologistViewModel;
    private PsikologHomeAdapter psikologHomeAdapter;

    private com.example.smedy.viewmodel.MusicViewModel musicViewModel;
    private MusicHomeAdapter musicHomeAdapter;

    private com.example.smedy.viewmodel.MeditationViewModel meditationViewModel;
    private MeditationHomeAdapter meditationHomeAdapter;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        ImgUserHome = view.findViewById(R.id.ImgUserHome);
        ImgUserHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        //Psikolog
        RVPsychologistHome = view.findViewById(R.id.RVPsychologistHome);

        psychologistViewModel = new ViewModelProvider(getActivity()).get(com.example.smedy.viewmodel.PsychologistViewModel.class);
        psychologistViewModel.getPsychologist();
        psychologistViewModel.getResultGetPsychologist().observe(getActivity(),showPsychologists);

        //Music
        RVMusicHome = view.findViewById(R.id.RVMusicHome);

        musicViewModel = new ViewModelProvider(getActivity()).get(com.example.smedy.viewmodel.MusicViewModel.class);
        musicViewModel.setResultGetMusic();
        musicViewModel.getResultGetMusic().observe(getActivity(), showMusic);

        //Meditasi
        RVMeditationHome = view.findViewById(R.id.RVMeditationHome);

        meditationViewModel = new ViewModelProvider(getActivity()).get(com.example.smedy.viewmodel.MeditationViewModel.class);
        meditationViewModel.getMeditation();
        meditationViewModel.getMedtationResult().observe(getActivity(), showGetMeditationResult);

        return view;
    }
    private Observer<ArrayList<Psychologist>> showPsychologists = new Observer<ArrayList<Psychologist>>() {
        @Override
        public void onChanged(ArrayList<Psychologist> ListPsychologists) {
            psikologHomeAdapter = new PsikologHomeAdapter(ListPsychologists, getActivity());
            RVPsychologistHome.setLayoutManager(new LinearLayoutManager(getActivity()));
            RVPsychologistHome.setAdapter(psikologHomeAdapter);
        }
    };

    private Observer<ArrayList<Music>> showMusic = new Observer<ArrayList<Music>>() {
        @Override
        public void onChanged(ArrayList<Music> ListMusic) {
            musicHomeAdapter = new MusicHomeAdapter(getActivity());
            musicHomeAdapter.setMusicList(ListMusic);
            RVMusicHome.setLayoutManager(new LinearLayoutManager(getActivity()));
            RVMusicHome.setAdapter(musicHomeAdapter);
        }
    };

    //Meditation
    private Observer<ArrayList<Meditation>> showGetMeditationResult = new Observer<ArrayList<Meditation>>() {
        @Override
        public void onChanged(ArrayList<Meditation> listMeditasi) {
            meditationHomeAdapter = new MeditationHomeAdapter(listMeditasi, getActivity());
            RVMeditationHome.setLayoutManager(new LinearLayoutManager(getActivity()));
            RVMeditationHome.setAdapter(meditationHomeAdapter);
        }
    };
}
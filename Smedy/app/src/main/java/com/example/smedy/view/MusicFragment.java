package com.example.smedy.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smedy.R;
import com.example.smedy.adapter.MusicAdapter;
import com.example.smedy.model.Music;
import com.example.smedy.viewmodel.MusicViewModel;

import java.util.ArrayList;

public class MusicFragment extends Fragment {

    private View view;
    private RecyclerView musicFragmentRV;
    private MusicViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_music, container, false);

        initialize();

        return view;
    }

    private void initialize() {
        musicFragmentRV = view.findViewById(R.id.musicFragmentRV);

        viewModel = new ViewModelProvider(getActivity()).get(MusicViewModel.class);
        viewModel.setResultGetMusic();
        viewModel.getResultGetMusic().observe(getActivity(), showMusic);
    }

    private Observer<ArrayList<Music>> showMusic = new Observer<ArrayList<Music>>() {
        @Override
        public void onChanged(ArrayList<Music> music) {
            musicFragmentRV.setLayoutManager(new LinearLayoutManager(getActivity()));
            MusicAdapter adapter = new MusicAdapter(getActivity(), getActivity());
            adapter.setMusicList(music);
            musicFragmentRV.setAdapter(adapter);
        }
    };
}
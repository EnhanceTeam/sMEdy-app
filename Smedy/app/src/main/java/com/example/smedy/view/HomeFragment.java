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
import com.example.smedy.adapter.PsikiaterAdapter;
import com.example.smedy.adapter.PsikologHomeAdapter;
import com.example.smedy.model.Psikiater;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView RVPsikologHome;
    private com.example.smedy.viewmodel.PsikiaterViewModel view_model;
    private View view;

    private ArrayList<Psikiater> listPsikiater = new ArrayList<>();
    private PsikologHomeAdapter psikologHomeAdapter;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        RVPsikologHome = view.findViewById(R.id.RVPsikologHome);

        view_model = new ViewModelProvider(getActivity()).get(com.example.smedy.viewmodel.PsikiaterViewModel.class);
        view_model.getPsikiater();
        view_model.getResultGetPsikiater().observe(getActivity(),showPsikiater);

        return view;
    }
    private Observer<ArrayList<Psikiater>> showPsikiater = new Observer<ArrayList<Psikiater>>() {
        @Override
        public void onChanged(ArrayList<Psikiater> Psikiater) {
            psikologHomeAdapter = new PsikologHomeAdapter(Psikiater, getActivity());
            RVPsikologHome.setLayoutManager(new LinearLayoutManager(getActivity()));
            RVPsikologHome.setAdapter(psikologHomeAdapter);
        }
    };
}
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
import com.example.smedy.model.Psikiater;
import com.example.smedy.viewmodel.PsikiaterViewModel;

import java.util.ArrayList;

public class MakeAppointmentFragment extends Fragment {

    private RecyclerView RVPsikiater;
    private PsikiaterViewModel view_model;
    private View view;

    private ArrayList<Psikiater> listPsikiater = new ArrayList<>();
    private PsikiaterAdapter psikiaterAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_make_appointment, container, false);

        RVPsikiater = view.findViewById(R.id.RVPsikiater);

        view_model = new ViewModelProvider(getActivity()).get(PsikiaterViewModel.class);
        view_model.getPsikiater();
        view_model.getResultGetPsikiater().observe(getActivity(),showPsikiater);

        return view;
    }

    private Observer<ArrayList<Psikiater>> showPsikiater = new Observer<ArrayList<Psikiater>>() {
        @Override
        public void onChanged(ArrayList<Psikiater> Psikiater) {
            psikiaterAdapter = new PsikiaterAdapter(Psikiater, getActivity());
            RVPsikiater.setLayoutManager(new LinearLayoutManager(getActivity()));
            RVPsikiater.setAdapter(psikiaterAdapter);
        }
    };
}
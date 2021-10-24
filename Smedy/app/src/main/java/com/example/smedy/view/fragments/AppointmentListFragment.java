package com.example.smedy.view.fragments;

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
import com.example.smedy.adapter.PsychologistAdapter;
import com.example.smedy.model.Psychologist;
import com.example.smedy.viewmodel.PsychologistViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentListFragment extends Fragment {

    private RecyclerView RVPsychologist;
    private PsychologistViewModel view_model;
    private View view;

    private ArrayList<Psychologist> listPsychologist = new ArrayList<>();
    private PsychologistAdapter psychologistAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AppointmentListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppointmentListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointmentListFragment newInstance(String param1, String param2) {
        AppointmentListFragment fragment = new AppointmentListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_appointment_list, container, false);

        RVPsychologist = view.findViewById(R.id.RVPsychologistApppointmentList);

        view_model = new ViewModelProvider(getActivity()).get(PsychologistViewModel.class);
        view_model.getPsychologist();
        view_model.getResultGetPsychologist().observe(getActivity(),showPsychologist);

        return view;
    }

    private Observer<ArrayList<Psychologist>> showPsychologist = new Observer<ArrayList<Psychologist>>() {
        @Override
        public void onChanged(ArrayList<Psychologist> Psychologist) {
            psychologistAdapter = new PsychologistAdapter(Psychologist, getActivity());
            RVPsychologist.setLayoutManager(new LinearLayoutManager(getActivity()));
            RVPsychologist.setAdapter(psychologistAdapter);
        }
    };
}
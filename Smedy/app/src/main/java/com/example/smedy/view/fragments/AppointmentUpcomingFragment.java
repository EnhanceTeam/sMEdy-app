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
import com.example.smedy.adapter.AppointmentAdapter;
import com.example.smedy.model.Appointment;
import com.example.smedy.viewmodel.AppointmentViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentUpcomingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentUpcomingFragment extends Fragment {

    private RecyclerView RVUpcomingApppointmentUpcoming;
    private AppointmentViewModel view_model;
    private View view;

    private ArrayList<Appointment> appointmentList = new ArrayList<>();
    private AppointmentAdapter appointmentAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AppointmentUpcomingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppointmentUpcomingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointmentUpcomingFragment newInstance(String param1, String param2) {
        AppointmentUpcomingFragment fragment = new AppointmentUpcomingFragment();
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
        view = inflater.inflate(R.layout.fragment_appointment_upcoming, container, false);

        RVUpcomingApppointmentUpcoming = view.findViewById(R.id.RVHistoryApppointmentHistory);

        view_model = new ViewModelProvider(getActivity()).get(AppointmentViewModel.class);
        view_model.getAppointment("upcoming");
        view_model.getResultGetAppointment().observe(getActivity(),showAppointment);

        return view;
    }

    private Observer<ArrayList<Appointment>> showAppointment = new Observer<ArrayList<Appointment>>() {
        @Override
        public void onChanged(ArrayList<Appointment> Appointment) {
            appointmentAdapter = new AppointmentAdapter(Appointment, getActivity());
            RVUpcomingApppointmentUpcoming.setLayoutManager(new LinearLayoutManager(getActivity()));
            RVUpcomingApppointmentUpcoming.setAdapter(appointmentAdapter);
        }
    };
}
package com.example.smedy.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.smedy.R;
import com.example.smedy.view.MeditationFragment;
import com.example.smedy.view.MusicFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AppointmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AppointmentFragment extends Fragment {

    private Button btnListAppointment, btnUpcomingAppointment, btnHistoryAppointment;
    private View view, divListAppointmentFragment, divUpcomingAppointmentFragment, divHistoryAppointmentFragment;
    private String menu;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AppointmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AppointmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AppointmentFragment newInstance(String param1, String param2) {
        AppointmentFragment fragment = new AppointmentFragment();
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
        view = inflater.inflate(R.layout.fragment_appointment, container, false);

        initView();
        setListener();

        return view;
    }

    private void setListener() {
        btnListAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menu.equals("upcoming")){
                    divListAppointmentFragment.setVisibility(View.VISIBLE);
                    divUpcomingAppointmentFragment.setVisibility(View.INVISIBLE);
                    divHistoryAppointmentFragment.setVisibility(View.INVISIBLE);
                    divListAppointmentFragment.setTranslationX(300);
                    divListAppointmentFragment.animate().translationX(0).setDuration(250).start();

                    loadFragment(new AppointmentListFragment());

                    menu = "list";
                }

                if(menu.equals("history")){
                    divListAppointmentFragment.setVisibility(View.VISIBLE);
                    divUpcomingAppointmentFragment.setVisibility(View.INVISIBLE);
                    divHistoryAppointmentFragment.setVisibility(View.INVISIBLE);
                    divListAppointmentFragment.setTranslationX(600);
                    divListAppointmentFragment.animate().translationX(0).setDuration(250).start();

                    loadFragment(new AppointmentListFragment());

                    menu = "list";
                }
            }
        });

        btnUpcomingAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menu.equals("list")){
                    divListAppointmentFragment.setVisibility(View.INVISIBLE);
                    divUpcomingAppointmentFragment.setVisibility(View.VISIBLE);
                    divHistoryAppointmentFragment.setVisibility(View.INVISIBLE);
                    divUpcomingAppointmentFragment.setTranslationX(-300);
                    divUpcomingAppointmentFragment.animate().translationX(0).setDuration(250).start();

                    loadFragment(new AppointmentUpcomingFragment());

                    menu = "upcoming";
                }

                if(menu.equals("history")){
                    divListAppointmentFragment.setVisibility(View.INVISIBLE);
                    divUpcomingAppointmentFragment.setVisibility(View.VISIBLE);
                    divHistoryAppointmentFragment.setVisibility(View.INVISIBLE);
                    divUpcomingAppointmentFragment.setTranslationX(300);
                    divUpcomingAppointmentFragment.animate().translationX(0).setDuration(250).start();

                    loadFragment(new AppointmentUpcomingFragment());

                    menu = "upcoming";
                }
            }
        });

        btnHistoryAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menu.equals("list")){
                    divListAppointmentFragment.setVisibility(View.INVISIBLE);
                    divUpcomingAppointmentFragment.setVisibility(View.INVISIBLE);
                    divHistoryAppointmentFragment.setVisibility(View.VISIBLE);
                    divHistoryAppointmentFragment.setTranslationX(-600);
                    divHistoryAppointmentFragment.animate().translationX(0).setDuration(250).start();

                    loadFragment(new AppointmentHistoryFragment());

                    menu = "history";
                }

                if(menu.equals("upcoming")){
                    divListAppointmentFragment.setVisibility(View.INVISIBLE);
                    divUpcomingAppointmentFragment.setVisibility(View.INVISIBLE);
                    divHistoryAppointmentFragment.setVisibility(View.VISIBLE);
                    divHistoryAppointmentFragment.setTranslationX(-300);
                    divHistoryAppointmentFragment.animate().translationX(0).setDuration(250).start();

                    loadFragment(new AppointmentHistoryFragment());

                    menu = "history";
                }
            }
        });
    }

    private void initView() {
        btnListAppointment = view.findViewById(R.id.btnListAppointment);
        btnUpcomingAppointment = view.findViewById(R.id.btnUpcomingAppointment);
        btnHistoryAppointment = view.findViewById(R.id.btnHistoryAppointment);
        divListAppointmentFragment = view.findViewById(R.id.divListAppointmentFragment);
        divUpcomingAppointmentFragment = view.findViewById(R.id.divUpcomingAppointmentFragment);
        divHistoryAppointmentFragment = view.findViewById(R.id.divHistoryAppointmentFragment);

        menu = "list";

        loadFragment(new AppointmentListFragment());
    }

    private void loadFragment(Fragment fragment) {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.consFragmentAppointmentFragment, fragment)
                .commit();
    }
}
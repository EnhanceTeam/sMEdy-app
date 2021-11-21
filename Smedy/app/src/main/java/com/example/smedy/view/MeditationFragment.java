package com.example.smedy.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.smedy.R;
import com.example.smedy.adapter.MeditationAdapter;
import com.example.smedy.helper.LoadingDialog;
import com.example.smedy.model.Meditation;
import com.example.smedy.viewmodel.MeditationViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeditationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeditationFragment extends Fragment implements MeditationAdapter.MeditationClickListener {
    private RecyclerView rvMeditationFragment;
    private MeditationAdapter adapter;
    private MeditationViewModel meditationViewModel;
    private ArrayList<Meditation> listMeditasi;

    private LoadingDialog loadingDialog;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MeditationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeditationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeditationFragment newInstance(String param1, String param2) {
        MeditationFragment fragment = new MeditationFragment();
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

        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.startLoading();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meditation, container, false);

        initVar(view);
        setViewModel();

        // Inflate the layout for this fragment
        return view;
    }

    private void setViewModel(){
        meditationViewModel.getMeditation();
        meditationViewModel.getMedtationResult().observe(getActivity(), showGetMeditationResult);
    }

    private Observer<ArrayList<Meditation>> showGetMeditationResult = new Observer<ArrayList<Meditation>>() {
        @Override
        public void onChanged(ArrayList<Meditation> meditations) {
            setAdapter(meditations);
            listMeditasi = meditations;
            loadingDialog.stopLoading();
        }
    };

    private void initVar(View view){
        rvMeditationFragment = view.findViewById(R.id.rvMeditationFragment);
        meditationViewModel = new ViewModelProvider(getActivity()).get(MeditationViewModel.class);
        listMeditasi = new ArrayList<>();
    }

    private void setAdapter(ArrayList<Meditation> listMeditasi){
        adapter = new MeditationAdapter(listMeditasi, getActivity(), this);
        rvMeditationFragment.setAdapter(adapter);
    }

    @Override
    public void onMeditationViewholderClick(int position) {
        loadingDialog.startLoading();

        Intent intent = new Intent(getActivity(), MeditationPlayerActivity.class);
        intent.putExtra("judul", listMeditasi.get(position).getTitle());
        intent.putExtra("uri", listMeditasi.get(position).getUrl());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingDialog.stopLoading();
            }
        }, 500);

        startActivity(intent);
    }
}
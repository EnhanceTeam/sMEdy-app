package com.example.smedy.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

<<<<<<< HEAD
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeditationMusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeditationMusicFragment extends Fragment {
    private Button btnMeditasiMeditationMusicFragment, btnMusikMeditationMusicFragment;
    private View divMeditasiMeditationMusicFragment, divMusikMeditationMusicFragment;
    private String menuBefore = "meditasi";
||||||| 0269d3d
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeditationMusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeditationMusicFragment extends Fragment {
=======
import com.example.smedy.R;
>>>>>>> 35827062c7de6a85bb95a61e0689ff93b330462d


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
                intent = new Intent(getActivity(),MusicActivity.class);
                startActivity(intent);
            }
        });
    }

<<<<<<< HEAD
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meditation_music, container, false);

        initVar(view);
        setListener();

        // Inflate the layout for this fragment
        return view;
    }

    private void setListener() {
        btnMeditasiMeditationMusicFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuBefore.equals("music")){
                    divMusikMeditationMusicFragment.setVisibility(View.INVISIBLE);
                    divMeditasiMeditationMusicFragment.setVisibility(View.VISIBLE);
                    divMeditasiMeditationMusicFragment.setTranslationX(300);
                    divMeditasiMeditationMusicFragment.animate().translationX(0).setDuration(250).start();

                    loadFragment(new MeditationFragment());

                    menuBefore = "meditasi";
                }
            }
        });

        btnMusikMeditationMusicFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menuBefore.equals("meditasi")){
                    divMusikMeditationMusicFragment.setVisibility(View.VISIBLE);
                    divMeditasiMeditationMusicFragment.setVisibility(View.INVISIBLE);
                    divMusikMeditationMusicFragment.setTranslationX(-300);
                    divMusikMeditationMusicFragment.animate().translationX(0).setDuration(250).start();

                    loadFragment(new MusicFragment());

                    menuBefore = "music";
                }
            }
        });
    }

    private void initVar(View view) {
        btnMeditasiMeditationMusicFragment = view.findViewById(R.id.btnMeditasiMeditationMusicFragment);
        btnMusikMeditationMusicFragment = view.findViewById(R.id.btnMusikMeditationMusicFragment);
        divMeditasiMeditationMusicFragment = view.findViewById(R.id.divMeditasiMeditationMusicFragment);
        divMusikMeditationMusicFragment = view.findViewById(R.id.divMusikMeditationMusicFragment);

        loadFragment(new MeditationFragment());
||||||| 0269d3d
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meditation_music, container, false);
=======
    private void initialize() {
        meditationMusicCardView = view.findViewById(R.id.meditationMusicCardView);
>>>>>>> 35827062c7de6a85bb95a61e0689ff93b330462d
    }

    private void loadFragment(Fragment fragment){
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.consFragmentMeditationMusicFragment, fragment)
                .commit();
    }


}
package com.example.smedy.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.smedy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MeditationMusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeditationMusicFragment extends Fragment {
    private Button btnMeditasiMeditationMusicFragment, btnMusikMeditationMusicFragment;
    private View divMeditasiMeditationMusicFragment, divMusikMeditationMusicFragment, view;
    private String menu = "meditasi";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MeditationMusicFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeditationMusicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MeditationMusicFragment newInstance(String param1, String param2) {
        MeditationMusicFragment fragment = new MeditationMusicFragment();
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
        view = inflater.inflate(R.layout.fragment_meditation_music, container, false);

        initVar();
        setListener();

        // Inflate the layout for this fragment
        return view;
    }

    private void initVar() {
        btnMeditasiMeditationMusicFragment = view.findViewById(R.id.btnMeditasiMeditationMusicFragment);
        btnMusikMeditationMusicFragment = view.findViewById(R.id.btnMusikMeditationMusicFragment);
        divMusikMeditationMusicFragment = view.findViewById(R.id.divMusikMeditationMusicFragment);
        divMeditasiMeditationMusicFragment = view.findViewById(R.id.divMeditasiMeditationMusicFragment);

        loadFragment(new MeditationFragment());
    }

    private void setListener() {
        btnMeditasiMeditationMusicFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menu.equals("musik")){
                    divMusikMeditationMusicFragment.setVisibility(View.INVISIBLE);
                    divMeditasiMeditationMusicFragment.setVisibility(View.VISIBLE);
                    divMeditasiMeditationMusicFragment.setTranslationX(300);
                    divMeditasiMeditationMusicFragment.animate().translationX(0).setDuration(250).start();

                    loadFragment(new MeditationFragment());

                    menu = "meditasi";
                }
            }
        });

        btnMusikMeditationMusicFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(menu.equals("meditasi")){
                    divMusikMeditationMusicFragment.setVisibility(View.VISIBLE);
                    divMeditasiMeditationMusicFragment.setVisibility(View.INVISIBLE);
                    divMusikMeditationMusicFragment.setTranslationX(-300);
                    divMusikMeditationMusicFragment.animate().translationX(0).setDuration(250).start();

                    loadFragment(new MusicFragment());

                    menu = "musik";
                }
            }
        });
    }

    private void loadFragment(Fragment fragment){
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.consFragmentMeditationMusicFragment, fragment)
                .commit();
    }
}
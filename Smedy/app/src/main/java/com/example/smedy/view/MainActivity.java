package com.example.smedy.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.smedy.R;
import com.example.smedy.view.fragments.AppointmentFragment;

public class MainActivity extends AppCompatActivity {
    private MeowBottomNavigation bottomNavbarMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVar();
        initNavbar();
    }

    private void initVar(){
        bottomNavbarMain = findViewById(R.id.bottomNavbarMain);
    }

    private void initNavbar(){
        bottomNavbarMain.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home_24));
        bottomNavbarMain.add(new MeowBottomNavigation.Model(2, R.drawable.ic_baseline_meditation_24));
        bottomNavbarMain.add(new MeowBottomNavigation.Model(3, R.drawable.ic_baseline_appointment_24));

        bottomNavbarMain.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;

                switch(item.getId()){
                    case 1:
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new MeditationMusicFragment();
                        break;
                    case 3:
                        fragment = new AppointmentFragment();
                        break;

                }

                loadFragment(fragment);
            }
        });

        bottomNavbarMain.show(1, true);

        bottomNavbarMain.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {

            }
        });

        bottomNavbarMain.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayoutMain, fragment)
                .commit();
    }
}
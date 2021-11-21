package com.example.smedy.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.example.smedy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashActivity extends AppCompatActivity {

    private ImageView splashImageUtara, splashImageBarat, splashImageTimur,
            splashImageTimurLaut, splashImageBaratLaut,
            splashImageBunder, splashImageNama;
    private FirebaseAuth auth;
    private FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        setAnimation();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(auth.getCurrentUser() != null){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }else{
                    startActivity(new Intent(getApplicationContext(), OnBoardingActivity.class));
                    finish();
                }

            }
        }, 3000);
        initView();


    }

    private void initView() {
        auth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        splashImageBunder = findViewById(R.id.splashImageBunder);
        splashImageUtara = findViewById(R.id.splashImageUtara);
        splashImageBarat = findViewById(R.id.splashImageBarat);
        splashImageBaratLaut = findViewById(R.id.splashImageBaratLaut);
        splashImageTimur = findViewById(R.id.splashImageTimur);
        splashImageTimurLaut = findViewById(R.id.splashImageTimurLaut);
        splashImageNama = findViewById(R.id.splashImageNama);

        //Y Translation
        splashImageBunder.setTranslationY(-100);
        splashImageUtara.setTranslationY(-100);
        splashImageBarat.setTranslationY(-100);
        splashImageTimur.setTranslationY(-100);
        splashImageBaratLaut.setTranslationY(-60);
        splashImageTimurLaut.setTranslationY(-60);
        splashImageNama.setTranslationY(80);

        //X Translation
        splashImageBarat.setTranslationX(-120);
        splashImageTimur.setTranslationX(120);
        splashImageBaratLaut.setTranslationX(-80);
        splashImageTimurLaut.setTranslationX(80);

//        //Alpha
        splashImageBunder.setAlpha(0.1F);
        splashImageUtara.setAlpha(0.1F);
        splashImageBarat.setAlpha(0.1F);
        splashImageTimur.setAlpha(0.1F);
        splashImageBaratLaut.setAlpha(0.1F);
        splashImageTimurLaut.setAlpha(0.1F);
        splashImageNama.setAlpha(0.1F);

        // Set Android Status Bar Color
        getWindow().setStatusBarColor(getColor(R.color.blue_800));

        // Set Android Status Bar Text Color
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void setAnimation() {
        //Animate 1
        splashImageBarat.animate().alpha(1).translationY(0).translationX(0).setDuration(2000).setStartDelay(500).start();
        splashImageBaratLaut.animate().alpha(1).translationY(0).translationX(0).setDuration(2000).setStartDelay(500).start();
        splashImageUtara.animate().alpha(1).translationY(0).setDuration(2000).setStartDelay(500).start();
        splashImageTimurLaut.animate().alpha(1).translationY(0).translationX(0).setDuration(2000).setStartDelay(500).start();
        splashImageTimur.animate().alpha(1).translationY(0).translationX(0).setDuration(2000).setStartDelay(500).start();
        splashImageNama.animate().alpha(1).translationY(0).setDuration(2000).setStartDelay(500).start();
        splashImageBunder.animate().alpha(1).translationY(0).setDuration(2000).setStartDelay(500).start();

    }


}
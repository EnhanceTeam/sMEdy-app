package com.example.smedy.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;

import com.example.smedy.R;
import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {

    private ImageView splashImageUtara, splashImageBarat, splashImageTimur,
            splashImageTimurLaut, splashImageBaratLaut,
            splashImageBunder, splashImageNama;
    private FirebaseAuth auth;


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

        splashImageBunder = findViewById(R.id.splashImageBunder);
        splashImageUtara = findViewById(R.id.splashImageUtara);
        splashImageBarat = findViewById(R.id.splashImageBarat);
        splashImageBaratLaut = findViewById(R.id.splashImageBaratLaut);
        splashImageTimur = findViewById(R.id.splashImageTimur);
        splashImageTimurLaut = findViewById(R.id.splashImageTimurLaut);
        splashImageNama = findViewById(R.id.splashImageNama);

        //Y Translation
        splashImageBunder.setTranslationY(-50);
        splashImageUtara.setTranslationY(-50);
        splashImageBarat.setTranslationY(-50);
        splashImageTimur.setTranslationY(-50);
        splashImageBaratLaut.setTranslationY(-30);
        splashImageTimurLaut.setTranslationY(-30);
        splashImageNama.setTranslationY(40);

        //X Translation
        splashImageBarat.setTranslationX(-60);
        splashImageTimur.setTranslationX(60);
        splashImageBaratLaut.setTranslationX(-40);
        splashImageTimurLaut.setTranslationX(40);

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
    }

    private void setAnimation() {
        //Animate 1
        splashImageBarat.animate().alpha(1).translationY(0).translationX(-10).setDuration(2000).setStartDelay(500).start();
        splashImageBaratLaut.animate().alpha(1).translationY(0).translationX(-20).setDuration(2000).setStartDelay(500).start();
        splashImageUtara.animate().alpha(1).translationY(0).setDuration(2000).setStartDelay(500).start();
        splashImageTimurLaut.animate().alpha(1).translationY(0).translationX(20).setDuration(2000).setStartDelay(500).start();
        splashImageTimur.animate().alpha(1).translationY(0).translationX(10).setDuration(2000).setStartDelay(500).start();
        splashImageNama.animate().alpha(1).translationY(20).setDuration(2000).setStartDelay(500).start();
        splashImageBunder.animate().alpha(1).translationY(0).setDuration(2000).setStartDelay(500).start();

    }


}
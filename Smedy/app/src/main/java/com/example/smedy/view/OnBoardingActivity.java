package com.example.smedy.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smedy.R;
import com.example.smedy.adapter.OnBoardingAdapter;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager onboardingViewPager;
    private LinearLayout onboardingLinearLayout;
    private TextView[] mdots;
    private Button onnoardingBtnBack, onboardingBtnNext;

    private OnBoardingAdapter onboardingAdapter;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        initView();
        addDotsIndicator(0);
        onboardingViewPager.addOnPageChangeListener(viewListener);

        setListener();
    }

    private void initView(){
        onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingLinearLayout = findViewById(R.id.onboardingLinearLayout);

        onnoardingBtnBack = findViewById(R.id.onnoardingBtnBack);
        onboardingBtnNext = findViewById(R.id.onboardingBtnNext);

        onboardingAdapter = new OnBoardingAdapter(this);
        onboardingViewPager.setAdapter(onboardingAdapter);

    }

    public void addDotsIndicator(int position){
        mdots = new TextView[3];
        onboardingLinearLayout.removeAllViews();

        for (int i = 0; i <mdots.length; i++){

            mdots[i] = new TextView(this);
            mdots[i].setText("\u2022");
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getColor(R.color.state_disabled));

            onboardingLinearLayout.addView(mdots[i]);

        }

        if(mdots.length > 0 ){
            mdots[position].setTextColor(getColor(R.color.primary_500));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {

            addDotsIndicator(i);

            currentPage = i;
            if(i==0){

                onnoardingBtnBack.setEnabled(false);
                onboardingBtnNext.setEnabled(true);
                onnoardingBtnBack.setVisibility(View.INVISIBLE);

                onboardingBtnNext.setText("Next");
                onnoardingBtnBack.setText("");

            }else if(i== mdots.length - 1 ){

                onnoardingBtnBack.setEnabled(true);
                onboardingBtnNext.setEnabled(true);
                onnoardingBtnBack.setVisibility(View.VISIBLE);

                onboardingBtnNext.setText("Finish");
                onnoardingBtnBack.setText("Back");

            }else{

                onnoardingBtnBack.setEnabled(true);
                onboardingBtnNext.setEnabled(true);
                onnoardingBtnBack.setVisibility(View.VISIBLE);

                onboardingBtnNext.setText("Next");
                onnoardingBtnBack.setText("Back");

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void setListener(){
        onboardingBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPage!=mdots.length - 1){
                    onboardingViewPager.setCurrentItem(currentPage + 1);
                }else{
                    Intent intent = new Intent(OnBoardingActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        onnoardingBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onboardingViewPager.setCurrentItem(currentPage - 1);
            }
        });
    }
}
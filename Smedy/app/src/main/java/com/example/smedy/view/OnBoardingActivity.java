package com.example.smedy.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
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
    private Button onboardingBtnSkip, onboardingBtnNext, onboardingBtnGetStarted;

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

    private void initView() {
        onboardingViewPager = findViewById(R.id.onboardingViewPager);
        onboardingLinearLayout = findViewById(R.id.onboardingLinearLayout);

        onboardingBtnSkip = findViewById(R.id.onboardingBtnSkip);
        onboardingBtnNext = findViewById(R.id.onboardingBtnNext);
        onboardingBtnGetStarted = findViewById(R.id.onboardingBtnGetStarted);

        onboardingAdapter = new OnBoardingAdapter(this);
        onboardingViewPager.setAdapter(onboardingAdapter);

    }

    public void addDotsIndicator(int position) {
        mdots = new TextView[3];
        onboardingLinearLayout.removeAllViews();

        for (int i = 0; i < mdots.length; i++) {

            mdots[i] = new TextView(this);
            mdots[i].setText("\u2022");
            mdots[i].setTextSize(35);
            mdots[i].setTextColor(getColor(R.color.primary_200));

            onboardingLinearLayout.addView(mdots[i]);

        }

        if (mdots.length > 0) {
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
            if (i == mdots.length - 1) {
                onboardingBtnSkip.setVisibility(View.GONE);
                onboardingBtnNext.setVisibility(View.GONE);
                onboardingBtnGetStarted.setVisibility(View.VISIBLE);
            } else {
                onboardingBtnSkip.setVisibility(View.VISIBLE);
                onboardingBtnNext.setVisibility(View.VISIBLE);
                onboardingBtnGetStarted.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void setListener() {
        onboardingBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onboardingViewPager.setCurrentItem(mdots.length - 1);
            }
        });

        onboardingBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentPage != mdots.length - 1) {
                    onboardingViewPager.setCurrentItem(currentPage + 1);
                }
            }
        });

        onboardingBtnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OnBoardingActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
package com.example.smedy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.smedy.R;

public class OnBoardingAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public OnBoardingAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.onboarding_1_meditation,
            R.drawable.onboarding_2_audio_player,
            R.drawable.onboarding_3_social_serenity
    };

    public String [] slide_headings = {
            "Audio Meditasi",
            "Musik Relaksasi",
            "Konsultasi"
    };

    public String [] slide_description = {
            "Temukan berbagai audio meditasi yang dapat menenangkan hati dan pikiran",
            "Dengarkan musik relaksasi dimana saja dan kapan saja",
            "Tanyakan masalahmu pada ahlinya dan temukan solusi yang terbaik"
    };



    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.onboarding_layout, container, false);

        ImageView slide_Image = view.findViewById(R.id.onboardingImageView);
        TextView slide_Heading = view.findViewById(R.id.onboardingTextViewHeading);
        TextView slide_desc = view.findViewById(R.id.onboardingTextViewDescription);

        slide_Image.setImageResource(slide_images[position]);
        slide_Heading.setText(slide_headings[position]);
        slide_desc.setText(slide_description[position]);

        container.addView(view);

        return  view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout)object);
    }
}

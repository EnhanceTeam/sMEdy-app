package com.example.smedy.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.smedy.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {
    private TextView txtNamaUserProfile;
    private Button btnLogoutProfile;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_profile, container, false);

        initView(view);
        setComponent();
        setListener();

        // Inflate the layout for this fragment
        return view;
    }

    private void setComponent() {
        if(user != null){
            txtNamaUserProfile.setText("Hello user!");
            btnLogoutProfile.setText("Logout");
        }else{
            txtNamaUserProfile.setText("Please Log in first");
            btnLogoutProfile.setText("Login");
        }
    }

    private void initView(View view) {
        txtNamaUserProfile = view.findViewById(R.id.txtNamaUserProfile);
        btnLogoutProfile = view.findViewById(R.id.btnLogoutProfile);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    private void setListener(){
        btnLogoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user != null) {
                    auth.signOut();
                }

                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
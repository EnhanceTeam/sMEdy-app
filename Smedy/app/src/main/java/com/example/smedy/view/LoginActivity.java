package com.example.smedy.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smedy.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private TextView logTextViewTitle, logTextViewDesc, logTextViewRegister;
    private TextInputLayout logTextInputEmail, logTextInputPassword;
    private Button logButtonLogin;
    private CardView logCardViewGoogle;
    private Intent intent;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
        clearError();
        setAnimation();

        mAuth = FirebaseAuth.getInstance();

        setTextWatcher();
        setListener();

    }

    private void setListener() {
        logTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(),RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = logTextInputEmail.getEditText().getText().toString().trim();
                String password = logTextInputPassword.getEditText().getText().toString().trim();

                if(!email.isEmpty()&&!password.isEmpty()){
                    mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            intent = new Intent(getBaseContext(),FiretestActivity.class);
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();

                            clearError();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                            Log.d("error", e.toString());
                        }
                    });
                }

            }
        });

    }

    private void setTextWatcher() {
        TextWatcher tmpWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String email = logTextInputEmail.getEditText().getText().toString().trim();
                String password = logTextInputPassword.getEditText().getText().toString().trim();

                if(!email.isEmpty() && !password.isEmpty()){
                    logButtonLogin.setEnabled(true);
                    logButtonLogin.setClickable(true);
                } else {
                    logButtonLogin.setEnabled(false);
                    logButtonLogin.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        };

        logTextInputEmail.getEditText().addTextChangedListener(tmpWatcher);
        logTextInputPassword.getEditText().addTextChangedListener(tmpWatcher);
    }

    private void clearError() {
        logTextInputEmail.getEditText().setText("");
        logTextInputEmail.setError("");
        logTextInputPassword.getEditText().setText("");
        logTextInputPassword.setError("");
    }

    private void setAnimation() {
        logTextViewTitle.setTranslationX(800);
        logTextViewDesc.setTranslationX(800);
        logTextInputEmail.setTranslationX(800);
        logTextInputPassword.setTranslationX(800);
        logButtonLogin.setTranslationX(800);
        logCardViewGoogle.setTranslationX(800);

        logTextViewTitle.setAlpha(0);
        logTextViewDesc.setAlpha(0);
        logTextInputEmail.setAlpha(0);
        logTextInputPassword.setAlpha(0);
        logButtonLogin.setAlpha(0);
        logCardViewGoogle.setAlpha(0);

        logTextViewTitle.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(200).start();
        logTextViewDesc.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(250).start();
        logTextInputEmail.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(500).start();
        logTextInputPassword.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(700).start();
        logButtonLogin.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(900).start();
        logCardViewGoogle.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(1100).start();
    }

    private void initialize() {
        logTextViewTitle = findViewById(R.id.logTextViewTitle);
        logTextViewDesc = findViewById(R.id.logTextViewDesc);
        logTextInputEmail = findViewById(R.id.logTextInputEmail);
        logTextInputPassword = findViewById(R.id.logTextInputPassword);
        logButtonLogin = findViewById(R.id.logButtonLogin);
        logCardViewGoogle = findViewById(R.id.logCardViewGoogle);
        logTextViewRegister = findViewById(R.id.logTextViewRegister);
    }
}
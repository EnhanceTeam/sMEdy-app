package com.example.smedy.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smedy.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;

    private TextInputLayout regTextInputUsername, regTextInputEmail, regTextInputPassword;
    private Button regButtonRegister;
    private TextView regTextViewTitle, regTextViewDesc, regTextViewLogin;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initialize();
        clearError();
        setAnimation();

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        setTextWatcher();
        setListener();

    }

    private void setAnimation() {
        regTextViewTitle.setTranslationX(800);
        regTextViewDesc.setTranslationX(800);
        regTextInputUsername.setTranslationX(800);
        regTextInputEmail.setTranslationX(800);
        regTextInputPassword.setTranslationX(800);
        regButtonRegister.setTranslationX(800);

        regTextViewTitle.setAlpha(0);
        regTextViewDesc.setAlpha(0);
        regTextInputUsername.setAlpha(0);
        regTextInputEmail.setAlpha(0);
        regTextInputPassword.setAlpha(0);
        regButtonRegister.setAlpha(0);

        regTextViewTitle.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(200).start();
        regTextViewDesc.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(250).start();
        regTextInputUsername.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(500).start();
        regTextInputEmail.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(700).start();
        regTextInputPassword.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(900).start();
        regButtonRegister.animate().translationX(0).alpha(1).setDuration(1200).setStartDelay(1100).start();
    }

    private void setListener() {
        regTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        regButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = regTextInputUsername.getEditText().getText().toString().trim();
                String email = regTextInputEmail.getEditText().getText().toString().trim();
                String password = regTextInputPassword.getEditText().getText().toString().trim();

                Boolean validateUsername = false, validateEmail = false, validatePassword = false;

                if (username.isEmpty()) {
                    regTextInputUsername.setError("Please fill the username column");
                    validateUsername = false;
                } else {
                    regTextInputUsername.setError("");
                    validateUsername = true;
                }

                Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");

                if (email.isEmpty()) {
                    regTextInputEmail.setError("Please fill the email column");
                    validateEmail = false;
                } else {
                    if (!EMAIL_ADDRESS_PATTERN.matcher(email).matches()) {
                        regTextInputEmail.setError("Wrong email format");
                        validateEmail = false;
                    } else {
                        regTextInputEmail.setError("");
                        validateEmail = true;
                    }
                }

                Pattern PASSWORD_PATTERN = Pattern.compile("^"
//                        + "(?=.*[0-9])"       //a digit must occur at least once
                        + "(?=.*[a-z])"       //a lower case letter must occur at least once
//                        + "(?=.*[A-Z])"       //an upper case letter must occur at least once
//                        + "(?=.*[@#$%^&+=])"  //a special character must occur at least once you can replace with your special characters
                        + "(?=\\S+$)"         //no whitespace allowed in the entire string
                        + ".{7,20}"           //anything, at least eight places though, max twenty
                        + "$");

                if (password.isEmpty()) {
                    regTextInputPassword.setError("Please fill the password column");
                    validatePassword = false;
                } else {
                    if (password.length() < 8 || password.length() > 20) {
                        regTextInputPassword.setError("Must contains 8-20 Characters");
                        validatePassword = false;
                    } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
                        regTextInputPassword.setError("Must contains at least lowercase with no whitespace");
                        validatePassword = false;
                    } else {
                        regTextInputPassword.setError("");
                        validatePassword = true;
                    }
                }

                if (validateUsername && validateEmail && validatePassword) {

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                userID = mAuth.getCurrentUser().getUid();
                                DocumentReference userReference = fStore.collection("user_collection").document(userID);
                                Map<String, Object> user_info = new HashMap<>();
                                user_info.put("username", username);
                                user_info.put("email", email);
//                                user_info.put("password", password);

                                userReference.set(user_info).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(RegisterActivity.this, "Account Registered!", Toast.LENGTH_SHORT).show();

                                        FirebaseAuth.getInstance().signOut();
                                        FirebaseFirestore.getInstance().terminate();

                                        clearError();

                                        intent = new Intent(getBaseContext(), LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(RegisterActivity.this, "Failed to Register", Toast.LENGTH_SHORT).show();
                                        Log.e("error", e.toString());
                                    }
                                });
                            } else {
                                Toast.makeText(RegisterActivity.this, "Failed to Create Account!", Toast.LENGTH_SHORT).show();
                                Log.e("error", "error firebase", task.getException());
                            }

                        }
                    });

                }

            }
        });

    }

    private void clearError() {
        regTextInputUsername.getEditText().setText("");
        regTextInputUsername.setError("");
        regTextInputEmail.getEditText().setText("");
        regTextInputEmail.setError("");
        regTextInputPassword.getEditText().setText("");
        regTextInputPassword.setError("");
    }

    private void setTextWatcher() {
        TextWatcher tmpWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String username = regTextInputUsername.getEditText().getText().toString().trim();
                String email = regTextInputEmail.getEditText().getText().toString().trim();
                String password = regTextInputPassword.getEditText().getText().toString().trim();

                if (!username.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    regButtonRegister.setEnabled(true);
                    regButtonRegister.setClickable(true);
                } else {
                    regButtonRegister.setEnabled(false);
                    regButtonRegister.setClickable(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        regTextInputUsername.getEditText().addTextChangedListener(tmpWatcher);
        regTextInputEmail.getEditText().addTextChangedListener(tmpWatcher);
        regTextInputPassword.getEditText().addTextChangedListener(tmpWatcher);
    }

    private void initialize() {
        regTextViewTitle = findViewById(R.id.regTextViewTitle);
        regTextViewDesc = findViewById(R.id.regTextViewDesc);
        regTextInputUsername = findViewById(R.id.regTextInputUsername);
        regTextInputEmail = findViewById(R.id.regTextInputEmail);
        regTextInputPassword = findViewById(R.id.regTextInputPassword);
        regButtonRegister = findViewById(R.id.regButtonRegister);
        regTextViewLogin = findViewById(R.id.regTextViewLogin);

    }
}
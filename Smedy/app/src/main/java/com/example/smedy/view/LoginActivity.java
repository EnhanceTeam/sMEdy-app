package com.example.smedy.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.smedy.R;
import com.example.smedy.helper.Const;
import com.example.smedy.helper.LoadingDialog;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

//    private static final int RC_SIGN_IN = 444;
//    private static final String TAG = "GoogleSignInActivity";
    FirebaseAuth mAuth;
//    FirebaseFirestore fStore;
//    String userID;
//    GoogleSignInClient googleSignInClient;
//    GoogleApiClient mGoogleApiClient;
//    FirebaseAuth.AuthStateListener mAuthListener;
    private TextView logTextViewTitle, logTextViewDesc, logTextViewRegister, logTextViewForgotPassword;
    private TextInputLayout logTextInputEmail, logTextInputPassword;
    private Button logButtonLogin;
    private CardView logCardViewGoogle;
    private Intent intent;
    private LoadingDialog loadingDialog;

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        mAuth.addAuthStateListener(mAuthListener);
//
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user != null) {
//            intent = new Intent(getBaseContext(), FiretestActivity.class);
//            startActivity(intent);
//        }
//
//    }

//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
        clearError();
        setAnimation();
        setTextWatcher();
//        setGoogleRequest();
        setListener();

        //ERROR: data gmail belum masuk
//        setGoogleData();
    }

//    private void setGoogleData() {
//        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
//        userID = signInAccount.getId();
//        if (signInAccount != null) {
//            DocumentReference userReference = fStore.collection("user_collection").document(userID);
//            Map<String, Object> user_info = new HashMap<>();
//            user_info.put("username", signInAccount.getDisplayName());
//            user_info.put("email", signInAccount.getEmail());
//        }
//    }

//    private void setGoogleRequest() {
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(Const.CLIENT_ID)
//                .requestEmail()
//                .build();
//        googleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);
//
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this, this)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
//
//        mAuth = FirebaseAuth.getInstance();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                } else {
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                }
//                setGoogleData();
//            }
//        };
//
//    }

    private void setListener() {
        logTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        logButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoading();

                String email = logTextInputEmail.getEditText().getText().toString().trim();
                String password = logTextInputPassword.getEditText().getText().toString().trim();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        intent = new Intent(LoginActivity.this, MainActivity.class);
                                        loadingDialog.stopLoading();
                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        finish();

                                        clearError();
                                    }
                                }, 1000);
                            }else{
//                                Log.d("error", e.toString());

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        loadingDialog.stopLoading();
                                        Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                                    }
                                }, 1000);
                            }
                        }
                    });
                }else{
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.stopLoading();
                            Toast.makeText(LoginActivity.this, "Type in your email and password!", Toast.LENGTH_SHORT).show();
                        }
                    }, 1000);

                }
            }
        });

        logCardViewGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                signIn();
            }
        });

    }

//    private void signIn() {
//        Intent signInIntent = googleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
//                firebaseAuthWithGoogle(account.getIdToken());
//            } catch (ApiException e) {
//                // Google Sign In failed, update UI appropriately
//                Log.w(TAG, "Google sign in failed", e);
//            }
//        }
//    }

//    private void firebaseAuthWithGoogle(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithCredential:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithCredential:failure", task.getException());
//                        }
//                    }
//                });
//    }

    private void setTextWatcher() {
        TextWatcher tmpWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String email = logTextInputEmail.getEditText().getText().toString().trim();
                String password = logTextInputPassword.getEditText().getText().toString().trim();

                if (!email.isEmpty() && !password.isEmpty()) {
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
        logTextViewForgotPassword.setTranslationX(800);
        logButtonLogin.setTranslationX(800);
        logCardViewGoogle.setTranslationX(800);

        logTextViewTitle.setAlpha(0);
        logTextViewDesc.setAlpha(0);
        logTextInputEmail.setAlpha(0);
        logTextInputPassword.setAlpha(0);
        logTextViewForgotPassword.setAlpha(0);
        logButtonLogin.setAlpha(0);
        logCardViewGoogle.setAlpha(0);

        logTextViewTitle.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        logTextViewDesc.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(250).start();
        logTextInputEmail.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(300).start();
        logTextInputPassword.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        logTextViewForgotPassword.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(500).start();
        logButtonLogin.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        logCardViewGoogle.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
    }

    private void initialize() {
        mAuth = FirebaseAuth.getInstance();

        logTextViewTitle = findViewById(R.id.logTextViewTitle);
        logTextViewDesc = findViewById(R.id.logTextViewDesc);
        logTextInputEmail = findViewById(R.id.logTextInputEmail);
        logTextInputPassword = findViewById(R.id.logTextInputPassword);
        logTextViewForgotPassword = findViewById(R.id.logTextViewForgotPassword);
        logButtonLogin = findViewById(R.id.logButtonLogin);
        logCardViewGoogle = findViewById(R.id.logCardViewGoogle);
        logTextViewRegister = findViewById(R.id.logTextViewRegister);

        loadingDialog = new LoadingDialog(LoginActivity.this);
    }

//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        Log.d(TAG, "onConnectionFailed:" + connectionResult);
//        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
//    }
}
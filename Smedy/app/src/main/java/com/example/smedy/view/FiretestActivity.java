package com.example.smedy.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smedy.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class FiretestActivity extends AppCompatActivity {

    private TextView testTextViewUsername, testTextViewEmail;
    private Button testButtonSignOut;
    private Intent intent;

    FirebaseAuth mAuth;
    FirebaseUser user;
    GoogleSignInAccount signInAccount;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firetest);

        initialize();

        //nanti harus dipindah di beda file klo ngga error!!
        // ini juga harus e di repository
//        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
//        if(signInAccount != null){
//            DocumentReference userReference = fStore.collection("user_collection").document(userID);
//            Map<String, Object> user_info = new HashMap<>();
//            user_info.put("username", signInAccount.getDisplayName());
//            user_info.put("email", signInAccount.getEmail());
//        }

        // ini harus e di repository
        DocumentReference userReference = fStore.collection("user_collection").document(userID);
        userReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException error) {
                testTextViewUsername.setText(documentSnapshot.getString("username"));
                testTextViewEmail.setText(documentSnapshot.getString("email"));
            }
        });

        testButtonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                FirebaseFirestore.getInstance().terminate();

                Toast.makeText(FiretestActivity.this, "Cya! Have a Nice Day!", Toast.LENGTH_SHORT).show();

                intent = new Intent(getBaseContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }

    private void initialize() {
        testTextViewUsername = findViewById(R.id.testTextViewUsername);
        testTextViewEmail = findViewById(R.id.testTextViewEmail);
        testButtonSignOut = findViewById(R.id.testButtonSignOut);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        userID = user.getUid();
    }
}
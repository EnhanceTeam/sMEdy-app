package com.example.smedy.view;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smedy.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseFirestore fStore;
    String userID;
    StorageReference storageReference;
    private TextView profileTextViewName;
    private ImageView profileImageView, profileImageViewSignOut;
    private TextInputLayout profileTextInputLayoutName, profileTextInputLayoutPhone, profileTextInputLayoutMail;
    private Button profileButtonSave;
    private Toolbar toolbarProfile;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initialize();
        setListener();
    }

    private void setListener() {
        toolbarProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initialize() {
        toolbarProfile = findViewById(R.id.toolbarProfile);
        profileImageView = findViewById(R.id.profileImageView);
        profileTextViewName = findViewById(R.id.profileTextViewName);
        profileImageViewSignOut = findViewById(R.id.profileImageViewSignOut);
        profileTextInputLayoutName = findViewById(R.id.profileTextInputLayoutName);
        profileTextInputLayoutPhone = findViewById(R.id.profileTextInputLayoutPhone);
        profileTextInputLayoutMail = findViewById(R.id.profileTextInputLayoutMail);
        profileButtonSave = findViewById(R.id.profileButtonSave);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        user = mAuth.getCurrentUser();
        userID = user.getUid();

        StorageReference profilePictureReference = storageReference.child("user_collection/" + userID + "/profile_picture.png");
        profilePictureReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImageView);
            }
        });

        DocumentReference userReference = fStore.collection("user_collection").document(userID);
        userReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException error) {
                profileTextViewName.setText(documentSnapshot.getString("username"));
                profileTextInputLayoutPhone.getEditText().setText(documentSnapshot.getString("phone"));
                profileTextInputLayoutMail.getEditText().setText(documentSnapshot.getString("email"));
            }
        });

    }
}
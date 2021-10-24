package com.example.smedy.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.smedy.R;
import com.google.android.gms.tasks.OnFailureListener;
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
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

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

        profileButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = profileTextInputLayoutName.getEditText().getText().toString().trim();
                String phone = profileTextInputLayoutPhone.getEditText().getText().toString().trim();
                String mail = profileTextInputLayoutMail.getEditText().getText().toString().trim();

                if (!(name.isEmpty() && phone.isEmpty() && mail.isEmpty())) {

                    DocumentReference usernameReference = fStore.collection("user_collection").document(user.getUid());
                    Map<String, Object> newUpdate = new HashMap<>();
                    newUpdate.put("username", name);
                    newUpdate.put("phone", phone);
                    newUpdate.put("email", mail);

                    usernameReference.update(newUpdate).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            intent = new Intent(getBaseContext(), ProfileActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                            Toast.makeText(ProfileActivity.this, "data updated!", Toast.LENGTH_SHORT).show();

                            finish();
                            startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(ProfileActivity.this, "Failed to change username", Toast.LENGTH_SHORT).show();
                            Log.d("error", e.toString());
                        }
                    });

                }

            }
        });

        profileImageViewSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                FirebaseFirestore.getInstance().terminate();

                Toast.makeText(ProfileActivity.this, "Sign Out Success", Toast.LENGTH_SHORT).show();

                intent = new Intent(getBaseContext(), HomeFragment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000) {
            if (resultCode == Activity.RESULT_OK) {
                Uri imageUri = data.getData();
                profileImageView.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);
            }
        }

    }

    private void uploadImageToFirebase(Uri imageUri) {
        StorageReference imageReference = storageReference.child("user_collection/" + userID + "/profile_picture.png");
        imageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(ProfileActivity.this, "Image Uploaded!", Toast.LENGTH_SHORT).show();
                imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(profileImageView);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
                Toast.makeText(ProfileActivity.this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                Log.d("error", e.toString());
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
                profileTextInputLayoutName.getEditText().setText(documentSnapshot.getString("username"));
                profileTextInputLayoutPhone.getEditText().setText(documentSnapshot.getString("phone"));
                profileTextInputLayoutMail.getEditText().setText(documentSnapshot.getString("email"));
            }
        });

    }
}
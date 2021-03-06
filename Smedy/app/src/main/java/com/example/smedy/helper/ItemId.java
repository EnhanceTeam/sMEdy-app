package com.example.smedy.helper;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;

public class ItemId {
    @Exclude
    public String ItemId;

    public <T extends ItemId> T withId(@NonNull final String id) {
        this.ItemId = id;
        return (T) this;
    }
}

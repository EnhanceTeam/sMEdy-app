package com.example.smedy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Music implements Parcelable {
    private String title, music, access_token;

    public Music(){}

    public Music(String title, String music, String access_token) {
        this.title = title;
        this.music = music;
        this.access_token = access_token;
    }

    protected Music(Parcel in) {
        title = in.readString();
        music = in.readString();
        access_token = in.readString();
    }

    public static final Creator<Music> CREATOR = new Creator<Music>() {
        @Override
        public Music createFromParcel(Parcel in) {
            return new Music(in);
        }

        @Override
        public Music[] newArray(int size) {
            return new Music[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(music);
        parcel.writeString(access_token);
    }
}

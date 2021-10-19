package com.example.smedy.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Meditation implements Parcelable {
    private String title, description, duration, url;

    public Meditation(){

    }

    public Meditation(String title, String description, String duration, String url) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.url = url;
    }

    protected Meditation(Parcel in) {
        title = in.readString();
        description = in.readString();
        duration = in.readString();
        url = in.readString();
    }

    public static final Creator<Meditation> CREATOR = new Creator<Meditation>() {
        @Override
        public Meditation createFromParcel(Parcel in) {
            return new Meditation(in);
        }

        @Override
        public Meditation[] newArray(int size) {
            return new Meditation[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(duration);
        parcel.writeString(url);
    }
}

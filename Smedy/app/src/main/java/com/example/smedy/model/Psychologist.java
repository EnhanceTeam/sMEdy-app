package com.example.smedy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.smedy.helper.ItemId;

public class Psychologist implements Parcelable {
    private String nama, lulusan, tahun, foto, lokasi, biaya, rating, specialist, noTelp, email;

    public Psychologist() {
        this.nama = "";
        this.lulusan = "";
        this.tahun = "";
        this.foto = "";
        this.lokasi = "";
        this.biaya = "";
        this.rating = "";
        this.specialist = "";
        this.noTelp = "";
        this.email = "";
    }

    public Psychologist(String nama, String lulusan, String tahun, String foto, String lokasi, String biaya, String rating, String specialist, String noTelp, String email) {
        this.nama = nama;
        this.lulusan = lulusan;
        this.tahun = tahun;
        this.foto = foto;
        this.lokasi = lokasi;
        this.biaya = biaya;
        this.rating = rating;
        this.specialist = specialist;
        this.noTelp = noTelp;
        this.email = email;
    }

    protected Psychologist(Parcel in) {
        nama = in.readString();
        lulusan = in.readString();
        tahun = in.readString();
        foto = in.readString();
        lokasi = in.readString();
        biaya = in.readString();
        rating = in.readString();
        specialist = in.readString();
        noTelp = in.readString();
        email = in.readString();
    }

    public static final Creator<Psychologist> CREATOR = new Creator<Psychologist>() {
        @Override
        public Psychologist createFromParcel(Parcel in) {
            return new Psychologist(in);
        }

        @Override
        public Psychologist[] newArray(int size) {
            return new Psychologist[size];
        }
    };

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLulusan() {
        return lulusan;
    }

    public void setLulusan(String lulusan) {
        this.lulusan = lulusan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getBiaya() {
        return biaya;
    }

    public void setBiaya(String biaya) {
        this.biaya = biaya;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeString(lulusan);
        parcel.writeString(tahun);
        parcel.writeString(foto);
        parcel.writeString(lokasi);
        parcel.writeString(biaya);
        parcel.writeString(rating);
        parcel.writeString(specialist);
        parcel.writeString(noTelp);
        parcel.writeString(email);
    }
}

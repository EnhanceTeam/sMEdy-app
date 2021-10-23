package com.example.smedy.model;

import java.util.ArrayList;

public class Psychologist {
    private String nama, lulusan, tahun, foto, lokasi, biaya, rating, specialist;
    private Integer id;

    public Psychologist() {
        this.nama = "";
        this.lulusan = "";
        this.tahun = "";
        this.foto = "";
        this.lokasi = "";
        this.biaya = "";
        this.rating = "";
        this.specialist = "";
        this.id = 0;
    }
    public Psychologist(String nama, String lulusan, String tahun, String foto, String lokasi, String biaya, String rating, String specialist, Integer id) {
        this.nama = nama;
        this.lulusan = lulusan;
        this.tahun = tahun;
        this.foto = foto;
        this.lokasi = lokasi;
        this.biaya = biaya;
        this.rating = rating;
        this.specialist = specialist;
        this.id = id;
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

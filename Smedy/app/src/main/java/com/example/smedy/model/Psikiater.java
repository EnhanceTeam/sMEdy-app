package com.example.smedy.model;

import java.util.ArrayList;

public class Psikiater {
    private String nama, lulusan, tahun, foto, lokasi, biaya;
    private Integer id;

    public Psikiater() {
        this.id = 0;
        this.nama = "";
        this.lulusan = "";
        this.tahun = "";
        this.foto = "";
        this.lokasi = "";
        this.biaya = "";
    }

    public Psikiater(String nama, String tahun, String lokasi) {
        this.nama = "";
        this.tahun = "";
        this.lokasi = "";
    }

    public Psikiater(Integer id, String nama, String lulusan, String tahun, String foto, String lokasi, String biaya) {
        this.id = id;
        this.nama = nama;
        this.lulusan = lulusan;
        this.tahun = tahun;
        this.foto = foto;
        this.lokasi = lokasi;
        this.biaya = biaya;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}

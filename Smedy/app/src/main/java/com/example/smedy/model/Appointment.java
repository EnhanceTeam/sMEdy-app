package com.example.smedy.model;

import com.example.smedy.helper.ItemId;

public class Appointment extends ItemId {

    private String namaPsikolog, spesialis, jam, tanggal, status, foto;

    public Appointment() {
        this.namaPsikolog = "";
        this.spesialis = "";
        this.jam = "";
        this.tanggal = "";
        this.status = "";
        this.foto = "";
    }

    public Appointment(String namaPsikolog, String spesialis, String jam, String tanggal, String status, String foto) {
        this.namaPsikolog = namaPsikolog;
        this.spesialis = spesialis;
        this.jam = jam;
        this.tanggal = tanggal;
        this.status = status;
        this.foto = foto;
    }

    public String getNamaPsikolog() {
        return namaPsikolog;
    }

    public void setNamaPsikolog(String namaPsikolog) {
        this.namaPsikolog = namaPsikolog;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

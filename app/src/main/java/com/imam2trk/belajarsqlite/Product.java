package com.imam2trk.belajarsqlite;

public class Product {
    private int id;
    private String nama;
    private int jumlah;

    public Product(String nama, int jumlah) {
        this.nama = nama;
        this.jumlah = jumlah;
    }

    public Product(int id, String nama, int jumlah) {
        this.id = id;
        this.nama = nama;
        this.jumlah = jumlah;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }
}

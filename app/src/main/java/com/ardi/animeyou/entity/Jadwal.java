package com.ardi.animeyou.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Jadwal implements Parcelable {

    private String id = "id";
    private String judul = "judul";
    private String hari = "hari";
    private String halaman = "halaman";
    private String gambar = "gambar";

    public Jadwal(JSONObject object){
        try {
            String id = object.getString("id");
            String judul = object.getString("judul");
            String hari = object.getString("hari");
            String halaman = object.getString("halaman");
            String gambar = object.getString("gambar");

            this.id = id;
            this.judul = judul;
            this.halaman = halaman;
            this.gambar = gambar;
            this.hari = hari;

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public Jadwal(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getHalaman() {
        return halaman;
    }

    public void setHalaman(String halaman) {
        this.halaman = halaman;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.judul);
        dest.writeString(this.hari);
        dest.writeString(this.halaman);
        dest.writeString(this.gambar);
    }

    protected Jadwal(Parcel in) {
        this.id = in.readString();
        this.judul = in.readString();
        this.hari = in.readString();
        this.halaman = in.readString();
        this.gambar = in.readString();
    }

    public static final Creator<Jadwal> CREATOR = new Creator<Jadwal>() {
        @Override
        public Jadwal createFromParcel(Parcel source) {
            return new Jadwal(source);
        }

        @Override
        public Jadwal[] newArray(int size) {
            return new Jadwal[size];
        }
    };
}

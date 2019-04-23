package com.ardi.animeyou.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Anime implements Parcelable {
    private String id = "id";
    private String judul = "judul";
    private String url = "url";
    private String srcVideo = "srcVideo";
    private String prev = "prev";
    private String all = "all";
    private String next = "next";
    private String img = "img";
    private String date = "date";
    private String ganre = "genre";
    private String halaman = "halaman";
    private String video = "video";
    private String video2 = "video2";
    private String video3 = "video3";

    public Anime(JSONObject object) {
        try {
            String id = object.getString("id");
            String judul = object.getString("judul");
            String url = object.getString("url");
            String src_video = object.getString("src_video");
            String prev_video = object.getString("prev_video");
            String all_video = object.getString("all_video");
            String next_video = object.getString("next_video");
            String gambar = object.getString("gambar");
            String tanggal = object.getString("tanggal");
            String genre = object.getString("genre");
            String halaman = object.getString("halaman");
            String video = object.getString("video");
            String video2 = object.getString("video2");
            String video3 = object.getString("video3");

            this.id = id;
            this.judul = judul;
            this.url = url;
            this.srcVideo = src_video;
            this.prev = prev_video;
            this.all = all_video;
            this.next = next_video;
            this.img = gambar;
            this.date = tanggal;
            this.ganre = genre;
            this.halaman = halaman;
            this.video = video;
            this.video2 = video2;
            this.video3 = video3;

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Anime(){

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSrcVideo() {
        return srcVideo;
    }

    public void setSrcVideo(String srcVideo) {
        this.srcVideo = srcVideo;
    }

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGanre() {
        return ganre;
    }

    public void setGanre(String ganre) {
        this.ganre = ganre;
    }

    public String getHalaman() {
        return halaman;
    }

    public void setHalaman(String halaman) {
        this.halaman = halaman;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideo2() {
        return video2;
    }

    public void setVideo2(String video2) {
        this.video2 = video2;
    }

    public String getVideo3() {
        return video3;
    }

    public void setVideo3(String video3) {
        this.video3 = video3;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.judul);
        dest.writeString(this.url);
        dest.writeString(this.srcVideo);
        dest.writeString(this.prev);
        dest.writeString(this.all);
        dest.writeString(this.next);
        dest.writeString(this.img);
        dest.writeString(this.date);
        dest.writeString(this.ganre);
        dest.writeString(this.halaman);
        dest.writeString(this.video);
        dest.writeString(this.video2);
        dest.writeString(this.video3);
    }

    protected Anime(Parcel in) {
        this.id = in.readString();
        this.judul = in.readString();
        this.url = in.readString();
        this.srcVideo = in.readString();
        this.prev = in.readString();
        this.all = in.readString();
        this.next = in.readString();
        this.img = in.readString();
        this.date = in.readString();
        this.ganre = in.readString();
        this.halaman = in.readString();
        this.video = in.readString();
        this.video2 = in.readString();
        this.video3 = in.readString();
    }

    public static final Parcelable.Creator<Anime> CREATOR = new Parcelable.Creator<Anime>() {
        @Override
        public Anime createFromParcel(Parcel source) {
            return new Anime(source);
        }

        @Override
        public Anime[] newArray(int size) {
            return new Anime[size];
        }
    };
}

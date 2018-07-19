package com.app.onur.hurriyetapp.Model;

import android.support.annotation.Nullable;

import java.util.ArrayList;

public class Article {

    private int Id;
    private String Title;
    private String Url;

    private ArrayList<ArticlePhotos> Files;

    public Article(int id, @Nullable String title, @Nullable String url, @Nullable ArrayList<ArticlePhotos> files){
        Id = id;
        Title = title;
        Url = url;
        Files = files;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public ArrayList<ArticlePhotos> getFiles() {
        return Files;
    }

    public void setFiles(ArrayList<ArticlePhotos> files) {
        Files = files;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}

package com.app.onur.hurriyetapp.Model;

public class MyArticle {

    private int id;
    private String title;
    private String url;

    public MyArticle(int id,String title,String url, String fileUrl){
        this.id = id;
        this.title = title;
        this.url = url;
        this.fileUrl = fileUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    private String fileUrl;
}

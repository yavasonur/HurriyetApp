package com.app.onur.hurriyetapp.Model;

public class ArticlePhotos {

    private String FileUrl;

    public ArticlePhotos(String fileUrl)
    {
        this.FileUrl = fileUrl;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.FileUrl = fileUrl;
    }
}

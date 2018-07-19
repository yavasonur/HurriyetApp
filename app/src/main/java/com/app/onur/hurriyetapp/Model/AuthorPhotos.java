package com.app.onur.hurriyetapp.Model;


public class AuthorPhotos {

    private String FileUrl;

    public AuthorPhotos(String fileurl){
        this.FileUrl = fileurl;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String fileUrl) {
        FileUrl = fileUrl;
    }


}

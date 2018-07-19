package com.app.onur.hurriyetapp.Model;

import java.util.ArrayList;

public class Author {

    private String Id;
    private String Fullname;
    private ArrayList<AuthorPhotos> Files;

    public Author(String id, String fullname, ArrayList files){
        Id = id;
        Fullname = fullname;
        Files = files;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public ArrayList<AuthorPhotos> getFiles() {
        return Files;
    }

    public void setFiles(ArrayList<AuthorPhotos> files) {
        Files = files;
    }


}

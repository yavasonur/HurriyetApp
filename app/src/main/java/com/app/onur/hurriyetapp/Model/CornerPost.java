package com.app.onur.hurriyetapp.Model;

import java.util.Date;

public class CornerPost {

    private String Title;
    private String Description;
    private Date CreatedDate;

    public CornerPost(String title, String description, Date date){
        this.Title = title;
        this.Description = description;
        this.CreatedDate = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        CreatedDate = createdDate;
    }

}

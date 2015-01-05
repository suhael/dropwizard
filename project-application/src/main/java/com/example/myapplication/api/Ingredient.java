package com.example.myapplication.api;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sakhtar on 23/12/2014.
 */
public class Ingredient implements Serializable{

    private String title;
    private Date created;

    public Ingredient(){

    }

    public Ingredient(String title, Date created){
        this.title = title;
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}

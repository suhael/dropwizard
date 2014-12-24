package com.example.myapplication.api;

import java.io.Serializable;

/**
 * Created by sakhtar on 22/12/2014.
 */
public class Recipe implements Serializable {

    private String title;

    public Recipe(){

    }

    public Recipe(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

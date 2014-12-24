package com.example.myapplication.api;

import java.io.Serializable;

/**
 * Created by sakhtar on 23/12/2014.
 */
public class Ingredient implements Serializable{

    private String title;

    public Ingredient(){

    }

    public Ingredient(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

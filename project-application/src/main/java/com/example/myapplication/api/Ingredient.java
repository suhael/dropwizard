package com.example.myapplication.api;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sakhtar on 23/12/2014.
 */
public class Ingredient implements Serializable{

    @NotNull
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

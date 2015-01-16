package com.example.myapplication.api;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by sakhtar on 22/12/2014.
 */
public class Recipe implements Serializable {

    private String id;
    @NotNull
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

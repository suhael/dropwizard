package com.example.myapplication.api;

import javax.validation.constraints.NotNull;

/**
 * Created by sakhtar on 19/01/2015.
 */
public class MealTime {

    private String id;

    @NotNull
    private String title;

    public MealTime(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

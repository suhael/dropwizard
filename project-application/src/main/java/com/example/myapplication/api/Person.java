package com.example.myapplication.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by sakhtar on 27/11/2014.
 */
public class Person {

    private long id;
    private String name = "suhael";

    public Person(){

    }

    public Person(long id, String name){
        this.id = id;
        this.name = name;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

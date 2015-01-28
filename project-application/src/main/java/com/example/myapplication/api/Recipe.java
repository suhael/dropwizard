package com.example.myapplication.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by sakhtar on 22/12/2014.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Recipe implements Serializable {

    private String id;
    @NotNull
    private String title;
    private List tags;
    private List mealtimes;
    private List ingredients;

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

    public List getIngredients() {
        return ingredients;
    }

    public void setIngredients(List ingredients) {
        this.ingredients = ingredients;
    }

    public List getTags() {
        return tags;
    }

    public void setTags(List tags) {
        this.tags = tags;
    }

    public List getMealtimes() {
        return mealtimes;
    }

    public void setMealtimes(List mealtimes) {
        this.mealtimes = mealtimes;
    }
}

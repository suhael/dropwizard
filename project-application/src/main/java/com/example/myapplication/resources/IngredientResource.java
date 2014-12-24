package com.example.myapplication.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.myapplication.api.Ingredient;
import com.example.myapplication.db.IngredientRepository;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sakhtar on 23/12/2014.
 */
@Path("/ingredient")
@Produces(MediaType.APPLICATION_JSON)
public class IngredientResource {

    private final IngredientRepository repository;

    public IngredientResource(IngredientRepository repository){
        this.repository = repository;
    }

    @GET
    @Timed
    public List<Ingredient> getIngredients(){
        return repository.getIngredients();
    }

    @POST
    public void saveIngredient(){
        Ingredient ingredient = new Ingredient("tomato");
        repository.saveIngredient(ingredient);
    }
}

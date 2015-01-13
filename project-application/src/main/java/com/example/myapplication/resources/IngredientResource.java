package com.example.myapplication.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.myapplication.api.Ingredient;
import com.example.myapplication.db.IngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by sakhtar on 23/12/2014.
 */
@Path("/ingredient")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class IngredientResource {

    private final IngredientRepository repository;
    final static Logger logger = LoggerFactory.getLogger(IngredientResource.class);

    public IngredientResource(IngredientRepository repository){
        this.repository = repository;
    }

    @GET
    @Timed
    public List<Ingredient> getIngredients() {
        return repository.getIngredients();
    }

    @POST
    public void saveIngredient(@Valid Ingredient ingredient){
        repository.saveIngredient(ingredient);
    }

    @GET
    @Timed
    @Path("/{id}")
    public Ingredient getIngredient(@PathParam("id") String id){
        return repository.getIngredient(id);
    }

    @PUT
    @Path("/{id}")
    public void updateIngredient(@PathParam("id") String id, @Valid Ingredient ingredient){
        repository.updateIngredient(id, ingredient);
    }

    @DELETE
    @Path("/{id}")
    public void deleteIngredient(@PathParam("id") String id){
        repository.deleteIngredient(id);
    }
}

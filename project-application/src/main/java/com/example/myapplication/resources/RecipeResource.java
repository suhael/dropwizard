package com.example.myapplication.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.myapplication.api.Recipe;
import com.example.myapplication.db.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by sakhtar on 22/12/2014.
 */
@Path("/recipe")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RecipeResource {

    private final RecipeRepository repository;
    final static Logger logger = LoggerFactory.getLogger(RecipeResource.class);

    public RecipeResource(RecipeRepository repository){
        this.repository = repository;
    }

    @GET
    @Timed
    public List<Recipe> getIngredients() {
        return repository.getRecipes();
    }

    @POST
    public void saveIngredient(@Valid Recipe recipe){
        repository.saveRecipe(recipe);
    }

    @GET
    @Timed
    @Path("/{id}")
    public Recipe getRecipe(@PathParam("id") String id){
        return repository.getRecipe(id);
    }


    @PUT
    @Path("/{id}")
    public void updateRecipe(@PathParam("id") String id, @Valid Recipe recipe){
        repository.updateRecipe(id, recipe);
    }

    @DELETE
    @Path("/{id}")
    public void deleteRecipe(@PathParam("id") String id){
        repository.deleteRecipe(id);
    }
}

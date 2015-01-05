package com.example.myapplication.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.myapplication.api.Ingredient;
import com.example.myapplication.db.IngredientRepository;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.Date;
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
    public List<Ingredient> getIngredients() throws ParseException {
        return repository.getIngredientsByDate();

/*        Ingredient ingredient = new Ingredient("blbl", new Date());
        return new IngredientView(ingredient);*/
    }

    @POST
    public void saveIngredient(){
        Ingredient ingredient = new Ingredient("tomato", new Date());
        repository.saveIngredient(ingredient);
        Ingredient ingredient2 = new Ingredient("pepper", new Date());
        repository.saveIngredient(ingredient2);
        Ingredient ingredient3 = new Ingredient("mushroom", new Date());
        repository.saveIngredient(ingredient3);
        Ingredient ingredient4 = new Ingredient("sweetcorn", new Date());
        repository.saveIngredient(ingredient4);
    }
}

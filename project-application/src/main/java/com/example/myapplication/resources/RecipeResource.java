package com.example.myapplication.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.myapplication.api.Recipe;
import org.elasticsearch.client.Client;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sakhtar on 22/12/2014.
 */
@Path("/recipe")
@Produces(MediaType.APPLICATION_JSON)
public class RecipeResource {

    private final Client client;

    public RecipeResource(Client client){
        this.client = client;
    }

    @GET
    @Timed
    public List<Recipe> getRecipes(){

/*        try {
            GetResponse responseGet = client.prepareGet("twitter", "tweet", "1")
                    .setOperationThreaded(false)
                    .execute()
                    .actionGet();

            System.out.println(responseGet.getSourceAsString());


        } catch (Exception e) {
            e.printStackTrace();
        }*/

        List<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe("food"));

        return recipes;
    }

    @POST
    public Response add(){
        return Response.created(UriBuilder.fromResource(Recipe.class).build()).build();
    }
}

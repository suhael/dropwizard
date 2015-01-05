package com.example.myapplication;

import com.example.myapplication.db.IngredientRepository;
import com.example.myapplication.health.RecipeHealthCheck;
import com.example.myapplication.resources.IngredientResource;
import com.example.myapplication.resources.RecipeResource;
import com.example.myapplication.services.ElasticSearchManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.elasticsearch.client.Client;

/**
 * Created by sakhtar on 27/11/2014.
 */
public class MyApplication extends Application<MyApplicationConfiguration> {
    public static void main(String[] args) throws Exception {
        new MyApplication().run(args);
    }
    @Override
    public void initialize(Bootstrap<MyApplicationConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    public void run(MyApplicationConfiguration configuration, Environment environment) {

        // Create elasticsearch server
        ElasticSearchManager esManager = new ElasticSearchManager();
        environment.lifecycle().manage(esManager);

        Client client = esManager.getClient();
        ObjectMapper objectMapper = new ObjectMapper();

        IngredientRepository ingredientRepository = new IngredientRepository(client, objectMapper);
        environment.lifecycle().manage(ingredientRepository);

        final RecipeResource recipeResource = new RecipeResource(esManager.getClient());
        environment.jersey().register(recipeResource);


        final IngredientResource ingredientResource = new IngredientResource(ingredientRepository);
        environment.jersey().register(ingredientResource);

        final RecipeHealthCheck recipeHealthCheck = new RecipeHealthCheck();
        environment.healthChecks().register("recipe", recipeHealthCheck);

        environment.jersey().setUrlPattern("/api/*");

    }
}

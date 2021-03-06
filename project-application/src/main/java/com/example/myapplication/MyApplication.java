package com.example.myapplication;

import com.example.myapplication.db.IngredientRepository;
import com.example.myapplication.db.MealTimeRepository;
import com.example.myapplication.db.RecipeRepository;
import com.example.myapplication.db.TagRepository;
import com.example.myapplication.health.RecipeHealthCheck;
import com.example.myapplication.resources.IngredientResource;
import com.example.myapplication.resources.MealTimeResource;
import com.example.myapplication.resources.RecipeResource;
import com.example.myapplication.resources.TagResource;
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
        bootstrap.addBundle(new AssetsBundle("/assets/", "/assets/", "index.html", "assets"));
        bootstrap.addBundle(new AssetsBundle("/guthub/", "/guthub/", "index.html", "guthub"));
        bootstrap.addBundle(new AssetsBundle("/cookbook/", "/cookbook/", "index.html", "cookbook"));
    }

    public void run(MyApplicationConfiguration configuration, Environment environment) {

        // Create elasticsearch server
        ElasticSearchManager esManager = new ElasticSearchManager();
        environment.lifecycle().manage(esManager);

        Client client = esManager.getClient();
        ObjectMapper objectMapper = new ObjectMapper();

        MealTimeRepository mealTimeRepository = new MealTimeRepository(client, objectMapper);
        environment.lifecycle().manage(mealTimeRepository);

        TagRepository tagRepository = new TagRepository(client, objectMapper);
        environment.lifecycle().manage(tagRepository);

        IngredientRepository ingredientRepository = new IngredientRepository(client, objectMapper);
        environment.lifecycle().manage(ingredientRepository);

        RecipeRepository recipeRepository = new RecipeRepository(client, objectMapper);
        environment.lifecycle().manage(recipeRepository);

        final RecipeResource recipeResource = new RecipeResource(recipeRepository);
        environment.jersey().register(recipeResource);

        final IngredientResource ingredientResource = new IngredientResource(ingredientRepository);
        environment.jersey().register(ingredientResource);

        final TagResource tagResource = new TagResource(tagRepository);
        environment.jersey().register(tagResource);

        final MealTimeResource mealTimeResource = new MealTimeResource(mealTimeRepository);
        environment.jersey().register(mealTimeResource);

        final RecipeHealthCheck recipeHealthCheck = new RecipeHealthCheck();
        environment.healthChecks().register("recipe", recipeHealthCheck);

        environment.jersey().setUrlPattern("/api/*");

    }
}

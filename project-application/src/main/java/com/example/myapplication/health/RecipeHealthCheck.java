package com.example.myapplication.health;

import com.codahale.metrics.health.HealthCheck;
import com.example.myapplication.api.Recipe;

/**
 * Created by sakhtar on 22/12/2014.
 */
public class RecipeHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        Recipe recipe = new Recipe("test");

        if(recipe.getTitle().equalsIgnoreCase("test")){
            return Result.healthy();
        }

        return Result.unhealthy("recipe title is not correct");
    }
}

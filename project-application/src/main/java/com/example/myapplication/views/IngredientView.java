package com.example.myapplication.views;

import com.example.myapplication.api.Ingredient;
import io.dropwizard.views.View;

/**
 * Created by Home on 27/12/2014.
 */
public class IngredientView extends View {
    private final Ingredient ingredient;

    public IngredientView(Ingredient ingredient) {
        super("ingredient.ftl");
        this.ingredient = ingredient;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
}
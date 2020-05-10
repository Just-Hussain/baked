package com.example.baked.models;

import java.util.List;

public class Recipe {
    int id;
    String name;
    List<Ingredient> ingredients;
    List<Step> steps;
    int servings;
    String image;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }
}



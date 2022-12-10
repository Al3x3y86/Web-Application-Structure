package my.sky.web_application_structure.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;

@Data
public class Recipe {

    private String titleRecipe;
    private int cookingTimeMinutes;
    private ArrayList<Ingredient> ingredientsList;
    private LinkedList<String> cookingInstruction;

    public Recipe(String titleRecipe, int cookingTimeMinutes, ArrayList<Ingredient> ingredientsList, LinkedList<String> cookingInstruction) {
        this.titleRecipe = titleRecipe;
        this.cookingTimeMinutes = cookingTimeMinutes;
        this.ingredientsList = ingredientsList;
        this.cookingInstruction = cookingInstruction;
        recipeID = counterID;
        counterID++;
    }

    private long recipeID;
    static Long counterID = 1L;

    public Recipe() {
        recipeID = counterID;
        counterID++;
    }
}

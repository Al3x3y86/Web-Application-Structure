package my.sky.web_application_structure.service;

import my.sky.web_application_structure.model.Recipe;
import my.sky.web_application_structure.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findRecipeById(Long recipeID) {
        Recipe recipe = recipeRepository.findById(recipeID);
        if (recipe == null) {
            throw new IllegalArgumentException();
        }
        return recipe;
    }

    public Map<Long, Recipe> addRecipe(Long recipeID, Recipe recipe) {
        return recipeRepository.add(recipeID, recipe);
    }
}

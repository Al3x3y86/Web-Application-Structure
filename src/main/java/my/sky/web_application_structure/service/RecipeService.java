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
        return recipeRepository.findById(recipeID);
    }

    public boolean addRecipe(Recipe recipe) {
        return recipeRepository.add(recipe);
    }

    public boolean updateRecipe (Long recipeID, Recipe recipe){
        return recipeRepository.update(recipeID, recipe);
    }

    public boolean deleteRecipe(Long recipeID){
        return recipeRepository.delete(recipeID);
    }

    public Map<Long, Recipe> viewAllRecipes(){
        return recipeRepository.viewAll();
    }
}

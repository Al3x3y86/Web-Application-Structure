package my.sky.web_application_structure.controllers;

import my.sky.web_application_structure.model.Recipe;
import my.sky.web_application_structure.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/viewId/{recipeId}")
    public Recipe findRecipeById(@PathVariable String recipeId) {
        return recipeService.findRecipeById(Long.parseLong(recipeId));
    }

    @PostMapping("/add")
    public Map<Long, Recipe> add(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe.getRecipeID(), recipe);
    }

    @PutMapping("/update/{recipeId}")
    public Map<Long, Recipe> update(@PathVariable String recipeId, @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(Long.parseLong(recipeId), recipe);
    }

    @DeleteMapping("/delete/{recipeId}")
    public void delete(@PathVariable String recipeId) {
        recipeService.deleteRecipe(Long.parseLong(recipeId));
    }

    @GetMapping(value = "/allRecipes")
    public Map<Long, Recipe> getAllRecipes() {
        return recipeService.viewAllRecipes();
    }

}
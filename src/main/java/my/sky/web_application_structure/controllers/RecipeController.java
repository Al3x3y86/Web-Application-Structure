package my.sky.web_application_structure.controllers;

import my.sky.web_application_structure.model.Recipe;
import my.sky.web_application_structure.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("viewId/{recipeId}")
    public Recipe findRecipeById(@PathVariable String recipeId) {
        return recipeService.findRecipeById(Long.parseLong(recipeId));
    }

    @PostMapping()
    public Map<Long, Recipe> add(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe.getRecipeID(), recipe);
    }
}
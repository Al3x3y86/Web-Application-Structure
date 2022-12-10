package my.sky.web_application_structure.controllers;

import my.sky.web_application_structure.model.Ingredient;
import my.sky.web_application_structure.service.IngredientsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientsService ingredientsService;

    public IngredientController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/viewId/{ingredientId}")
    public Ingredient findIngredientById(@PathVariable String ingredientId) {
        return ingredientsService.findIngredientById(Long.parseLong(ingredientId));
    }

    @PostMapping("/add")
    public Map<Long, Ingredient> add(@RequestBody Ingredient ingredient) {
        return ingredientsService.createIngredient(ingredient.getIngredientID(), ingredient);
    }

    @PutMapping("/update/{ingredientId}")
    public Map<Long, Ingredient> update(@PathVariable String ingredientId, @RequestBody Ingredient ingredient) {
        return ingredientsService.updateIngredient(Long.parseLong(ingredientId), ingredient);
    }

    @DeleteMapping("/delete/{ingredientId}")
    public void delete(@PathVariable String ingredientId) {
        ingredientsService.deleteIngredient(Long.parseLong(ingredientId));
    }

    @GetMapping(value = "/allIngredients")
    public Map<Long, Ingredient> viewAllIngredients() {
        return ingredientsService.viewAllIngredients();
    }
}
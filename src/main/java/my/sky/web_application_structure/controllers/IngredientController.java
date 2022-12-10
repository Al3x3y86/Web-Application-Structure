package my.sky.web_application_structure.controllers;

import my.sky.web_application_structure.model.Ingredient;
import my.sky.web_application_structure.service.IngredientsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/ingredients/")
public class IngredientController {
    private final IngredientsService ingredientsService;

    public IngredientController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/viewId/{ingredientId}/")
    public Ingredient findIngredientById(@PathVariable String ingredientId) {
        return ingredientsService.findIngredientById(Long.parseLong(ingredientId));
    }

    @PostMapping()
    public Map<Long, Ingredient> add(@RequestBody Ingredient ingredient) {
        return ingredientsService.createIngredient(ingredient.getIngredientID(), ingredient);
    }
}
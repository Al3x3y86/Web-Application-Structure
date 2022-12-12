package my.sky.web_application_structure.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.sky.web_application_structure.model.Recipe;
import my.sky.web_application_structure.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/recipes")
@Tag(name = "Рецепты", description = "CRUD-операции для работы с рецептами")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/viewId/{recipeId}")
    @Operation(summary = "Поиск рецепта по id",
            description = "Для поиска рецепта введите его id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Recipe.class)
                            )
                    }
            )
    })
    public Recipe findRecipeById(@PathVariable String recipeId) {
        return recipeService.findRecipeById(Long.parseLong(recipeId));
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление рецепта")
    public Map<Long, Recipe> add(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @PutMapping("/update/{recipeId}")
    @Operation(summary = "Обновление сведений по рецепту",
            description = "Для добавления сведений введите его id и новый рецепт")
    public Map<Long, Recipe> update(@PathVariable String recipeId, @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(Long.parseLong(recipeId), recipe);
    }

    @DeleteMapping("/delete/{recipeId}")
    @Operation(summary = "Удаление рецепта",
            description = "Для удаления рецепта введите его id")
    public void delete(@PathVariable String recipeId) {
        recipeService.deleteRecipe(Long.parseLong(recipeId));
    }

    @GetMapping(value = "/allRecipes")
    @Operation(summary = "Просмотр всех добавленных рецептов")
    public Map<Long, Recipe> getAllRecipes() {
        return recipeService.viewAllRecipes();
    }

}
package my.sky.web_application_structure.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.sky.web_application_structure.model.Ingredient;
import my.sky.web_application_structure.service.IngredientsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "CRUD-операции для работы с ингредиентами")
public class IngredientController {
    private final IngredientsService ingredientsService;

    public IngredientController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/viewId/{ingredientId}")
    @Operation(summary = "Поиск ингредиента по id",
            description = "Для поиска ингредиента введите его id"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиент найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Ingredient.class)
                            )
                    }
            )
    })
    public Ingredient findIngredientById(@PathVariable String ingredientId) {
        return ingredientsService.findIngredientById(Long.parseLong(ingredientId));
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление ингредиента")
    public Map<Long, Ingredient> add(@RequestBody Ingredient ingredient) {
        return ingredientsService.addIngredient(ingredient);
    }

    @PutMapping("/update/{ingredientId}")
    @Operation(summary = "Обновление сведений по ингредиенту",
            description = "Для добавления сведений введите его id и новый ингредиент")
    public Map<Long, Ingredient> update(@PathVariable String ingredientId, @RequestBody Ingredient ingredient) {
        return ingredientsService.updateIngredient(Long.parseLong(ingredientId), ingredient);
    }

    @DeleteMapping("/delete/{ingredientId}")
    @Operation(summary = "Удаление ингредиента",
            description = "Для удаления ингредиента введите его id")
    public void delete(@PathVariable String ingredientId) {
        ingredientsService.deleteIngredient(Long.parseLong(ingredientId));
    }

    @GetMapping(value = "/allIngredients")
    @Operation(summary = "Просмотр всех добавленных ингредиентов")
    public Map<Long, Ingredient> viewAllIngredients() {
        return ingredientsService.viewAllIngredients();
    }
}
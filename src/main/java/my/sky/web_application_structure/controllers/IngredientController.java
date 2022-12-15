package my.sky.web_application_structure.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import my.sky.web_application_structure.model.Ingredient;
import my.sky.web_application_structure.service.IngredientsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> findIngredientById(@PathVariable String ingredientId) {
        Ingredient ingredient = ingredientsService.findIngredientById(Long.parseLong(ingredientId));
        if (ingredient == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ингредиент не найден. (Ошибка 404)");
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(ingredient.toString());
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление ингредиента")
    public ResponseEntity<String> add(@RequestBody Ingredient ingredient) {
        if (ingredientsService.addIngredient(ingredient)) {
            return ResponseEntity.status(HttpStatus.OK).body("Ингредиент добавлен");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ингредиент не добавлен. (Ошибка 404)");
    }

    @PutMapping("/update/{ingredientId}")
    @Operation(summary = "Обновление сведений по ингредиенту",
            description = "Для добавления сведений введите его id и новый ингредиент")
    public ResponseEntity<String> update(@PathVariable String ingredientId, @RequestBody Ingredient ingredient) {
        if (ingredientsService.updateIngredient(Long.parseLong(ingredientId), ingredient)) {
            return ResponseEntity.ok().body("Ингредиент обновлен");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ингредиент не добавлен. (Ошибка 404)");
    }

    @DeleteMapping("/delete/{ingredientId}")
    @Operation(summary = "Удаление ингредиента",
            description = "Для удаления ингредиента введите его id")
    public ResponseEntity<String> delete(@PathVariable String ingredientId) {
        if (ingredientsService.deleteIngredient(Long.parseLong(ingredientId))) {
            return ResponseEntity.ok().body("Ингредиент удален");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ингредиент не найден. (Ошибка 404)");
    }

    @GetMapping(value = "/allIngredients")
    @Operation(summary = "Просмотр всех добавленных ингредиентов")
    public ResponseEntity<Map<Long, Ingredient>> viewAllIngredients() {
        return ResponseEntity.ok().body(ingredientsService.viewAllIngredients());
    }


}
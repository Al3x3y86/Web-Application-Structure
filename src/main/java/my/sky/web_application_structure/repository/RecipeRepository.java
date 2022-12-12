package my.sky.web_application_structure.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import my.sky.web_application_structure.model.Recipe;
import my.sky.web_application_structure.service.RecipeFilesService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RecipeRepository implements IdRepository<Recipe> {

    static Long idCounter = 1L;
    private Map<Long, Recipe> recipeStorage = new HashMap<>();
    private final RecipeFilesService recipeFilesService;

    public RecipeRepository(RecipeFilesService recipeFilesService) {
        this.recipeFilesService = recipeFilesService;
    }

    private boolean checkInputObject(Recipe recipe) {
        return StringUtils.isNotBlank(recipe.getTitleRecipe()) &
                StringUtils.isNotEmpty(recipe.getTitleRecipe()) &
                recipe.getCookingTimeMinutes() > 0 &
                !recipe.getIngredientsList().isEmpty() &
                !recipe.getCookingInstruction().isEmpty();
    }

    private String jsonFromList(){
        String json;
        try {
            json = new ObjectMapper().writeValueAsString(recipeStorage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    private Map<Long, Recipe> listFromFile(){
        try {
            String json = recipeFilesService.readRecipesFromFile();
            if (StringUtils.isNotEmpty(json) || StringUtils.isNotBlank(json) ){
                recipeStorage = new ObjectMapper().readValue(json, new TypeReference<>(){});
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return recipeStorage;
    }

    @Override
    public Map<Long, Recipe> add(Recipe recipe) {
        if (checkInputObject(recipe) & !recipeStorage.containsValue(recipe)) {
            idCounter = 1L;
            while (recipeStorage.containsKey(idCounter)){
                idCounter++;
            }
            recipeStorage.put(idCounter, recipe);
            idCounter++;
            recipeFilesService.saveRecipesToFile(jsonFromList());
            return recipeStorage;
        }
        return null;
    }

    @Override
    public Recipe findById(Long id) {
        if (recipeStorage.containsKey(id)) {
            return recipeStorage.get(id);
        } else {
            throw new IllegalArgumentException("Рецепт с номером " + id + " отсутствует!");
        }
    }

    @Override
    public Map<Long, Recipe> update(Long id, Recipe recipe) {
        if (!recipeStorage.containsKey(id)){
            throw new IllegalArgumentException("С таким номером рецепт отсутствует");
        }
        if (recipe != null){
            recipeStorage.remove(id);
            recipeStorage.put(id, recipe);
            recipeFilesService.saveRecipesToFile(jsonFromList());
            return recipeStorage;
        } else {
            throw new IllegalArgumentException("Поля рецепта для обновления не заполнены");
        }
    }

    @Override
    public void delete(Long id) {
        if (recipeStorage.containsKey(id)){
            recipeStorage.remove(id);
            recipeFilesService.saveRecipesToFile(jsonFromList());
        } else {
            throw new IllegalArgumentException("С таким номером рецепт отсутствует");
        }
    }

    @Override
    public Map<Long, Recipe> viewAll() {
        recipeStorage = listFromFile();
        if (!recipeStorage.isEmpty()) {
            return recipeStorage;
        }
        return null;
    }

    @PostConstruct
    private void init(){
        recipeStorage = listFromFile();
    }
}

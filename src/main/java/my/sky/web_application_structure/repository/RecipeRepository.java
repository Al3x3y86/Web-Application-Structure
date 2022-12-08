package my.sky.web_application_structure.repository;

import my.sky.web_application_structure.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RecipeRepository implements IdRepository<Recipe> {
    private final Map<Long, Recipe> recipeStorage = new HashMap<>();

    @Override
    public Map<Long, Recipe> add(Long id, Recipe recipe) {
        if (!recipeStorage.containsKey(id) & recipe != null) {
            recipeStorage.put(id, recipe);
        }
        return recipeStorage;
    }

    @Override
    public Recipe findById(Long id) {
        if (recipeStorage.containsKey(id)) {
            return recipeStorage.get(id);
        } else {
            throw new IllegalArgumentException("Рецепт с номером " + id + " отсутствует!");
        }
    }
}

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

    @Override
    public Map<Long, Recipe> update(Long id, Recipe recipe) {
        if (!recipeStorage.containsKey(id)){
            throw new IllegalArgumentException("С таким id рецепт отсутствует");
        }
        if (recipe != null){
            recipeStorage.remove(id);
            recipeStorage.put(id, recipe);
            return recipeStorage;
        } else {
            throw new IllegalArgumentException("Поля рецепта для обновления не заполнены");
        }
    }

    @Override
    public void delete(Long id) {
        if (recipeStorage.containsKey(id)){
            recipeStorage.remove(id);
        } else {
            throw new IllegalArgumentException("С таким id рецепт отсутствует");
        }
    }

    @Override
    public Map<Long, Recipe> viewAll() {
        return null;
    }
}

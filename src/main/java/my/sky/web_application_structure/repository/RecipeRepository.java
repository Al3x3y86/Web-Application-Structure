package my.sky.web_application_structure.repository;

import my.sky.web_application_structure.model.Recipe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RecipeRepository implements IdRepository<Recipe> {

    static Long idCounter = 1L;
    private final Map<Long, Recipe> recipeStorage = new HashMap<>();

    private boolean checkInputObject(Recipe recipe) {
        return StringUtils.isNotBlank(recipe.getTitleRecipe()) &
                StringUtils.isNotEmpty(recipe.getTitleRecipe()) &
                recipe.getCookingTimeMinutes() > 0 &
                !recipe.getIngredientsList().isEmpty() &
                !recipe.getCookingInstruction().isEmpty();
    }

    @Override
    public Map<Long, Recipe> add(Recipe recipe) {
        if (checkInputObject(recipe) & !recipeStorage.containsValue(recipe)) {
            recipeStorage.put(idCounter, recipe);
            idCounter++;
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
            throw new IllegalArgumentException("С таким номером рецепт отсутствует");
        }
    }

    @Override
    public Map<Long, Recipe> viewAll() {
        if (!recipeStorage.isEmpty()) {
            return recipeStorage;
        }
        return null;
    }
}

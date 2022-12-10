package my.sky.web_application_structure.repository;

import my.sky.web_application_structure.model.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class IngredientsRepository implements IdRepository<Ingredient> {
    private final Map<Long, Ingredient> ingredientsStorage = new HashMap<>();

    {
        ingredientsStorage.put(1L, new Ingredient("Творог", 350, "г"));
        ingredientsStorage.put(2L, new Ingredient("Куриное яйцо", 2, "штуки"));
        ingredientsStorage.put(3L, new Ingredient("Пшеничная мука", 6, "столовых ложек"));
        ingredientsStorage.put(4L, new Ingredient("Сахар", 2, "столовые ложки"));
        ingredientsStorage.put(5L, new Ingredient("Специи", 0, "по вкусу"));
        ingredientsStorage.put(6L, new Ingredient("Консервированная фасоль", 400, "г"));
        ingredientsStorage.put(7L, new Ingredient("Лимон", 1, "штука"));
        ingredientsStorage.put(8L, new Ingredient("Чеснок", 2, "зубчика"));
        ingredientsStorage.put(9L, new Ingredient("Оливковое масло", 50, "мл"));
        ingredientsStorage.put(10L, new Ingredient("Руккола", 50, "г"));
        ingredientsStorage.put(11L, new Ingredient("Творожный сыр", 200, "г"));
        ingredientsStorage.put(12L, new Ingredient("Красный лук", 50, "г"));
        ingredientsStorage.put(13L, new Ingredient("Красная чечевица", 150, "г"));
        ingredientsStorage.put(14L, new Ingredient("Репчатый лук", 1, "головка"));
        ingredientsStorage.put(15L, new Ingredient("Морковь", 1, "штука"));
        ingredientsStorage.put(16L, new Ingredient("Подсолнечное масло", 30, "мл"));
        ingredientsStorage.put(17L, new Ingredient("Сливочное масло", 15, "г"));
        ingredientsStorage.put(18L, new Ingredient("Пшеничная мука", 30, "г"));
        ingredientsStorage.put(19L, new Ingredient("Сливки 33%", 50, "мл"));
        ingredientsStorage.put(20L, new Ingredient("Лимон", 0, "0.5 штуки"));
        ingredientsStorage.put(21L, new Ingredient("Мята", 50, "по вкусу"));
        ingredientsStorage.put(23L, new Ingredient("Помидоры", 3, "штуки"));
        ingredientsStorage.put(24L, new Ingredient("Королевские креветки", 300, "г"));
        ingredientsStorage.put(25L, new Ingredient("Зеленый салат", 1, "пучек"));
        ingredientsStorage.put(26L, new Ingredient("Огурцы", 2, "штуки"));
        ingredientsStorage.put(27L, new Ingredient("Майонез", 0, "по вкусу"));
        ingredientsStorage.put(28L, new Ingredient("Кунжутные семечки", 3, "столовые ложки"));
    }

    @Override
    public Map<Long, Ingredient> add(Long id, Ingredient ingredient) {
        if (!ingredientsStorage.containsKey(id) & ingredient != null) {
            ingredientsStorage.put(id, ingredient);
            return ingredientsStorage;
        }
        if (ingredientsStorage.containsKey(id)){
            throw new IllegalArgumentException("С таким номером ингредиент уже есть");
        }
        if (ingredient == null) {
            throw new IllegalArgumentException("Ингредиент == null");
        }
        return null;
    }

    @Override
    public Ingredient findById(Long id) {
        if (ingredientsStorage.containsKey(id)) {
            return ingredientsStorage.get(id);
        } else {
            throw new IllegalArgumentException("Ингредиент с номером " + id + " отсутствует!");
        }
    }

    @Override
    public Map<Long, Ingredient> update(Long id, Ingredient ingredient) {
        if (!ingredientsStorage.containsKey(id)){
            throw new IllegalArgumentException("С таким номером ингредиент отсутствует");
        }
        if (ingredient != null){
            ingredientsStorage.remove(id);
            ingredientsStorage.put(id, ingredient);
            return ingredientsStorage;
        } else {
            throw new IllegalArgumentException("Поля ингредиента для обновления не заполнены");
        }
    }

    @Override
    public void delete(Long id) {
        if (ingredientsStorage.containsKey(id)){
            ingredientsStorage.remove(id);
        } else {
            throw new IllegalArgumentException("С таким номером ингредиент отсутствует");
        }
    }

    @Override
    public Map<Long, Ingredient> viewAll() {
        if (!ingredientsStorage.isEmpty()) {
            return ingredientsStorage;
        }
        return null;
    }
}

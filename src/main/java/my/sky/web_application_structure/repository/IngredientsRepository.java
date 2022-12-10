package my.sky.web_application_structure.repository;

import my.sky.web_application_structure.model.Ingredient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class IngredientsRepository implements IdRepository<Ingredient> {

    static Long idCounter = 1L;
    private final Map<Long, Ingredient> ingredientsStorage = new HashMap<>();

    {
        add(new Ingredient("Творог", 350, "г"));
        add(new Ingredient("Куриное яйцо", 2, "штуки"));
        add(new Ingredient("Пшеничная мука", 6, "столовых ложек"));
        add(new Ingredient("Сахар", 2, "столовые ложки"));
        add(new Ingredient("Специи", 0, "по вкусу"));
        add(new Ingredient("Консервированная фасоль", 400, "г"));
        add(new Ingredient("Лимон", 1, "штука"));
        add(new Ingredient("Чеснок", 2, "зубчика"));
        add(new Ingredient("Оливковое масло", 50, "мл"));
        add(new Ingredient("Руккола", 50, "г"));
        add(new Ingredient("Творожный сыр", 200, "г"));
        add(new Ingredient("Красный лук", 50, "г"));
        add(new Ingredient("Красная чечевица", 150, "г"));
        add(new Ingredient("Репчатый лук", 1, "головка"));
        add(new Ingredient("Морковь", 1, "штука"));
        add(new Ingredient("Подсолнечное масло", 30, "мл"));
        add(new Ingredient("Сливочное масло", 15, "г"));
        add(new Ingredient("Пшеничная мука", 30, "г"));
        add(new Ingredient("Сливки 33%", 50, "мл"));
        add(new Ingredient("Лимон", 0, "0.5 штуки"));
        add(new Ingredient("Мята", 50, "по вкусу"));
        add(new Ingredient("Помидоры", 3, "штуки"));
        add(new Ingredient("Королевские креветки", 300, "г"));
        add(new Ingredient("Зеленый салат", 1, "пучек"));
        add(new Ingredient("Огурцы", 2, "штуки"));
        add(new Ingredient("Майонез", 0, "по вкусу"));
        add(new Ingredient("Кунжутные семечки", 3, "столовые ложки"));
    }

    private boolean checkInPutObject(Ingredient ingredient) {
        return !StringUtils.isBlank(ingredient.getTitleIngredient()) &
                !StringUtils.isEmpty(ingredient.getTitleIngredient()) &
                ingredient.getAmount() >= 0 &
                !StringUtils.isBlank(ingredient.getMeasureUnit()) &
                !StringUtils.isEmpty(ingredient.getMeasureUnit());
    }

    @Override
    public Map<Long, Ingredient> add(Ingredient ingredient) {
        if (checkInPutObject(ingredient) & !ingredientsStorage.containsValue(ingredient)) {
            ingredientsStorage.put(idCounter, ingredient);
            idCounter++;
            return ingredientsStorage;
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

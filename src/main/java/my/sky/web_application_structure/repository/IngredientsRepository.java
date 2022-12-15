package my.sky.web_application_structure.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import my.sky.web_application_structure.model.Ingredient;
import my.sky.web_application_structure.service.IngredientsFilesService;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class IngredientsRepository implements IdRepository<Ingredient> {

    static Long idCounter = 1L;
    private Map<Long, Ingredient> ingredientsStorage = new HashMap<>();
    private final IngredientsFilesService ingredientsFilesService;

    public IngredientsRepository(IngredientsFilesService ingredientsFilesService) {
        this.ingredientsFilesService = ingredientsFilesService;
    }
    private String jsonFromList() {
        String json;
        try {
            json = new ObjectMapper().writeValueAsString(ingredientsStorage);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    private Map<Long, Ingredient> listFromFile() {
        try {
            String json = ingredientsFilesService.readIngredientsFromFile();
            if (StringUtils.isNotEmpty(json) || StringUtils.isNotBlank(json)) {
                ingredientsStorage = new ObjectMapper().readValue(json, new TypeReference<>() {
                });
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return ingredientsStorage;
    }



    private boolean checkInPutObject(Ingredient ingredient) {
        return !StringUtils.isBlank(ingredient.getTitleIngredient()) &
                !StringUtils.isEmpty(ingredient.getTitleIngredient()) &
                ingredient.getAmount() >= 0 &
                !StringUtils.isBlank(ingredient.getMeasureUnit()) &
                !StringUtils.isEmpty(ingredient.getMeasureUnit());
    }

    @Override
    public boolean add(Ingredient ingredient) {
        if (checkInPutObject(ingredient) & !ingredientsStorage.containsValue(ingredient)) {
            idCounter = 1L;
            while (ingredientsStorage.containsKey(idCounter)) {
                idCounter++;
            }
            ingredientsStorage.put(idCounter, ingredient);
            idCounter++;
            ingredientsFilesService.saveIngredientsToFile(jsonFromList());
            return true;
        }
        return false;
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
    public boolean update(Long id, Ingredient ingredient) {
        if (!ingredientsStorage.containsKey(id)){
            throw new IllegalArgumentException("С таким номером ингредиент отсутствует");
        }
        if (ingredient != null){
            ingredientsStorage.remove(id);
            ingredientsStorage.put(id, ingredient);
            ingredientsFilesService.saveIngredientsToFile(jsonFromList());
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (ingredientsStorage.containsKey(id)){
            ingredientsStorage.remove(id);
            ingredientsFilesService.saveIngredientsToFile(jsonFromList());
            return true;
        }
        return false;
    }

    @Override
    public Map<Long, Ingredient> viewAll() {
        ingredientsStorage = listFromFile();
        if (ingredientsStorage != null && !ingredientsStorage.isEmpty()) {
            return ingredientsStorage;
        }
        return null;
    }

    @PostConstruct
    private void init() {
        ingredientsStorage = listFromFile();
    }
}

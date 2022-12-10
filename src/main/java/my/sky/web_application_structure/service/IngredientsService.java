package my.sky.web_application_structure.service;

import my.sky.web_application_structure.model.Ingredient;
import my.sky.web_application_structure.repository.IngredientsRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IngredientsService {
    private final IngredientsRepository ingredientsRepository;

    public IngredientsService(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public Ingredient findIngredientById(Long ingredientId) {
        return ingredientsRepository.findById(ingredientId);
    }

    public Map<Long, Ingredient> createIngredient(Long ingredientID, Ingredient ingredient) {
        return ingredientsRepository.add(ingredientID, ingredient);
    }
}

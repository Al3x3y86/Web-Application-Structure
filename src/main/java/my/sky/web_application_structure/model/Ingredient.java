package my.sky.web_application_structure.model;

import lombok.Data;

@Data
public class Ingredient {

    private String titleIngredient;
    private int amount;
    private String measureUnit;

    public Ingredient(String titleIngredient, int amount, String measureUnit) {
        this.titleIngredient = titleIngredient;
        this.amount = amount;
        this.measureUnit = measureUnit;
        ingredientID = counterID;
        counterID++;
    }

    private Long ingredientID;
    static Long counterID = 1L;

    public Ingredient() {
        ingredientID = counterID;
        counterID++;
    }
}
package my.sky.web_application_structure.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredient {

    private String titleIngredient;
    private int amount;
    private String measureUnit;


}
package my.sky.web_application_structure.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private String titleIngredient;
    private int amount;
    private String measureUnit;


}
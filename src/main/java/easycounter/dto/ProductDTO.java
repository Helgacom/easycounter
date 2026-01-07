package easycounter.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Double caloriesPer100g;

    @NotNull
    private Double protein;

    @NotNull
    private Double fat;

    @NotNull
    private Double carb;

    private String description;
}

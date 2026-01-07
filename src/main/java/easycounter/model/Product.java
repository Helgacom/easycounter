package easycounter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "calories_per_100g")
    private Double caloriesPer100g;

    @NotNull
    private Double protein;

    @NotNull
    private Double fat;

    @NotNull
    private Double carb;

    private String description;
}

package org.example.subd.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;

    @OneToMany(
            mappedBy = "ingredient",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<IngredientMaterial> ingredientMaterials = new ArrayList<>();

}

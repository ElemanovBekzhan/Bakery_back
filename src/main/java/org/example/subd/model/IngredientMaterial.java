package org.example.subd.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.subd.enums.Unit;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ingredient_material")
public class IngredientMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
    Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "material_id")
    Material material;

    BigDecimal quantity;
    Unit unit;
}

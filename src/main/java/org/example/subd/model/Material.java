package org.example.subd.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.subd.enums.Unit;

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
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    String name;
    BigDecimal quantity;
    BigDecimal amount;

    @Enumerated(EnumType.STRING)
    Unit unit;


    @OneToMany(mappedBy = "material")
    List<IngredientMaterial> ingredientMaterials = new ArrayList<>();

    @OneToMany(mappedBy = "material")
    List<Purchase_material> purchase_materials = new ArrayList<>();
}
package org.example.subd.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.subd.enums.Role;

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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String name;
    BigDecimal quantity;
    BigDecimal amount;


    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    Ingredient ingredient;



    @OneToMany(mappedBy = "product")
    List<Production> productions = new ArrayList<>();


    @OneToMany(mappedBy = "product")
    List<Sale> sales = new ArrayList<>();
}

package org.example.subd.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    BigDecimal amount;
    BigDecimal percent;
    BigDecimal bonus;
}

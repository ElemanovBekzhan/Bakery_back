package org.example.subd.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(precision = 15, scale = 2)
    BigDecimal sum;
    int month;

    @Column(name = "[percent]", precision = 5, scale = 2)
    BigDecimal percent;

    @Column(precision = 15, scale = 2)
    BigDecimal fine;

    @Column(nullable = false)
    LocalDate receiptDate;

    boolean isPaid;

}

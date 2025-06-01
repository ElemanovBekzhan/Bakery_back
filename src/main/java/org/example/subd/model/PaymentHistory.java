package org.example.subd.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(precision = 15, scale = 2)
    BigDecimal baseAmount;

    @Column(precision = 15, scale = 2)
    BigDecimal percentAmount;

    @Column(precision = 15, scale = 2)
    BigDecimal allAmount;

    @Column(precision = 15, scale = 2)
    BigDecimal fineAmount;

    @Column(precision = 15, scale = 2)
    BigDecimal totalAmount;

    @Column(precision = 15, scale = 2)
    BigDecimal residue;

    LocalDate peymentDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    Bank bank;
}

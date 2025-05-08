package org.example.subd.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    Employee employee;
    LocalDate salaryDate;

    @Column(nullable = false)
    BigDecimal salary;
    @Column(nullable = false)
    BigDecimal bonus = BigDecimal.ZERO;
    @Column(nullable = false)
    BigDecimal fine = BigDecimal.ZERO;
    @Column(name = "total", insertable = false, updatable = false)
    BigDecimal total;
    @Column(nullable = false)
    Boolean isIssued = false;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    LocalDate createdAt;

    Integer purchaseCount = 0;
    Integer productionCount = 0;
    Integer saceCount = 0;
}
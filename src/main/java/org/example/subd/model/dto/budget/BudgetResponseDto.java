package org.example.subd.model.dto.budget;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BudgetResponseDto {
    UUID id;
    BigDecimal amount;
    BigDecimal bonus;
    BigDecimal percent;
}

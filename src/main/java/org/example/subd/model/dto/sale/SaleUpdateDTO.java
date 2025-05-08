package org.example.subd.model.dto.sale;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.subd.enums.Unit;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleUpdateDTO {
    UUID id;
    UUID employeeId;
    UUID productId;
    BigDecimal quantity;
    BigDecimal amount;
}
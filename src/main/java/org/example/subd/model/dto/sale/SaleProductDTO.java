package org.example.subd.model.dto.sale;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleProductDTO {
    UUID employeeId;
    UUID productId;
    BigDecimal quantity;
    BigDecimal amount;
}

package org.example.subd.model.dto.purchase;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseDTO {

    UUID materialId;
    BigDecimal quantity;
    BigDecimal amount;
    UUID employeeId;

}

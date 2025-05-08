package org.example.subd.model.dto.productions;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductionDTO {

    UUID employeeId;
    UUID productId;
    BigDecimal quantity;

}

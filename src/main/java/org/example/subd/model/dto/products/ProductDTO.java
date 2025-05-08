package org.example.subd.model.dto.products;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {
    String name;
    BigDecimal quantity;
    BigDecimal amount;
}

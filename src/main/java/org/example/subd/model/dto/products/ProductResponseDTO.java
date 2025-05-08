package org.example.subd.model.dto.products;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponseDTO {

    UUID id;
    String name;
    BigDecimal quantitiy;
    BigDecimal amount;

}

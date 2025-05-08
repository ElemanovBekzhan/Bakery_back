package org.example.subd.model.dto.sale;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SaleResponseDTO {

    UUID id;
    String employeeName;
    String productName;
    BigDecimal quantity;
    BigDecimal amount;
    LocalDate saleDate;
}

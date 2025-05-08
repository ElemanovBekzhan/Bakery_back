package org.example.subd.model.dto.purchase;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseMaterialResponseDTO {


    UUID id;
    String materialName;
    String EmployeeName;
    BigDecimal quantity;
    BigDecimal amount;
    LocalDateTime created_at;



}

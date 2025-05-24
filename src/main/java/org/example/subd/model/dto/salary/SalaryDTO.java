package org.example.subd.model.dto.salary;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SalaryDTO {
    UUID id;
    String employeeName;
    String salaryDate;
    BigDecimal salary;
    BigDecimal bonus;
    BigDecimal fine;
    BigDecimal total;
    Boolean isIssued;

    Integer purchaseCount;
    Integer productionCount;
    Integer saleCount;

}

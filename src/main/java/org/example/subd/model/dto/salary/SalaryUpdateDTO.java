package org.example.subd.model.dto.salary;

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
public class SalaryUpdateDTO {
    UUID id;
    BigDecimal bonus;
    BigDecimal fine;
}

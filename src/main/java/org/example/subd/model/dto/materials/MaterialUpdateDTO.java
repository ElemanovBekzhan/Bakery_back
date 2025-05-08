package org.example.subd.model.dto.materials;

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
public class MaterialUpdateDTO {

    UUID id;
    String name;
    BigDecimal quantity;
    BigDecimal amount;
    Unit unit;
}

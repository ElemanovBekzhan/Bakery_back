package org.example.subd.model.dto.materials;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.subd.enums.Unit;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaterialResponseDTO {
    String materialName;
    BigDecimal quantity;
    Unit unit;
}

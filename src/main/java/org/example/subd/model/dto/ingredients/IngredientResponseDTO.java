package org.example.subd.model.dto.ingredients;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.subd.model.dto.materials.MaterialResponseDTO;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientResponseDTO {
    UUID id;
    String productName;
    List<MaterialResponseDTO> materials;
}
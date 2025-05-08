package org.example.subd.model.dto.ingredients;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.subd.enums.Unit;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredienUpdateDTO {

    UUID id;
    UUID productId;
    List<IngredientMaterialDto> ingredientMaterials; //Материалы с количеством

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IngredientMaterialDto{
        UUID materialId; //Айдишка материала
        BigDecimal quantity; //Количество материала
        Unit unit;
    }
}

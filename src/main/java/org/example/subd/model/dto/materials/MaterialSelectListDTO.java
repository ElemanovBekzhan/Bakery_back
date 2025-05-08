package org.example.subd.model.dto.materials;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaterialSelectListDTO {
    UUID id;
    String name;
}

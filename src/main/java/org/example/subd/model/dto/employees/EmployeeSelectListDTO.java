package org.example.subd.model.dto.employees;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeSelectListDTO {
    UUID id;
    String name;
}

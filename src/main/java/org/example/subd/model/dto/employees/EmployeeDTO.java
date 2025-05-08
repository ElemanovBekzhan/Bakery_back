package org.example.subd.model.dto.employees;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.subd.enums.Role;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeDTO {
    String name;
    BigDecimal salary;
    String address;
    String phone;
    String email;
    String password;
    Role role;
    UUID positionid;
}

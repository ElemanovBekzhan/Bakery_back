package org.example.subd.model.dto.employees;


import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.subd.enums.Role;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateDto {
    String full_name;
    String email;
    String phone_number;
    Role role;
    BigDecimal salary;
    String address;
}

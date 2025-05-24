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
public class UserResponseDto {
    UUID id;
    String full_name;
    String email;
    String phone_number;
    String role;
    BigDecimal salary;
    String address;
}
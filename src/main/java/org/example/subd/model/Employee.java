package org.example.subd.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.subd.enums.Role;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    String full_name;
    BigDecimal salary;
    String address;
    String phone_number;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;




    @OneToMany(mappedBy = "employee")
    List<Purchase_material> purchase_materials = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    List<Production> productions = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    List<Sale> sales = new ArrayList<>();


}

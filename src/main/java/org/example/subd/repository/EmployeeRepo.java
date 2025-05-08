package org.example.subd.repository;


import org.example.subd.model.Employee;
import org.example.subd.model.dto.employees.EmployeeSelectListDTO;
import org.example.subd.model.dto.materials.MaterialSelectListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID> {

    @Query("select new org.example.subd.model.dto.employees.EmployeeSelectListDTO(m.id, m.full_name) from Employee m")
    List<EmployeeSelectListDTO> findAllEmployeeSelectList();

    Optional<Employee> findByEmail(String email);
}

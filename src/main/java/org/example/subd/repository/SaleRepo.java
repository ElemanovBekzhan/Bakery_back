package org.example.subd.repository;


import org.example.subd.model.Employee;
import org.example.subd.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface SaleRepo extends JpaRepository<Sale, UUID> {
    int countByEmployeeAndCreatedAtBetween(Employee employee, LocalDate from, LocalDate to);
}

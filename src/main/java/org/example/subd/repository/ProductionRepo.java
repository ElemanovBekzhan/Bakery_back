package org.example.subd.repository;

import org.example.subd.model.Employee;
import org.example.subd.model.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public interface ProductionRepo extends JpaRepository<Production, UUID> {

    int countByEmployeeAndCreatedAtBetween(Employee employee, LocalDate from, LocalDate to);
}

package org.example.subd.repository;

import org.example.subd.model.Employee;
import org.example.subd.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalaryRepo extends JpaRepository<Salary, UUID> {

    boolean existsByEmployeeAndSalaryDate(Employee employee, LocalDate date);

    Optional<Salary> findTopByEmployeeAndIsIssuedOrderBySalaryDateDesc(Employee employee, Boolean isIssued);

    List<Salary> findAllByIsIssuedFalse();

    boolean findAllBySalaryDateBetween(LocalDate fromDate, LocalDate toDate);

    List<Salary> findAllBySalaryDateBetweenOrderBySalary(LocalDate fromDate, LocalDate toDate);
}

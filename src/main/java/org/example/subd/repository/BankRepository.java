package org.example.subd.repository;


import org.example.subd.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<Bank, UUID> {
    List<Bank> findByReceiptDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    Bank getReferenceById(Long id);
}

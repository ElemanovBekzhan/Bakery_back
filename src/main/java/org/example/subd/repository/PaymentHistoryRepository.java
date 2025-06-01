package org.example.subd.repository;

import org.example.subd.model.PaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, UUID> {
    List<PaymentHistory> findByBankId(UUID bankId);



    @Query("SELECT COALESCE(SUM(ph.totalAmount), 0) FROM PaymentHistory ph WHERE ph.bank.id = :bankId")
    BigDecimal calculateTotalAmountByBankId(@Param("bankId") UUID bankId);
}

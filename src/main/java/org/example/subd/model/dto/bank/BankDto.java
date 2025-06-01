package org.example.subd.model.dto.bank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BankDto(
        Long id,
        BigDecimal sum,
        int month,
        BigDecimal percent,
        BigDecimal fine,
        LocalDate receiptDate,
        boolean isPaid
) {
}

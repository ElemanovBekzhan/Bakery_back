package org.example.subd.model.dto.payment;

import org.example.subd.model.dto.bank.BankDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record PaymentHistoryDto(
        UUID id,
        BigDecimal baseAmount,
        BigDecimal percentAmount,
        BigDecimal allAmount,
        BigDecimal fineAmount,
        BigDecimal totalAmount,
        BigDecimal residue,
        LocalDate paymentDate,
        BankDto bank
) {
}

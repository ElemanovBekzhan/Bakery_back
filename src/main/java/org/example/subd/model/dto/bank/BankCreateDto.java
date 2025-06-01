package org.example.subd.model.dto.bank;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BankCreateDto(
        @NotNull @Positive BigDecimal sum,
        @Min(1) int month,
        @NotNull @Positive BigDecimal percent,
        @PositiveOrZero BigDecimal fine,
        @NotNull LocalDate receiptDate,
        boolean isPaid
) {}

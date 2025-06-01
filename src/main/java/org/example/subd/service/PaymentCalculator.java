package org.example.subd.service;


import lombok.RequiredArgsConstructor;
import org.example.subd.model.Bank;
import org.example.subd.model.PaymentHistory;
import org.example.subd.model.dto.payment.PaymentHistoryDto;
import org.example.subd.model.mapper.PaymentHistoryMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class PaymentCalculator {
    private final PaymentHistoryMapper paymentHistoryMapper;

    private static final MathContext MC = new MathContext(10, RoundingMode.HALF_UP);
    private static final BigDecimal MONTHS_IN_YEAR = BigDecimal.valueOf(12);
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);


    public PaymentHistoryDto calculatePayment(Bank bank, LocalDate paymentDate){
        if(bank.isPaid()){
            return new PaymentHistoryDto(
                    null,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO,
                    paymentDate,
                    null
            );
        }


        BigDecimal monthlyRate = bank.getPercent()
                .divide(ONE_HUNDRED, MC)
                .divide(MONTHS_IN_YEAR, MC);

        BigDecimal annuity = calculateAnnuity(bank.getSum(), monthlyRate, bank.getMonth());

        BigDecimal baseAmount = calculateBaseAmount(bank, annuity, monthlyRate);

        BigDecimal percentAmount = calculatePercentAmount(bank, monthlyRate);

        BigDecimal fineAmount = calculateFineAmount(bank, paymentDate);

        BigDecimal allAmount = baseAmount.add(percentAmount);
        BigDecimal totalAmount = allAmount.add(fineAmount);

        BigDecimal resuide = bank.getSum().subtract(baseAmount);

        return new PaymentHistoryDto(
                null,
                baseAmount,
                percentAmount,
                allAmount,
                fineAmount,
                totalAmount,
                resuide,
                paymentDate,
                null
        );
    }

    private BigDecimal calculateAnnuity(BigDecimal sum, BigDecimal monthlyRate, int months) {
        BigDecimal rateFactor = BigDecimal.ONE.add(monthlyRate);
        BigDecimal denominator = BigDecimal.ONE.subtract(
                rateFactor.pow(-months, MC)
        );

        return sum.multiply(monthlyRate)
                .divide(denominator, MC)
                .setScale(2, RoundingMode.HALF_UP);
    }



    private BigDecimal calculateBaseAmount(Bank bank, BigDecimal annuity, BigDecimal monthlyRate) {
        // Основная часть = Аннуитет - Проценты за месяц
        BigDecimal interest = bank.getSum().multiply(monthlyRate);
        return annuity.subtract(interest)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculatePercentAmount(Bank bank, BigDecimal monthlyRate) {
        // Проценты = Остаток долга * Месячная ставка
        return bank.getSum().multiply(monthlyRate)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateFineAmount(Bank bank, LocalDate paymentDate) {
        // 1. Проверка просрочки
        LocalDate dueDate = bank.getReceiptDate().plusMonths(1);
        if (!paymentDate.isAfter(dueDate)) {
            return BigDecimal.ZERO;
        }

        // 2. Расчет дней просрочки
        long daysOverdue = ChronoUnit.DAYS.between(dueDate, paymentDate);
        if (daysOverdue <= 0) {
            return BigDecimal.ZERO;
        }

        // 3. Расчет штрафа
        BigDecimal dailyFineRate = bank.getFine()
                .divide(ONE_HUNDRED, MC)
                .divide(BigDecimal.valueOf(365), MC);

        return bank.getSum()
                .multiply(dailyFineRate)
                .multiply(BigDecimal.valueOf(daysOverdue))
                .setScale(2, RoundingMode.HALF_UP);
    }
}



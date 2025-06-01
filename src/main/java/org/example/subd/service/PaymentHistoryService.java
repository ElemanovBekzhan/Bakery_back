package org.example.subd.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.subd.model.Bank;
import org.example.subd.model.PaymentHistory;
import org.example.subd.model.dto.payment.PaymentHistoryDto;
import org.example.subd.model.mapper.PaymentHistoryMapper;
import org.example.subd.repository.BankRepository;
import org.example.subd.repository.PaymentHistoryRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryRepository paymentHistoryRepository;
    private final BankRepository bankRepository;
    private final PaymentHistoryMapper paymentHistoryMapper;
    private final PaymentCalculator paymentCalculator;

    public PaymentHistoryDto getPaymentHistory(UUID bankId, LocalDate paymentDate){
        Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new EntityNotFoundException("Bank not found"));
        return paymentCalculator.calculatePayment(bank, paymentDate);
    }

    public UUID savePaymentHistory(PaymentHistoryDto dto){
        PaymentHistory entity = paymentHistoryMapper.toEntity(dto);
        entity.setBank(bankRepository.getReferenceById(dto.bank().id()));
        PaymentHistory saved = paymentHistoryRepository.save(entity);
        return saved.getId();
    }

    public List<PaymentHistoryDto> getPaymentHistories(UUID bankId) {
        return paymentHistoryMapper.toDtoListWithoutBank(paymentHistoryRepository.findByBankId(bankId));
    }

    public BigDecimal getPaymentTotalAmount(UUID bankId) {
        return paymentHistoryRepository.calculateTotalAmountByBankId(bankId);
    }
}

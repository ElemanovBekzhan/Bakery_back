package org.example.subd.model.mapper;


import org.example.subd.model.PaymentHistory;
import org.example.subd.model.dto.payment.PaymentHistoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentHistoryMapper {
    PaymentHistoryDto toDto(PaymentHistory entity);
    PaymentHistory toEntity(PaymentHistoryDto dto);

    @Mapping(target = "bank", ignore = true)
    PaymentHistoryDto toDtoWithoutBank(PaymentHistory entity);

    default List<PaymentHistoryDto> toDtoListWithoutBank(List<PaymentHistory> entities) {
        return entities.stream()
                .map(this::toDtoWithoutBank)
                .toList();
    }
}

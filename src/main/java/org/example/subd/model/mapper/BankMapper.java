package org.example.subd.model.mapper;


import org.example.subd.model.Bank;
import org.example.subd.model.dto.bank.BankCreateDto;
import org.example.subd.model.dto.bank.BankDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankMapper {
    Bank toEntity(BankCreateDto dto);
    BankDto toDto(Bank entity);
}

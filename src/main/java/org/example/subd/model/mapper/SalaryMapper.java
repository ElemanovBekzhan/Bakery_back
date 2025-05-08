package org.example.subd.model.mapper;


import org.example.subd.model.Employee;
import org.example.subd.model.Salary;
import org.example.subd.model.dto.salary.SalaryDTO;
import org.example.subd.model.dto.salary.SalaryUpdateDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SalaryMapper {


    public static SalaryDTO toDto(Salary s) {
        SalaryDTO dto = new SalaryDTO();
        dto.setId(s.getId());
        dto.setEmployeeName(s.getEmployee().getFull_name());
        dto.setSalaryDate(dto.getSalaryDate());
        dto.setSalary(s.getSalary());
        dto.setBonus(s.getBonus());
        dto.setFine(s.getFine());
        dto.setTotal(s.getTotal());
        dto.setIsIssued(s.getIsIssued());
        dto.setPurchaseCount(s.getPurchaseCount());
        dto.setProductionCount(s.getProductionCount());
        dto.setSaleCount(s.getSaceCount());
        return dto;
    }

    public static List<SalaryDTO> toDtoList(List<Salary> s) {
        return s.stream()
                .map(SalaryMapper::toDto)
                .collect(Collectors.toList());
    }



}

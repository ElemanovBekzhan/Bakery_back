package org.example.subd.model.mapper;


import org.example.subd.model.Budget;
import org.example.subd.model.dto.budget.BudgetRequestDto;
import org.example.subd.model.dto.budget.BudgetResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BudgetMapper {


    public BudgetResponseDto toDto(Budget budget) {
        BudgetResponseDto dto = new BudgetResponseDto();
        dto.setId(budget.getId());
        dto.setAmount(budget.getAmount());
        dto.setPercent(budget.getPercent());
        dto.setBonus(budget.getBonus());
        return dto;
    }


    public void updatePatch(BudgetRequestDto dto, Budget budget) {
        if(dto.getAmount() != null) {
            budget.setAmount(dto.getAmount());
        }
        if(dto.getBonus() != null){
            budget.setBonus(dto.getBonus());
        }
        if(dto.getPercent() != null) {
            budget.setPercent(dto.getPercent());
        }
    }
}

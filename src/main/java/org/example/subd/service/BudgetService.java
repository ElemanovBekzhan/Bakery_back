package org.example.subd.service;


import lombok.RequiredArgsConstructor;
import org.example.subd.model.Budget;
import org.example.subd.model.dto.budget.BudgetRequestDto;
import org.example.subd.model.dto.budget.BudgetResponseDto;
import org.example.subd.model.mapper.BudgetMapper;
import org.example.subd.repository.BudgetRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final BudgetRepo budgetRepo;
    private final BudgetMapper budgetMapper;

    @Transactional(readOnly = true)
    public BudgetResponseDto getBudget(){
        BudgetResponseDto dto = budgetMapper.toDto(budgetRepo.findAll().get(0));
        return dto;
    }

    @Transactional
    public void updateBudget(BudgetRequestDto dto, UUID id){
        Budget b = budgetRepo.findById(id).get();
        budgetMapper.updatePatch(dto, b);
        budgetRepo.save(b);
    }
}

package org.example.subd.controller;


import lombok.RequiredArgsConstructor;
import org.example.subd.model.dto.budget.BudgetRequestDto;
import org.example.subd.model.dto.budget.BudgetResponseDto;
import org.example.subd.service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {


    private final BudgetService budgetService;

    @GetMapping
    public ResponseEntity<BudgetResponseDto> getBudget() {
        return ResponseEntity.ok(budgetService.getBudget());
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBudget(@PathVariable UUID id, @RequestBody BudgetRequestDto dto) {
        budgetService.updateBudget(dto, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

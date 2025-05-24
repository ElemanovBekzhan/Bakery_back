package org.example.subd.repository;

import org.example.subd.model.Budget;
import org.example.subd.model.dto.budget.BudgetResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BudgetRepo extends JpaRepository<Budget, UUID> {
}

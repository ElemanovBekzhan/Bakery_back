package org.example.subd.service;


import lombok.RequiredArgsConstructor;
import org.example.subd.model.Budget;
import org.example.subd.model.Employee;
import org.example.subd.model.Material;
import org.example.subd.model.Purchase_material;
import org.example.subd.model.dto.purchase.PurchaseDTO;
import org.example.subd.model.dto.purchase.PurchaseMaterialResponseDTO;
import org.example.subd.model.mapper.PurchaseMapper;
import org.example.subd.repository.BudgetRepo;
import org.example.subd.repository.EmployeeRepo;
import org.example.subd.repository.MaterialRepo;
import org.example.subd.repository.PurchaseRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepo purchaseRepo;
    private final EmployeeRepo employeeRepo;
    private final MaterialRepo materialRepo;
    private final PurchaseMapper purchaseMapper;
    private final BudgetRepo budgetRepo;



    @Transactional
    public void purchaseMaterial(PurchaseDTO dto){
        Employee employee = employeeRepo.findById(dto.getEmployeeId()).orElseThrow(() -> new RuntimeException("Сотрудник не " +
                "найден"));
        Material material = materialRepo.findById(dto.getMaterialId()).orElseThrow(() -> new RuntimeException("Материал не " +
                "найден"));

        Budget budget = budgetRepo.findAll().stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Бюджета нет"));

        if(budget.getAmount().compareTo(dto.getAmount()) < 0){
            throw new RuntimeException("Недостаточно средств");
        }

        budget.setAmount(budget.getAmount().subtract(dto.getAmount()));
        budgetRepo.save(budget);

        BigDecimal newQuantity = material.getQuantity().add(dto.getQuantity());
        BigDecimal newAmount = material.getAmount().add(dto.getAmount());

        material.setQuantity(newQuantity);
        material.setAmount(newAmount);
        materialRepo.save(material);

        Purchase_material purchaseMaterial = purchaseMapper.toEntity(dto, employee, material);
        purchaseRepo.save(purchaseMaterial);

    }


    public List<PurchaseMaterialResponseDTO> getPurchase(){
        List<Purchase_material> all = purchaseRepo.findAll();
        return purchaseMapper.toDtoList(all);
    }
}
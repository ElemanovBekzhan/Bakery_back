package org.example.subd.service;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.subd.model.Budget;
import org.example.subd.model.Employee;
import org.example.subd.model.Product;
import org.example.subd.model.Sale;
import org.example.subd.model.dto.sale.SaleProductDTO;
import org.example.subd.model.dto.sale.SaleResponseDTO;
import org.example.subd.model.dto.sale.SaleUpdateDTO;
import org.example.subd.model.mapper.SaleProductMapper;
import org.example.subd.repository.BudgetRepo;
import org.example.subd.repository.EmployeeRepo;
import org.example.subd.repository.ProductRepo;
import org.example.subd.repository.SaleRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaleService {


    private final SaleRepo saleRepo;
    private final BudgetRepo budgetRepo;
    private final ProductRepo productRepo;
    private final EmployeeRepo employeeRepo;
    private final SaleProductMapper saleProductMapper;


    public void SaleProduct(SaleProductDTO dto) {

        Product product = productRepo.findById(dto.getProductId()).orElseThrow(() -> new RuntimeException("Продукт не найден"));
        Employee employee = employeeRepo.findById(dto.getEmployeeId()).orElseThrow(() -> new RuntimeException(("Сотрудник не " +
                "найден")));

        if(product.getQuantity().compareTo(dto.getQuantity()) < 0){
            throw new RuntimeException("Недостаточно продукта на складе");
        }


        BigDecimal costPerUnit = product.getAmount().divide(product.getQuantity(), 6, BigDecimal.ROUND_HALF_UP);
        BigDecimal totalCost = costPerUnit.multiply(dto.getAmount()).setScale(2, RoundingMode.HALF_UP);

        product.setQuantity(product.getQuantity().subtract(dto.getQuantity()));
        product.setAmount(product.getAmount().subtract(totalCost).setScale(2, RoundingMode.HALF_UP));
        productRepo.save(product);

        Budget budget = budgetRepo.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("Бюджет не найден"));
        BigDecimal percent = budget.getPercent();
        BigDecimal markup = dto.getAmount().multiply(percent).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        BigDecimal totalToAdd = dto.getAmount().add(markup); //Сумма с надбавкой

        budget.setAmount(budget.getAmount().add(totalToAdd));
        budgetRepo.save(budget);

        Sale sale = saleProductMapper.toEntity(dto, employee, product);
        saleRepo.save(sale);
    }


    @Transactional(readOnly = true)
    public List<SaleResponseDTO> getAll(){
        List<Sale> sale = saleRepo.findAll();
        List<SaleResponseDTO> dto = saleProductMapper.toDtoList(sale);
        return dto;
    }


    @Transactional
    public void update(SaleUpdateDTO dto){
        Employee employee = employeeRepo.findById(dto.getEmployeeId()).orElseThrow(()-> new EntityNotFoundException("Сотрудник " +
                "не найден"));
        Product product = productRepo.findById(dto.getProductId()).orElseThrow(()-> new EntityNotFoundException("Продукт не " +
                "найден"));
        Sale sale = saleProductMapper.UpdateToDto(dto, employee, product);
        saleRepo.save(sale);
    }


    @Transactional(readOnly = true)
    public SaleResponseDTO getOne(UUID id){
        Sale sale = saleRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Проданный продукт не найден"));
        Employee employee = employeeRepo.findById(sale.getEmployee().getId()).orElseThrow(()-> new EntityNotFoundException(
                "Сотрудник не найден"));
        Product product = productRepo.findById(sale.getProduct().getId()).orElseThrow(()-> new EntityNotFoundException("Продукт" +
                " не найден"));


        SaleResponseDTO dto = saleProductMapper.toDto(sale, employee, product);
        return dto;
    }
}

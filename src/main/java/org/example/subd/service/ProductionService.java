package org.example.subd.service;


import org.example.subd.enums.Unit;
import org.example.subd.model.*;
import org.example.subd.model.dto.productions.ProductionDTO;
import org.example.subd.model.mapper.ProductMapper;
import org.example.subd.model.mapper.ProductionMapper;
import org.example.subd.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ProductionService {

    private final ProductRepo productRepo;
    private final IngredientMaterialRepo ingredientMaterialRepo;
    private final MaterialRepo materialRepo;
    private final IngredientRepo ingredientRepo;
    private final ProductionRepo productionRepo;
    private final EmployeeRepo employeeRepo;
    private final ProductionMapper productionMapper;

    public ProductionService(ProductRepo productRepo, ProductionRepo productionRepo, EmployeeRepo employeeRepo, ProductMapper productMapper, ProductionMapper productMapper1, IngredientMaterialRepo ingredientMaterialRepo, MaterialRepo materialRepo, IngredientRepo ingredientRepo, ProductionMapper productionMapper) {
        this.productRepo = productRepo;
        this.productionRepo = productionRepo;
        this.employeeRepo = employeeRepo;
        this.ingredientMaterialRepo = ingredientMaterialRepo;
        this.materialRepo = materialRepo;
        this.ingredientRepo = ingredientRepo;
        this.productionMapper = productionMapper;
    }


    @Transactional
    public void PrductionProduct(ProductionDTO dto){
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        Ingredient ingredient = (Ingredient) ingredientRepo.findByProductId(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Ingredients not found"));
        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));


        List<IngredientMaterial> ingredientMaterials = ingredientMaterialRepo.findAllByIngredientId(ingredient.getId());
        BigDecimal totalProductionCost = BigDecimal.ZERO;

        for(IngredientMaterial im : ingredientMaterials){
            Material material = im.getMaterial();

            BigDecimal requiredPerOne = im.getQuantity(); //На 1 ед. продукта
            Unit requiredUnit = im.getUnit();

            BigDecimal requiredTotal = requiredPerOne.multiply(dto.getQuantity()); //Сколько нужно всего
            BigDecimal available = convertUnit(material.getQuantity(), material.getUnit(), requiredUnit);

            if(available.compareTo(requiredTotal) < 0){
                throw new RuntimeException("Недостаточно материала: " + material.getName());
            }


            //Сколько нужно списать со склада в Unit
            BigDecimal toSubstract = convertUnit(requiredTotal, requiredUnit, material.getUnit());
            //Вычисляем стоимость 1 единицы материала
            BigDecimal pricePerUnit = material.getAmount().divide(material.getQuantity(), 6, RoundingMode.HALF_UP);
            //Стоимость списанного количества
            BigDecimal materialCostUsed = pricePerUnit.multiply(toSubstract)
                    .setScale(2, RoundingMode.HALF_UP);


            //Уменьшаем количество и сумму материала
            material.setQuantity(material.getQuantity().subtract(toSubstract));
            material.setAmount(material.getAmount().subtract(materialCostUsed).setScale(2, RoundingMode.HALF_UP));

            materialRepo.save(material);

            totalProductionCost = totalProductionCost.add(materialCostUsed);
        }

        Production production = new Production();
        production.setProduct(product);
        production.setEmployee(employee);
        production.setQuantity(dto.getQuantity());
        productionRepo.save(production);



        //Обновляем готовый продукт
        product.setQuantity(product.getQuantity().add(dto.getQuantity()));
        product.setAmount(product.getAmount().add(totalProductionCost).setScale(2, RoundingMode.HALF_UP));

        productRepo.save(product);
    }

    private BigDecimal convertUnit(BigDecimal value, Unit from, Unit to) {
        if (from == to) return value;

        // Пример конверсий (можно заменить на Map)
        if (from == Unit.g && to == Unit.kg) return value.divide(BigDecimal.valueOf(1000), 6, RoundingMode.HALF_UP);
        if (from == Unit.kg && to == Unit.g) return value.multiply(BigDecimal.valueOf(1000));

        if (from == Unit.ml && to == Unit.l) return value.divide(BigDecimal.valueOf(1000), 6, RoundingMode.HALF_UP);
        if (from == Unit.l && to == Unit.ml) return value.multiply(BigDecimal.valueOf(1000));

        throw new IllegalArgumentException("Неизвестная конвертация: " + from + " → " + to);
    }
}

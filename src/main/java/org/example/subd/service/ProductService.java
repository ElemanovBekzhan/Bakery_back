package org.example.subd.service;


import org.example.subd.model.Product;
import org.example.subd.model.dto.products.ProductDTO;
import org.example.subd.model.dto.products.ProductResponseDTO;
import org.example.subd.model.mapper.ProductMapper;
import org.example.subd.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;
    private final IngredientMaterialRepo ingredientMaterialRepo;
    private final MaterialRepo materialRepo;
    private final IngredientRepo ingredientRepo;
    private final ProductionRepo productionRepo;
    private final EmployeeRepo employeeRepo;

    public ProductService(ProductRepo productRepo, ProductMapper productMapper, IngredientMaterialRepo ingredientMaterialRepo, MaterialRepo materialRepo, IngredientRepo ingredientRepo, ProductionRepo productionRepo, EmployeeRepo employeeRepo) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
        this.ingredientMaterialRepo = ingredientMaterialRepo;
        this.materialRepo = materialRepo;
        this.ingredientRepo = ingredientRepo;
        this.productionRepo = productionRepo;
        this.employeeRepo = employeeRepo;
    }

    @Transactional
    public void createProduct(ProductDTO dto) {
        Product product = productMapper.toEntity(dto);
        productRepo.save(product);
    }


    public List<ProductResponseDTO> getAll(){
        List<Product> product = productRepo.findAll();
        return ProductMapper.toDtoList(product);
    }


    @Transactional
    public void deleteProduct(UUID id){
        productRepo.deleteById(id);
    }


    public ProductResponseDTO getProduct(UUID id){
        return ProductMapper.toDto(productRepo.findById(id).orElseThrow(() -> new RuntimeException("Продукт не найден")));
    }


    @Transactional
    public void updateProduct(UUID id,ProductDTO DTO){
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Продукт не найден"));
        product.setId(id);
        product.setName(DTO.getName());
        product.setQuantity(DTO.getQuantity());
        product.setAmount(DTO.getAmount());
        productRepo.save(product);
    }


}

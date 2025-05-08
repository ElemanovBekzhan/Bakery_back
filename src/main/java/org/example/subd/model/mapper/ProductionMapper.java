package org.example.subd.model.mapper;


import org.example.subd.model.Employee;
import org.example.subd.model.Product;
import org.example.subd.model.Production;
import org.example.subd.model.dto.productions.ProductionDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductionMapper {

    public Production toEntity(ProductionDTO dto, Product product, Employee employee) {
        Production production = new Production();
        production.setProduct(product);
        production.setEmployee(employee);
        production.setQuantity(dto.getQuantity());

        return production;
    }
}

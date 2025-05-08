package org.example.subd.model.mapper;


import org.example.subd.model.Product;
import org.example.subd.model.dto.products.ProductDTO;
import org.example.subd.model.dto.products.ProductResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product toEntity(final ProductDTO dto) {
        Product p = new Product();
        p.setName(dto.getName());
        p.setQuantity(dto.getQuantity());
        p.setAmount(dto.getAmount());
        return p;
    }


    public static ProductResponseDTO toDto(Product p) {
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setAmount(p.getAmount());
        dto.setQuantitiy(p.getQuantity());
        return dto;
    }


    public static List<ProductResponseDTO> toDtoList(final List<Product> products) {
        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }
}

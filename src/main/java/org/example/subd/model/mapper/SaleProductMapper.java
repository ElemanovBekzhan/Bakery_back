package org.example.subd.model.mapper;


import org.example.subd.model.Employee;
import org.example.subd.model.Product;
import org.example.subd.model.Sale;
import org.example.subd.model.dto.sale.SaleProductDTO;
import org.example.subd.model.dto.sale.SaleResponseDTO;
import org.example.subd.model.dto.sale.SaleUpdateDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleProductMapper {



    public Sale toEntity(SaleProductDTO dto, Employee employee, Product product){
        Sale sale = new Sale();
        sale.setProduct(product);
        sale.setEmployee(employee);
        sale.setQuantity(dto.getQuantity());
        sale.setAmount(dto.getAmount());
        return sale;
    }


    public SaleResponseDTO toDto(Sale s, Employee employee, Product product){
        SaleResponseDTO dto = new SaleResponseDTO();
        dto.setId(s.getId());
        dto.setEmployeeName(employee.getFull_name());
        dto.setProductName(product.getName());
        dto.setQuantity(s.getQuantity());
        dto.setAmount(s.getAmount());
        dto.setSaleDate(s.getCreatedAt());
        return dto;
    }

    public List<SaleResponseDTO> toDtoList(List<Sale> sales) {
        List<SaleResponseDTO> dtoList = new ArrayList<>();
        for (Sale s : sales) {
            // Получаем соответствующих employee и product для каждой продажи
            Employee employee = s.getEmployee();  // Предполагается, что есть метод getEmployee() в Sale
            Product product = s.getProduct();     // Предполагается, что есть метод getProduct() в Sale

            // Преобразуем объект Sale в DTO с использованием соответствующих Employee и Product
            SaleResponseDTO dto = toDto(s, employee, product);
            dtoList.add(dto);
        }
        return dtoList;
    }





    public Sale UpdateToDto(SaleUpdateDTO dto, Employee employee, Product product){
        Sale sale = new Sale();
        sale.setId(dto.getId());
        sale.setProduct(product);
        sale.setEmployee(employee);
        sale.setQuantity(dto.getQuantity());
        sale.setAmount(dto.getAmount());
        return sale;
    }





}

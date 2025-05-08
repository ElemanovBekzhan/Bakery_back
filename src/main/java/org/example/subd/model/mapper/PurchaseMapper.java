package org.example.subd.model.mapper;


import org.example.subd.model.Employee;
import org.example.subd.model.Material;
import org.example.subd.model.Purchase_material;
import org.example.subd.model.dto.purchase.PurchaseDTO;
import org.example.subd.model.dto.purchase.PurchaseMaterialResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PurchaseMapper {


    public Purchase_material toEntity(final PurchaseDTO dto, Employee employee, Material material){
        Purchase_material e = new Purchase_material();
        e.setMaterial(material);
        e.setEmployee(employee);
        e.setQuantity(dto.getQuantity());
        e.setAmount(dto.getAmount());
        return e;
    }



    public static PurchaseMaterialResponseDTO toDto(Purchase_material p){
        PurchaseMaterialResponseDTO dto = new PurchaseMaterialResponseDTO();
        dto.setId(p.getId());
        dto.setAmount(p.getAmount());
        dto.setQuantity(p.getQuantity());
        dto.setEmployeeName(p.getEmployee().getFull_name());
        dto.setMaterialName(p.getMaterial().getName());
        return dto;
    }


    public static List<PurchaseMaterialResponseDTO> toDtoList(List<Purchase_material> p){
        return p.stream()
                .map(PurchaseMapper::toDto)
                .collect(Collectors.toList());
    }


}

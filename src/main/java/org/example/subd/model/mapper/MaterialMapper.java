package org.example.subd.model.mapper;


import org.example.subd.model.Material;
import org.example.subd.model.dto.materials.MaterialDTO;
import org.example.subd.model.dto.materials.MaterialUpdateDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MaterialMapper {

    public Material toEntity(MaterialDTO dto) {
        Material m = new Material();
        m.setName(dto.getName());
        m.setQuantity(dto.getQuantity());
        m.setAmount(dto.getAmount());
        m.setUnit(dto.getUnit());
        return m;
    }

    public MaterialUpdateDTO OnetoDto(Material m){
        MaterialUpdateDTO dto = new MaterialUpdateDTO();
        dto.setId(m.getId());
        dto.setName(m.getName());
        dto.setQuantity(m.getQuantity());
        dto.setAmount(m.getAmount());
        dto.setUnit(m.getUnit());
        return dto;
    }

    public Material updateToEnity(MaterialUpdateDTO dto){
        Material m = new Material();
        m.setId(dto.getId());
        m.setName(dto.getName());
        m.setQuantity(dto.getQuantity());
        m.setAmount(dto.getAmount());
        m.setUnit(dto.getUnit());
        return m;
    }


    public static MaterialUpdateDTO toDto (Material m) {
        MaterialUpdateDTO dto = new MaterialUpdateDTO();
        dto.setId(m.getId());
        dto.setName(m.getName());
        dto.setAmount(m.getAmount());
        dto.setQuantity(m.getQuantity());
        dto.setUnit(m.getUnit());
        return dto;
    }

    public List<MaterialUpdateDTO> toDtoList(List<Material> m) {
        return m.stream()
                .map(MaterialMapper::toDto)
                .collect(Collectors.toList());
    }

}

package org.example.subd.service;


import org.example.subd.model.Material;
import org.example.subd.model.dto.materials.MaterialDTO;
import org.example.subd.model.dto.materials.MaterialUpdateDTO;
import org.example.subd.model.mapper.MaterialMapper;
import org.example.subd.repository.MaterialRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MaterialService {

    private final MaterialRepo materialRepo;
    private final MaterialMapper materialMapper;

    public MaterialService(MaterialRepo materialRepo, MaterialMapper materialMapper) {
        this.materialRepo = materialRepo;
        this.materialMapper = materialMapper;
    }

    @Transactional
    public void createMaterial(MaterialDTO dto) {
        Material mat = materialMapper.toEntity(dto);
        materialRepo.save(mat);
    }


    @Transactional(readOnly = true)
    public List<MaterialUpdateDTO> getAllMaterials() {
        List<Material> materials = materialRepo.findAll();
        List<MaterialUpdateDTO> dtos = materialMapper.toDtoList(materials);
        return dtos;
    }


    @Transactional
    public void updateMaterial(MaterialUpdateDTO dto) {
        Material m =  materialMapper.updateToEnity(dto);
        materialRepo.save(m);
    }

    @Transactional(readOnly = true)
    public MaterialUpdateDTO getMaterial(UUID id) {
        Material m =  materialRepo.findById(id).orElseThrow(() -> new RuntimeException("Material not found"));
        MaterialUpdateDTO dto = materialMapper.OnetoDto(m);
        return dto;
    }
}

package org.example.subd.repository;


import org.example.subd.model.Material;
import org.example.subd.model.dto.materials.MaterialSelectListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MaterialRepo extends JpaRepository<Material, UUID> {

    @Query("select new org.example.subd.model.dto.materials.MaterialSelectListDTO(m.id, m.name) from Material m")
    List<MaterialSelectListDTO> findAllSimle();
}

package org.example.subd.repository;


import org.example.subd.model.IngredientMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IngredientMaterialRepo extends JpaRepository<IngredientMaterial, UUID> {
    List<IngredientMaterial> findAllByIngredientId(UUID id);
}

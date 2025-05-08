package org.example.subd.repository;

import org.example.subd.model.Ingredient;
import org.example.subd.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IngredientRepo extends JpaRepository<Ingredient, UUID> {

    Optional<Object> findByProductId(UUID productId);
}

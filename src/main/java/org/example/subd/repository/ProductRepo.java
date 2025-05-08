package org.example.subd.repository;


import org.example.subd.model.Product;
import org.example.subd.model.dto.products.ProductSelectListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepo extends JpaRepository<Product, UUID> {



    @Query("select new org.example.subd.model.dto.products.ProductSelectListDTO(p.id, p.name) from Product p")
    List<ProductSelectListDTO> findAllSimple();
}

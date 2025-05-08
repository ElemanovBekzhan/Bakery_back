package org.example.subd.controller;


import org.example.subd.model.dto.products.ProductDTO;
import org.example.subd.model.dto.products.ProductResponseDTO;
import org.example.subd.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;




    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductDTO dto) {
        productService.createProduct(dto);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/all")
    public ResponseEntity<List<ProductResponseDTO>> getAll() {
        List<ProductResponseDTO> dto = productService.getAll();
        return ResponseEntity.ok().body(dto);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable UUID id) {
        ProductResponseDTO dto = productService.getProduct(id);
        return ResponseEntity.ok().body(dto);
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editProduct(@PathVariable UUID id, @RequestBody ProductDTO dto) {
        productService.updateProduct(id, dto);
        return ResponseEntity.ok().build();
    }
}

package org.example.subd.controller;


import lombok.RequiredArgsConstructor;
import org.example.subd.model.dto.ingredients.IngredienUpdateDTO;
import org.example.subd.model.dto.ingredients.IngredientDTO;
import org.example.subd.model.dto.ingredients.IngredientResponseDTO;
import org.example.subd.model.dto.materials.MaterialSelectListDTO;
import org.example.subd.model.dto.products.ProductSelectListDTO;
import org.example.subd.repository.IngredientRepo;
import org.example.subd.repository.MaterialRepo;
import org.example.subd.repository.ProductRepo;
import org.example.subd.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;
    private final IngredientRepo ingredientRepo;
    private final ProductRepo productRepo;
    private final MaterialRepo materialRepo;


    @PostMapping("/save")
    public ResponseEntity<IngredientDTO> save(@RequestBody IngredientDTO dto) {
        ingredientService.createIngredient(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }



    @GetMapping("/all")
    public ResponseEntity<List<IngredientResponseDTO>> getAll() {
        return ResponseEntity.ok().body(ingredientService.getAll());
    }

    @GetMapping("/select-listProduct")
    public ResponseEntity<List<ProductSelectListDTO>> listProduct() {
        return ResponseEntity.ok().body(productRepo.findAllSimple());
    }

    @GetMapping("/select-listMaterial")
    public ResponseEntity<List<MaterialSelectListDTO>> listMaterial() {
        return ResponseEntity.ok().body(materialRepo.findAllSimle());
    }


    @GetMapping("/getOne/{id}")
    public ResponseEntity<IngredientResponseDTO> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok().body(ingredientService.findById(id));
    }


    @PutMapping("/edit")
    public ResponseEntity<?> update(@RequestBody IngredienUpdateDTO dto) {
        ingredientService.updateIngredient(dto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        ingredientRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

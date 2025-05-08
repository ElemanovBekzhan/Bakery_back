package org.example.subd.controller;


import lombok.RequiredArgsConstructor;
import org.example.subd.model.dto.materials.MaterialDTO;
import org.example.subd.model.dto.materials.MaterialUpdateDTO;
import org.example.subd.repository.MaterialRepo;
import org.example.subd.service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/material")
@RequiredArgsConstructor
public class MaterialController {

    private final MaterialService materialService;
    private final MaterialRepo materialRepo;


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MaterialDTO dto) {
        try {
            materialService.createMaterial(dto);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok().body(materialService.getAllMaterials());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody MaterialUpdateDTO dto) {
        try {
            materialService.updateMaterial(dto);
            return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        try {
            materialRepo.deleteById(id);
            return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok().body(materialService.getMaterial(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

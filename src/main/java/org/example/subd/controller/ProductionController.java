package org.example.subd.controller;


import org.example.subd.model.dto.productions.ProductionDTO;
import org.example.subd.service.ProductionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/production")
public class ProductionController {

    private final ProductionService productionService;


    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @PostMapping("/production")
    public ResponseEntity<?> production(@RequestBody ProductionDTO dto) {
        productionService.PrductionProduct(dto);
        return ResponseEntity.ok().build();
    }

}

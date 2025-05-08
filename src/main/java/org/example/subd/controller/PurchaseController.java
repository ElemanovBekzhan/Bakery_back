package org.example.subd.controller;


import lombok.RequiredArgsConstructor;
import org.example.subd.model.dto.purchase.PurchaseDTO;
import org.example.subd.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;


    @PostMapping()
    public ResponseEntity<?> purchaseMaterial(PurchaseDTO dto){
        purchaseService.purchaseMaterial(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(purchaseService.getPurchase());
    }





}

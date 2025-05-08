package org.example.subd.controller;


import lombok.RequiredArgsConstructor;
import org.example.subd.model.dto.sale.SaleProductDTO;
import org.example.subd.model.dto.sale.SaleResponseDTO;
import org.example.subd.model.dto.sale.SaleUpdateDTO;
import org.example.subd.repository.SaleRepo;
import org.example.subd.service.EmployeeService;
import org.example.subd.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sale")
public class SaleController {

    private final SaleService saleService;
    private final SaleRepo saleRepo;
    private final EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SaleProductDTO dto){
        saleService.SaleProduct(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return new ResponseEntity<>(saleService.getAll(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody SaleUpdateDTO dto){
        try{
            saleService.update(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        try {
            saleRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employeesList")
    public ResponseEntity<?> getEmployeeList(){
        try{
            return ResponseEntity.ok(employeeService.SelectListEmployee());
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<SaleResponseDTO> getProductForUpdate(@PathVariable UUID id){
        try{
            SaleResponseDTO dto = saleService.getOne(id);
            return ResponseEntity.ok().body(dto);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

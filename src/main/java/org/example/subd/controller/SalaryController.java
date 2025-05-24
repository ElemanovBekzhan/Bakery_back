package org.example.subd.controller;


import lombok.RequiredArgsConstructor;
import org.example.subd.model.dto.salary.SalaryDTO;
import org.example.subd.model.dto.salary.SalaryUpdateDTO;
import org.example.subd.repository.SalaryRepo;
import org.example.subd.service.SalaryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/salaries")
@RequiredArgsConstructor
public class SalaryController {

    private final SalaryService salaryService;
    private final SalaryRepo salaryRepo;

    @PostMapping("/calculated")
    public ResponseEntity<String> calculatedSalaries(){
        salaryService.calculateAllSalary();
        return ResponseEntity.ok("Зарплаты успешно рассчитаны");
    }

    @GetMapping("/pending")
    public ResponseEntity<List<SalaryDTO>> getPendingSalaries(){
        List<SalaryDTO> salaries = salaryService.getPending();
        return ResponseEntity.ok(salaries);
    }

    @PostMapping("/issue")
    public ResponseEntity<String> issueAllSalaries(){
        String result = salaryService.issueCalculatedSalaries();
        if ("OK".equals(result)) {
            return ResponseEntity.ok("Все рассчитанные зарплаты выданы");
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SalaryDTO>> getAllSalaries(){
        List<SalaryDTO> salaries = salaryService.getAll();
        return ResponseEntity.ok(salaries);
    }


    @PatchMapping("/update")
    public ResponseEntity<?> updateSalary(@RequestBody SalaryUpdateDTO dto){
        try{
            salaryService.update(dto);
            return ResponseEntity.ok().body(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaryDTO> getSalaryById(@PathVariable UUID id){
        return ResponseEntity.ok().body(salaryService.getById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSalaryById(@PathVariable UUID id){
        try {
            salaryRepo.deleteById(id);
            return ResponseEntity.ok().body(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return ResponseEntity.ok().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

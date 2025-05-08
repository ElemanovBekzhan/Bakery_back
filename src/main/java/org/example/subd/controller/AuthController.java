package org.example.subd.controller;


import org.example.subd.model.dto.employees.EmployeeDTO;
import org.example.subd.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final EmployeeService employeeService;

    public AuthController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> register(@RequestBody EmployeeDTO dto) {
        employeeService.UserReg(dto);
        return ResponseEntity.ok().build();
    }

}

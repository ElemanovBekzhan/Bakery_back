package org.example.subd.controller;


import lombok.RequiredArgsConstructor;
import org.example.subd.jwt.EmployeeDetails;
import org.example.subd.model.dto.employees.UserResponseDto;
import org.example.subd.model.dto.employees.UserUpdateDto;
import org.example.subd.repository.EmployeeRepo;
import org.example.subd.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final EmployeeService employeeService;
    private final EmployeeRepo employeeRepo;


    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = authentication.getName();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("roles", roles);

        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        return ResponseEntity.ok().body(employeeService.getUsers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody UserUpdateDto dto) {
        employeeService.updateEmployee(id, dto);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        employeeRepo.deleteById(id);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
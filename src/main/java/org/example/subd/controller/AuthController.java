package org.example.subd.controller;


import lombok.RequiredArgsConstructor;
import org.example.subd.jwt.EmployeeDetails;
import org.example.subd.jwt.JwtUtils;
import org.example.subd.model.Employee;
import org.example.subd.model.dto.employees.AuthRequest;
import org.example.subd.model.dto.employees.AuthResponse;
import org.example.subd.model.dto.employees.EmployeeDTO;
import org.example.subd.repository.EmployeeRepo;
import org.example.subd.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


    private final EmployeeRepo repo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtil;
    private final EmployeeService employeeService;



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> register(@RequestBody EmployeeDTO dto) {
        employeeService.UserReg(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        Employee emp = repo.findByEmail(req.getEmail()).get();
        String token = jwtUtil.generateToken(new EmployeeDetails(emp));
        return ResponseEntity.ok(new AuthResponse(token));
    }

}

package org.example.subd.service;


import org.example.subd.model.Employee;
import org.example.subd.model.Position;
import org.example.subd.model.dto.employees.EmployeeDTO;
import org.example.subd.model.dto.employees.EmployeeSelectListDTO;
import org.example.subd.model.mapper.EmployeeMapper;
import org.example.subd.repository.EmployeeRepo;
import org.example.subd.repository.PositionRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;
    private final PositionRepo positionRepo;

    public EmployeeService(EmployeeRepo employeeRepo, EmployeeMapper employeeMapper, PasswordEncoder passwordEncoder, PositionRepo positionRepo) {
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
        this.positionRepo = positionRepo;
    }

    @Transactional
    public void UserReg(EmployeeDTO dto) {
        Position position = positionRepo.findById(dto.getPositionid()).orElseThrow(() -> new RuntimeException("Position not found"));

        Employee employee = employeeMapper.toEntityEmployee(dto, position);
        employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        employeeRepo.save(employee);
    }


    @Transactional(readOnly = true)
    public List<EmployeeSelectListDTO> SelectListEmployee() {
        List<EmployeeSelectListDTO> employees = employeeRepo.findAllEmployeeSelectList();
        return employees;
    }


}

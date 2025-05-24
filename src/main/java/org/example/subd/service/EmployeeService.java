package org.example.subd.service;


import jakarta.persistence.EntityNotFoundException;
import org.example.subd.model.Employee;
import org.example.subd.model.dto.employees.*;
import org.example.subd.model.mapper.EmployeeMapper;
import org.example.subd.repository.EmployeeRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepo employeeRepo, EmployeeMapper employeeMapper, PasswordEncoder passwordEncoder) {
        this.employeeRepo = employeeRepo;
        this.employeeMapper = employeeMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void UserReg(EmployeeDTO dto) {
        Employee employee = employeeMapper.toEntityEmployee(dto);
        employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        employeeRepo.save(employee);
    }


    @Transactional(readOnly = true)
    public List<EmployeeSelectListDTO> SelectListEmployee() {
        List<EmployeeSelectListDTO> employees = employeeRepo.findAllEmployeeSelectList();
        return employees;
    }


    @Transactional(readOnly = true)
    public List<UserResponseDto> getUsers(){
        List<Employee> e = employeeRepo.findAll();
        List<UserResponseDto> users = employeeMapper.toDtoList(e);
        return users;
    }



    @Transactional
    public void updateEmployee(UUID id, UserUpdateDto dto){
        Employee e = employeeRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Сотрудник не найден"));
        employeeMapper.updateEmployee(e, dto);
        employeeRepo.save(e);
    }





}

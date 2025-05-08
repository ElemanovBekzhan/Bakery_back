package org.example.subd.model.mapper;

import org.example.subd.model.Employee;
import org.example.subd.model.Position;
import org.example.subd.model.dto.employees.EmployeeDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntityEmployee(EmployeeDTO dto, Position position) {
        Employee e = new Employee();
        e.setFull_name(dto.getName());
        e.setSalary(dto.getSalary());
        e.setEmail(dto.getEmail());
        e.setAddress(dto.getAddress());
        e.setPhone_number(dto.getPhone());
        e.setPassword(dto.getPassword());
        e.setRole(dto.getRole());
        e.setPosition(position);
        return e;
    }
}

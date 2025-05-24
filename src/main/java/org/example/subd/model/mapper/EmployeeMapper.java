package org.example.subd.model.mapper;

import org.example.subd.model.Employee;
import org.example.subd.model.dto.employees.EmployeeDTO;
import org.example.subd.model.dto.employees.UserResponseDto;
import org.example.subd.model.dto.employees.UserUpdateDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public Employee toEntityEmployee(EmployeeDTO dto) {
        Employee e = new Employee();
        e.setFull_name(dto.getName());
        e.setSalary(dto.getSalary());
        e.setEmail(dto.getEmail());
        e.setAddress(dto.getAddress());
        e.setPhone_number(dto.getPhone());
        e.setPassword(dto.getPassword());
        e.setRole(dto.getRole());
        return e;
    }


    public static UserResponseDto toDto(Employee employee){
        UserResponseDto dto = new UserResponseDto();
        dto.setId(employee.getId());
        dto.setFull_name(employee.getFull_name());
        dto.setEmail(employee.getEmail());
        dto.setPhone_number(employee.getPhone_number());
        dto.setRole(employee.getRole().name());
        dto.setAddress(employee.getAddress());
        dto.setSalary(employee.getSalary());
        return dto;
    }

    public List<UserResponseDto> toDtoList(final List<Employee> employees){
        return employees.stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }


    public void updateEmployee(final Employee e, UserUpdateDto dto){
        if(dto.getFull_name() != null){ e.setFull_name(dto.getFull_name()); }
        if(dto.getEmail() != null){ e.setEmail(dto.getEmail()); }
        if(dto.getPhone_number() != null){ e.setPhone_number(dto.getPhone_number()); }
        if(dto.getRole() != null){ e.setRole(dto.getRole()); }
        if(dto.getAddress() != null){ e.setAddress(dto.getAddress());}
        if(dto.getSalary()!= null){ e.setSalary(dto.getSalary()); }
    }
}

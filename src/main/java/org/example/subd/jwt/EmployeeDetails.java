package org.example.subd.jwt;


import lombok.AllArgsConstructor;
import org.example.subd.enums.Role;
import org.example.subd.model.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class EmployeeDetails implements UserDetails {


    private final Employee employee;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // берём Role из enum и формируем SimpleGrantedAuthority
        return List.of(new SimpleGrantedAuthority("ROLE_" + employee.getRole().name()));
    }



    @Override public String getPassword()    { return employee.getPassword(); }
    @Override public String getUsername()    { return employee.getEmail(); }
    @Override public boolean isAccountNonExpired()   { return true; }
    @Override public boolean isAccountNonLocked()    { return true; }
    @Override public boolean isCredentialsNonExpired(){ return true; }
    @Override public boolean isEnabled()             { return true; }

    // при необходимости добавьте геттер на сам Employee
    public Employee getEmployee() { return employee; }
}

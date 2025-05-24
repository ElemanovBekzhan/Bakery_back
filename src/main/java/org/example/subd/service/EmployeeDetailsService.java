package org.example.subd.service;

import lombok.AllArgsConstructor;
import org.example.subd.jwt.EmployeeDetails;
import org.example.subd.model.Employee;
import org.example.subd.repository.EmployeeRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EmployeeDetailsService implements UserDetailsService {


    private final EmployeeRepo repo;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee emp = repo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Employee not found: " + email));
        return new EmployeeDetails(emp);
    }
}

package com.tejas.employeemanagement.service;

import com.tejas.employeemanagement.model.Employee;
import com.tejas.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Create
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Read all
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Read one
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Update
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Delete
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}


package com.tejas.employeemanagement.controller;

import com.tejas.employeemanagement.model.Employee;
import com.tejas.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    // Read all
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Read one
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeService.getEmployeeById(id)
                .map(existing -> {
                    existing.setName(updatedEmployee.getName());
                    existing.setEmail(updatedEmployee.getEmail());
                    existing.setJobTitle(updatedEmployee.getJobTitle());
                    existing.setPhone(updatedEmployee.getPhone());
                    existing.setImageUrl(updatedEmployee.getImageUrl());
                    return ResponseEntity.ok(employeeService.updateEmployee(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        if (employeeService.getEmployeeById(id).isPresent()) {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


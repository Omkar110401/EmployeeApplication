package com.example.employee.controllers;

import com.example.employee.classes.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {
    private Map<String, Employee> employees = new HashMap<>();

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting() {
        return ResponseEntity.ok("Hello world!");
    }

    @PostMapping("/employee")
    public ResponseEntity<Map<String, String>> createEmployee(@RequestBody Employee employee) {
        String employeeId = String.valueOf(employees.size() + 1);
        employee.setEmployeeId(employeeId);
        employees.put(employeeId, employee);

        Map<String, String> response = new HashMap<>();
        response.put("employeeId", employeeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable String id) {
        Employee employee = employees.get(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            String errorMessage = "Employee with " + id + " was not found";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @GetMapping("/employees/all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>(employees.values());
        return ResponseEntity.ok(employeeList);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee updatedEmployee) {
        Employee employee = employees.get(id);
        if (employee != null) {
            employee.setName(updatedEmployee.getName());
            employee.setCity(updatedEmployee.getCity());
            return ResponseEntity.status(HttpStatus.CREATED).body(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable String id) {
        Employee employee = employees.remove(id);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

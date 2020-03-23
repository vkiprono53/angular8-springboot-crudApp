package com.kiprono.employee.controllers;

import com.kiprono.employee.models.Employee;
import com.kiprono.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
//http://localhost:4200/api/v1
@RequestMapping("/api/v1")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Optional<Employee> employeeId = employeeService.findEmployeeById(id);
        if (!employeeId.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(employeeId);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        Employee employee1 = employeeService.createEmployee(employee);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(employee1.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {

        Optional<Employee> employee = employeeService.findEmployeeById(id);
        if (!employee.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        employeeService.deleteEmployees(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee employee){
        Optional<Employee> employee1 = employeeService.findEmployeeById(id);
        if (!employee1.isPresent()){
            return ResponseEntity.notFound().build();
        }
        employee.setId(id);
        employeeService.createEmployee(employee);
        return ResponseEntity.ok(employeeService.createEmployee(employee));
    }
}
